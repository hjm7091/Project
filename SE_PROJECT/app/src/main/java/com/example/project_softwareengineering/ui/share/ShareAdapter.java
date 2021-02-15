package com.example.project_softwareengineering.ui.share;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.project_softwareengineering.R;
import com.example.project_softwareengineering.ui.home.MyItem;

import java.util.ArrayList;
import java.util.Map;

public class ShareAdapter extends BaseAdapter {

    private ArrayList<ShareItem> sItems = new ArrayList<>();

    @Override
    public int getCount(){
        return sItems.size();
    }

    @Override
    public ShareItem getItem(int position){
        return sItems.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_listview_share, null, false);
        }

        TextView textView_title = (TextView) convertView.findViewById(R.id.textView_title);
        TextView textView_content = (TextView) convertView.findViewById(R.id.textView_content);
        TextView textView_name = (TextView) convertView.findViewById(R.id.textView_name);
        ImageView imageView_thumb = (ImageView) convertView.findViewById(R.id.imageView_thumb);
        TextView textView_like = (TextView) convertView.findViewById(R.id.textView_like);

        ShareItem sItem = getItem(position);
        textView_title.setText(sItem.getTitle());
        textView_content.setText(sItem.getContent());
        textView_name.setText(sItem.getDate()+" | " + sItem.getName());
        imageView_thumb.setImageResource(R.drawable.thumb_up);
        textView_like.setText(sItem.getLike()+"");

        return convertView;
    }

    public void addItem(String title, String content, String name, String email, String date, int like, Map<String, Boolean> map){
        ShareItem sItem = new ShareItem();
        sItem.setTitle(title);
        sItem.setContent(content);
        sItem.setName(name);
        sItem.setEmail(email);
        sItem.setDate(date);
        sItem.setLike(like);
        sItem.map = map;
        sItems.add(sItem);
    }

    public void clear(){
        sItems.clear();
    }
}
