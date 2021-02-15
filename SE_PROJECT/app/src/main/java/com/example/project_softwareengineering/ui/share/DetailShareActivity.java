package com.example.project_softwareengineering.ui.share;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_softwareengineering.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class DetailShareActivity extends AppCompatActivity {

    private TextView textView_title;
    private TextView textView_content;
    private TextView textView_name;
    private ImageButton imageButton;
    private TextView textView_like;
    private ListView listView;
    private ArrayAdapter adapter;
    private EditText editText;
    private Button button;
    private InputMethodManager imm;

    private ArrayList<String> list = new ArrayList<>();

    String uid = "";

    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_share);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);  //UI가림 방지
        findId();
        Intent intent = getIntent();
        final String key = (String) intent.getExtras().get("share_key");
        ShareItem Item = (ShareItem) intent.getExtras().get("share_class");
        setData(Item);
        read(key);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String line = "";
                line = editText.getText().toString();
                if(line.equals(""))
                    Toast.makeText(getApplicationContext(), "글을 입력하세요.", Toast.LENGTH_SHORT).show();
                else{
                    String now_user = auth.getCurrentUser().getDisplayName();
                    databaseRef.child("free").child(key).child("comments").push().setValue(now_user+" : "+line);
                    read(key);
                    editText.setText(null);
                    hideKeyboard();
                }
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() { //좋아요 버튼
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "이미지 버튼을 눌렀습니다.", Toast.LENGTH_SHORT).show();
                update_like(key);
            }
        });
    }

    public void read(String key){
        databaseRef.child("free").child(key).child("comments").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(final DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if(snapshot != null) {
                        list.add(snapshot.getValue().toString());
                        adapter.notifyDataSetChanged();
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "에러", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void update_like(String key){
        databaseRef.child("free").child(key).runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                String like = mutableData.child("like").getValue().toString();
                Map<String, Boolean> map = (Map<String, Boolean>) mutableData.child("map").getValue();
                if(!map.containsKey(uid)) {
                    int cnt = Integer.parseInt(like);
                    cnt++;
                    mutableData.child("like").setValue(cnt);
                    map.put(uid, true);
                    mutableData.child("map").setValue(map);
                }
                /*else
                    Log.d("like", "불가능");*/
                //Log.d("value", mutableData.child("like").getValue().toString());
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
                ShareItem item = dataSnapshot.getValue(ShareItem.class);
                //Log.d("after +", item.getLike()+"");
                textView_like.setText(item.getLike()+"");
            }
        });

    }

    public void setData(ShareItem Item){
        textView_title.setText(Item.getTitle());
        textView_content.setText(Item.getContent());
        textView_name.setText(Item.getDate() + " | " + Item.getName());
        textView_like.setText(Item.getLike()+"");
    }

    public void findId(){
        auth = FirebaseAuth.getInstance();
        uid = auth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReference();
        textView_title = findViewById(R.id.textView_title);
        textView_content = findViewById(R.id.textView_content);
        textView_name = findViewById(R.id.textView_name);
        imageButton = findViewById(R.id.imageButton_thumb);
        textView_like = findViewById(R.id.textView_like);
        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        editText = findViewById(R.id.edit);
        button = findViewById(R.id.button);
        imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
    }

    public void hideKeyboard(){
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
