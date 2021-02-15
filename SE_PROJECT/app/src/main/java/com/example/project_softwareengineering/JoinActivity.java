package com.example.project_softwareengineering;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class JoinActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private EditText email_id;
    private EditText password_id;
    private EditText name_id;
    private Button join_id;

    private String email="";
    private String password="";
    private String name="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);  //UI가림 방지
        find_Id();
        join_id.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                email = email_id.getText().toString();
                password = password_id.getText().toString();
                name = name_id.getText().toString();
                //Toast.makeText(JoinActivity.this, name, Toast.LENGTH_SHORT).show();
                if(check_pattern(email, password, name)){
                    createUser(email, password, name);
                }
            }
        });

    }

    private boolean check_pattern(String email, String password, String name){
        if(email.equals("")||password.equals("")) {
            Toast.makeText(JoinActivity.this, "이메일 또는 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!email.contains("@")||!email.contains(".")){
            Toast.makeText(JoinActivity.this, "올바른 형식의 이메일 주소를 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        /*else if(!email.contains("kangwon.ac.kr")){
            Toast.makeText(JoinActivity.this, "강원대학교 메일만 사용 가능합니다.", Toast.LENGTH_SHORT).show();
            return false;
        }*/
        else if(password.length()<6) {
            Toast.makeText(JoinActivity.this, "비밀번호를 6자이상 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(name.length()<=0){
            Toast.makeText(JoinActivity.this, "이름을 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void find_Id(){
        mAuth = FirebaseAuth.getInstance();
        email_id = findViewById(R.id.editText_email);
        password_id = findViewById(R.id.editText_password);
        name_id = findViewById(R.id.editText_name);
        join_id = findViewById(R.id.button_join);
    }

    private void createUser(String email, final String password, final String name){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(JoinActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                            user = mAuth.getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                //Log.d(TAG, "User profile updated.");
                                                //Toast.makeText(JoinActivity.this, "이름 변경", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(JoinActivity.this, "이미 존재하는 계정입니다.", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}
