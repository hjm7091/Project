package com.example.project_softwareengineering.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.project_softwareengineering.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailHomeActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView info;
    private TextView user;
    private TextView time;
    private TextView price;
    private TextView address;
    private ListView listView;
    private ArrayAdapter adapter;
    private EditText editText;
    private Button button;
    private InputMethodManager imm;

    private ArrayList<String> list = new ArrayList<>();

    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_home);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);  //UI가림 방지
        findId();
        Intent intent = getIntent();
        MyItem Item = (MyItem) intent.getExtras().get("room_class");
        final String key = (String) intent.getExtras().get("room_key");
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
                    databaseRef.child("room").child(key).child("comments").push().setValue(now_user+" : "+line);
                    read(key);
                    editText.setText(null);
                    hideKeyboard();
                }
            }
        });
    }

    public void read(String key){
        databaseRef.child("room").child(key).child("comments").addListenerForSingleValueEvent(new ValueEventListener() {
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

    public void setData(MyItem Item){
        Glide.with(getApplicationContext()).load(Item.getImageURI()).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        user.append(" " + Item.getName());
        time.append(" " + Item.getTime());
        price.append(" " + Item.getPrice());
        address.append(" " + Item.getAddress());
        user.setTextColor(Color.parseColor("#000000"));
        time.setTextColor(Color.parseColor("#000000"));
        price.setTextColor(Color.parseColor("#000000"));
        address.setTextColor(Color.parseColor("#000000"));
    }

    public void findId(){
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReference();
        imageView = findViewById(R.id.image_room);
        info = findViewById(R.id.info);
        user = findViewById(R.id.user);
        time = findViewById(R.id.time);
        price = findViewById(R.id.price);
        address = findViewById(R.id.address);
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
