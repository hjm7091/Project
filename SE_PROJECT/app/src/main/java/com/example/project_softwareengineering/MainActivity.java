package com.example.project_softwareengineering;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText email_id;
    private EditText password_id;
    private Button join_id;
    private Button login_id;

    private String email="";
    private String password="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);  //UI가림 방지
        init_variable();
        join_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
                email_id.setText(null);
                password_id.setText(null);
            }
        });
        login_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = email_id.getText().toString();
                password = password_id.getText().toString();
                if(check_pattern(email, password))
                    SingIn(email, password);
            }
        });

    }

    private void dialog_show(final FirebaseUser user)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("이메일 인증이 필요한 계정입니다.");
        builder.setMessage("인증하시겠습니까?");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(),"예를 선택했습니다.",Toast.LENGTH_SHORT).show();
                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {                         //해당 이메일에 확인메일을 보냄
                                    //Log.d(TAG, "Email sent.");
                                    Toast.makeText(MainActivity.this,
                                            "인증 이메일을 " + user.getEmail() + "로 보냈습니다.",
                                            Toast.LENGTH_SHORT).show();
                                    int idx = user.getEmail().indexOf("@");
                                    String mail = user.getEmail().substring(idx+1);
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setData(Uri.parse("http://mail."+mail));
                                    startActivity(intent);
                                } else {                                             //메일 보내기 실패
                                    //Log.e(TAG, "sendEmailVerification", task.getException());
                                    Toast.makeText(MainActivity.this,
                                            "인증 이메일을 이미 보냈습니다. 메일을 확인해주세요.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"취소되었습니다.",Toast.LENGTH_SHORT).show();
                    }
                });
        builder.show();
    }

    private boolean check_pattern(String email, String password){
        if(email.equals("")||password.equals("")) {
            Toast.makeText(MainActivity.this, "이메일 또는 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!email.contains("@")||!email.contains(".")){
            Toast.makeText(MainActivity.this, "올바른 형식의 이메일 주소를 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(password.length()<6) {
            Toast.makeText(MainActivity.this, "비밀번호를 6자이상 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void init_variable(){
        mAuth = FirebaseAuth.getInstance();
        email_id = findViewById(R.id.editText_email);
        password_id = findViewById(R.id.editText_password);
        join_id = findViewById(R.id.button_join);
        login_id = findViewById(R.id.button_login);
    }

    private void SingIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithEmail:success");
                            user = mAuth.getCurrentUser();
                            if(user.isEmailVerified()) {
                                Toast.makeText(MainActivity.this, "로그인 성공",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), BoardActivity.class);
                                startActivity(intent);
                            }
                            else{
                                dialog_show(user);
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "이메일 또는 비밀번호를 확인해주세요.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

}
