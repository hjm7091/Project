package com.example.project_softwareengineering.ui.home;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import com.example.project_softwareengineering.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

    //private HomeViewModel homeViewModel;
    private Button make_room_button;
    private ImageView imageView_room;
    private Boolean wantToCloseDialog;
    private ListView mListView;
    private MyAdapter myAdapter;

    private Uri photoURI = null;
    private String time="", price="", address="", name="", email="";

    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private FirebaseDatabase database;
    private DatabaseReference databaseRef;
    private StorageReference storageRef;

    private ArrayList<String> key_list = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        //권한부여//
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);

        init_variable(root);
        read_once();

        make_room_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("STATE", "LOG in SERVER");
                make_room(v);
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) { //롱클릭시 삭제와 관련된 동작을 수행
                //Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
                final MyItem Item = myAdapter.getItem(position);
                final String user_email = Item.getEmail();
                //Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("방을 삭제하시겠습니까?");
                builder.setPositiveButton("예",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //Toast.makeText(getContext(),"예를 선택했습니다.",Toast.LENGTH_SHORT).show();
                                if(email.equals(user_email)) {
                                    remove_data(Item, position);
                                    Toast.makeText(getContext(), "삭제 되었습니다.", Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(getContext(),"본인이 만든 방만 삭제가 가능합니다.",Toast.LENGTH_SHORT).show();
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

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyItem Item = myAdapter.getItem(position);
                String key = key_list.get(position);
                //Toast.makeText(getContext(), key, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(), DetailHomeActivity.class);
                intent.putExtra("room_class", Item);
                intent.putExtra("room_key", key);
                startActivity(intent);
            }
        });

        return root;
    }

    public void init_variable(View root){
        make_room_button = root.findViewById(R.id.button_makeroom);
        auth = FirebaseAuth.getInstance();
        name = auth.getCurrentUser().getDisplayName();
        email = auth.getCurrentUser().getEmail();
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://projectsoftwareengineeri-184fa.appspot.com");
        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReference();
        mListView = (ListView) root.findViewById(R.id.listView);
        myAdapter = new MyAdapter();
        mListView.setAdapter(myAdapter);
    }

    public void remove_data(MyItem Item, final int position){
        storageRef.child("images").child(Item.getImageName()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //Toast.makeText(getContext(), "이미지 삭제 완료", Toast.LENGTH_SHORT).show();
                databaseRef.child("room").child(key_list.get(position)).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
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
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "이미지 삭제 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void read_once(){ //데이터가 추가되거나 삭제될 때마다 이 메소드를 호출하여 리스트를 업데이트 해준다.

        //addListenerForSingleValueEvent
        databaseRef.child("room").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                key_list.clear();
                myAdapter.clear();
                //Log.d("size", myAdapter.getCount()+"");
                for(final DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if(snapshot.getValue() != null) {
                        final MyItem Item = snapshot.getValue(MyItem.class);
                        StorageReference pathReference = storageRef.child("images/" + Item.getImageName());
                        pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                //Log.d("price", Item.getPrice());
                                //Log.d("key", snapshot.getKey());
                                key_list.add(snapshot.getKey());
                                myAdapter.addItem(uri, Item.getImageName(), Item.getTime(), Item.getPrice(), Item.getAddress(), Item.getName(), Item.getEmail());
                                myAdapter.notifyDataSetChanged();
                                //Toast.makeText(getContext(), "가져오기 성공", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "가져오기 실패", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "에러", Toast.LENGTH_SHORT).show();
            }


        });

    }

    public void make_room(final View v){
        wantToCloseDialog = false;
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog_home, null);

        Button button_select = dialogView.findViewById(R.id.button_select);
        imageView_room = dialogView.findViewById(R.id.imageView_room);
        button_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "!!!!!!!", Toast.LENGTH_SHORT).show();
                selectAlbum();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("방정보 입력하기");
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
                EditText editText_time = dialogView.findViewById(R.id.editText_time);
                EditText editText_price = dialogView.findViewById(R.id.editText_price);
                EditText editText_address = dialogView.findViewById(R.id.editText_address);
                time = editText_time.getText().toString();
                price = editText_price.getText().toString();
                address = editText_address.getText().toString();
                if(check_validation(photoURI, time, price, address)) {
                    dataUpload(photoURI, time ,price, address, name, email);
                    /*Toast.makeText(getContext(), photoURI+"", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), time+" "+price+" "+address+" "+phone, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), myAdapter.getCount()+"", Toast.LENGTH_SHORT).show();*/
                    wantToCloseDialog = true;
                }
                //Do stuff, possibly set wantToCloseDialog to true then...
                if(wantToCloseDialog) {
                    photoURI = null;
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
                    photoURI = null;
                    time="";
                    price="";
                    address="";
                    Toast.makeText(v.getContext(), "취소되었습니다.", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

    }

    public void dataUpload(final Uri uri, final String time, final String price, final String address, final String name, final String email){
        final String path = getPath(uri);
        final Uri file = Uri.fromFile(new File(path));
        final String date = getCurrentDate();
        StorageReference riversRef = storageRef.child("images/" + date + " " + file.getLastPathSegment());
        UploadTask uploadTask = riversRef.putFile(file);

        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Toast.makeText(getContext(), "데이터 업로드 실패", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                MyItem item = new MyItem();
                item.setImageURI(taskSnapshot.getUploadSessionUri().toString());
                item.setImageName(date + " " + file.getLastPathSegment());
                item.setTime(time);
                item.setPrice(price);
                item.setAddress(address);
                item.setName(name);
                item.setEmail(email);
                databaseRef.child("room").push().setValue(item);
                read_once();
                //Toast.makeText(getContext(), "데이터 업로드 성공", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getCurrentDate(){
        TimeZone tz;
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss (z)"); //"yyyy-MM-dd HH:mm:ss (z)"
        tz = TimeZone.getTimeZone("Asia/Seoul"); df.setTimeZone(tz);
        return df.format(date);
    }

    public boolean check_validation(Uri uri, String time, String price, String address){
        if(uri==null){
            Toast.makeText(getContext(), "이미지를 추가하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(time.equals("")){
            Toast.makeText(getContext(), "이용 가능한 시간을 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(price.equals("")){
            Toast.makeText(getContext(), "시간당 가격을 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(address.equals("")){
            Toast.makeText(getContext(), "상세 주소를 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void selectAlbum(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requsetCode, int resultCode, Intent data){
        super.onActivityResult(requsetCode, resultCode, data);
        if(resultCode != RESULT_OK){
            return;
        }
        if(requsetCode==1){
            if(data.getData()!=null){
                try {
                    photoURI = data.getData();
                    //System.out.println(photoURI);
                    //System.out.println(getPath(photoURI));
                    imageView_room.setImageURI(photoURI);
                    //imageView_room.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    imageView_room.setScaleType(ImageView.ScaleType.FIT_XY);
                }catch (Exception e){
                    e.printStackTrace();
                    Log.v("알림", "앨범에서 가져오기 에러");
                }
            }
        }
    }

    public String getPath(Uri uri){
        String [] proj = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(getContext(), uri, proj, null, null, null);

        Cursor cursor = cursorLoader.loadInBackground();
        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        return cursor.getString(index);
    }

}