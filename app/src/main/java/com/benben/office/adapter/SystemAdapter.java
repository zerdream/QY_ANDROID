package com.benben.office.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.benben.office.R;
import com.benben.office.entities.SystemEntity;
import com.benben.office.views.MyIconImageView;
import com.jauker.widget.BadgeView;

import java.util.Collection;
import java.util.List;

/**
 * Created by 牛海丰 on 2017/5/23.
 */

public class SystemAdapter extends BaseAdapter {

    private Context context ;
    private List<SystemEntity> list ;

    public SystemAdapter(){}

    public SystemAdapter(Context context , List<SystemEntity> list ){
        super();
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder ;
        if(convertView == null ){
            convertView = LayoutInflater.from(context).inflate(R.layout.system_items, parent , false);
            holder = new ViewHolder() ;
            holder.linear = (LinearLayout) convertView.findViewById(R.id.linear ) ;
            holder.frameLayout = (FrameLayout) convertView.findViewById(R.id.frameLayout ) ;
            holder.system_image = (MyIconImageView) convertView.findViewById(R.id.system_image ) ;
            holder.title = (TextView) convertView.findViewById(R.id.title ) ;
            holder.des = (TextView) convertView.findViewById(R.id.des ) ;
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(list.get(position).getSystemName());
        holder.des.setText(list.get(position).getLabel());

//        BadgeView badgeView = new BadgeView(context);
//        badgeView.setTargetView(holder.frameLayout);
//        badgeView.setBackground(8, Color.parseColor("#ff0000"));
//        badgeView.setText("2");

        switch (list.get(position).getSystemId()){
            case "1" :
                holder.system_image.setImageResource(R.mipmap.icon_kaoqin);
                break;
            case "2" :
                holder.system_image.setImageResource(R.mipmap.icon_renli);
                break;
            case "3" :
                holder.system_image.setImageResource(R.mipmap.icon_caiwu);
                break;
            case "4" :
                holder.system_image.setImageResource(R.mipmap.icon_email);
                break;
            case "5" :
                holder.system_image.setImageResource(R.mipmap.icon_meet);
                break;
            case "6" :
                holder.system_image.setImageResource(R.mipmap.icon_project);
                break;
            case "7" :
                holder.system_image.setImageResource(R.mipmap.icon_relation);
                break;
            case "8" :
                holder.system_image.setImageResource(R.mipmap.icon_work);
                break;
            case "9" :
                holder.system_image.setImageResource(R.mipmap.icon_know);
                break;
            case "10" :
                holder.system_image.setImageResource(R.mipmap.icon_company);
                break;
            case "11" :
                holder.system_image.setImageResource(R.mipmap.icon_store);
                break;
            case "12" :
                holder.system_image.setImageResource(R.mipmap.icon_storage);
                break;
            default:
                holder.system_image.setVisibility(View.GONE);
                break;
        }
        return convertView;
    }

    static class  ViewHolder{
        LinearLayout linear ;
        MyIconImageView system_image ;
        TextView title ;
        TextView des ;
        FrameLayout frameLayout ;
    }

    public void addAll(Collection<? extends SystemEntity> collection) {

        list.addAll(collection);
        notifyDataSetChanged();

    }
    public void clear(){

        list.clear();
        notifyDataSetChanged();
    }
}

