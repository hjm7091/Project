package com.example.project_softwareengineering.ui.share;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.project_softwareengineering.R;
import com.example.project_softwareengineering.ui.home.MyItem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class ShareFragment extends Fragment {

    //private ShareViewModel shareViewModel;
    private Button make_write;
    private Boolean wantToCloseDialog;
    private ListView sListView;
    private ShareAdapter sAdapter;

    private String title = "", content = "";
    private String name = "", email = "", uid = "";

    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference databaseRef;

    private ArrayList<String> key_list = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        final TextView textView = root.findViewById(R.id.text_share);
        shareViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        View root = inflater.inflate(R.layout.fragment_share, container, false);
        init_variable(root);
        read_once();
        make_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "버튼을 눌렀습니다.", Toast.LENGTH_SHORT).show();
                make_dialog(v);
            }
        });

        sListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final ShareItem Item = sAdapter.getItem(position);
                final String user_email = Item.getEmail();
                //Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("글을 삭제하시겠습니까?");
                builder.setPositiveButton("예",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //Toast.makeText(getContext(),"예를 선택했습니다.",Toast.LENGTH_SHORT).show();
                                if(email.equals(user_email)) {
                                    remove_data(Item, position);
                                    Toast.makeText(getContext(), "삭제 되었습니다.", Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(getContext(),"본인이 작성한 글만 삭제가 가능합니다.",Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNegativeButton("아니오",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getContext(),"취소되었습니다.",Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.show();
                return true;
            }
        });

        sListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShareItem Item = sAdapter.getItem(position);
                String key = key_list.get(position);
                //Toast.makeText(getContext(), key, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(), DetailShareActivity.class);
                intent.putExtra("share_key", key);
                intent.putExtra("share_class", Item);
                startActivity(intent);
            }
        });

        return root;
    }

    public void init_variable(View root){
        make_write = root.findViewById(R.id.button_write);
        auth = FirebaseAuth.getInstance();
        name = auth.getCurrentUser().getDisplayName();
        email = auth.getCurrentUser().getEmail();
        uid = auth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReference();
        sListView = root.findViewById(R.id.listView);
        sAdapter = new ShareAdapter();
        sListView.setAdapter(sAdapter);
    }

    public void make_dialog(final View v){
        wantToCloseDialog = false;
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog_share, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("글쓰기");
        builder.setView(dialogView);
        builder.setPositiveButton("확인",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //Do nothing here because we override this button later to change the close behaviour.
                        //However, we still need this because on older versions of Android unless we
                        //pass a handler the button doesn't get instantiated
                    }
                });
        builder.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        final AlertDialog dialog = builder.create();
        dialog.show();

        //Overriding the handler immediately after show is probably a better approach than OnShowListener as described below
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText editText_title = dialogView.findViewById(R.id.editText_title);
                EditText editText_content = dialogView.findViewById(R.id.editText_content);
                CheckBox checkBox = dialogView.findViewById(R.id.checkBox);
                if(checkBox.isChecked())
                    name = "익명";
                else
                    name = auth.getCurrentUser().getDisplayName();
                title = editText_title.getText().toString();
                content = editText_content.getText().toString();
                if(check_validation(title, content)) {
                    dataUpload(title, content, name, email);
                    wantToCloseDialog = true;
                }
                //Do stuff, possibly set wantToCloseDialog to true then...
                if(wantToCloseDialog) {
                    dialog.dismiss();
                    //mListView.setAdapter(myAdapter);
                }
                //else dialog stays open. Make sure you have an obvious way to close the dialog especially if you set cancellable to false.
            }
        });
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wantToCloseDialog = true;
                if(wantToCloseDialog) {
                    title = "";
                    content = "";
                    Toast.makeText(v.getContext(), "취소되었습니다.", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

    }

    public void read_once(){ //데이터가 추가되거나 삭제될 때마다 이 메소드를 호출하여 리스트를 업데이트 해준다.

        //addListenerForSingleValueEvent
        databaseRef.child("free").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                key_list.clear();
                sAdapter.clear();
                for(final DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if(snapshot.getValue() != null) {
                        final ShareItem Item = snapshot.getValue(ShareItem.class);
                        key_list.add(snapshot.getKey());
                        sAdapter.addItem(Item.getTitle(), Item.getContent(), Item.getName(), Item.getEmail(), Item.getDate(), Item.getLike(), Item.map);
                        //sAdapter.notifyDataSetChanged();
                    }
                }
                sAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "에러", Toast.LENGTH_SHORT).show();
            }


        });

    }

    public void remove_data(ShareItem Item, final int position){

        databaseRef.child("free").child(key_list.get(position)).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                read_once();
                //Toast.makeText(getContext(), "데이터 삭제 완료", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "데이터 삭제 실패", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void dataUpload(final String title, final String content, final String name, final String email){
        String date = getCurrentDate();
        ShareItem item = new ShareItem();
        item.setTitle(title);
        item.setContent(content);
        item.setDate(date);
        item.setName(name);
        item.setEmail(email);
        item.map.put(uid, true);
        databaseRef.child("free").push().setValue(item);
        read_once();
        //Toast.makeText(getContext(), "데이터 업로드 성공", Toast.LENGTH_SHORT).show();

    }

    public String getCurrentDate(){
        TimeZone tz;
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //"yyyy-MM-dd HH:mm:ss (z)"
        tz = TimeZone.getTimeZone("Asia/Seoul"); df.setTimeZone(tz);
        return df.format(date);
    }

    public boolean check_validation(String title, String content){
        if(title.equals("")){
            Toast.makeText(getContext(), "제목을 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(content.equals("")){
            Toast.makeText(getContext(), "내용을 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}