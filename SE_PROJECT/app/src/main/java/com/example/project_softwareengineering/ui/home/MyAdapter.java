package com.example.project_softwareengineering.ui.home;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.project_softwareengineering.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private ArrayList<MyItem> mItems = new ArrayList<>();

    @Override
    public int getCount(){
        return mItems.size();
    }

    @Override
    public MyItem getItem(int position){
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_listview_home, null, false);
        }

        ImageView imageView_room = (ImageView) convertView.findViewById(R.id.imageView_room);
        TextView textView_time = (TextView) convertView.findViewById(R.id.textView_time);
        TextView textView_price = (TextView) convertView.findViewById(R.id.textView_price);
        TextView textView_address = (TextView) convertView.findViewById(R.id.textView_address);
        TextView textView_name = (TextView) convertView.findViewById(R.id.textView_name);

        MyItem myItem = getItem(position);
        //imageView_room.setImageURI(Uri.parse(myItem.getImageURI()));
        Glide.with(parent).load(myItem.getImageURI()).into(imageView_room);
        textView_time.setText("시간 : " + myItem.getTime());
        textView_price.setText("가격 : " + myItem.getPrice());
        textView_address.setText("주소 : " + myItem.getAddress());
        textView_name.setText(myItem.getName()+"님의 방입니다.");

        return convertView;
    }

    public void addItem(Uri uri, String ImageName, String time, String price, String address, String name, String email){
        MyItem mItem = new MyItem();
        mItem.setImageURI(uri.toString());
        mItem.setImageName(ImageName);
        mItem.setTime(time);
        mItem.setPrice(price);
        mItem.setAddress(address);
        mItem.setName(name);
        mItem.setEmail(email);
        mItems.add(mItem);
    }

    public void clear(){
        mItems.clear();
    }
}
