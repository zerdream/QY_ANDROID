package com.benben.office.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.benben.office.R;
import com.benben.office.entities.SecondEntity;

import java.util.Collection;
import java.util.List;

/**
 * Created by 牛海丰 on 2017/5/25.
 */

public class SecondAdapter extends BaseAdapter {

    private Context context ;
    private List<SecondEntity> list ;
    private int index ;

    public SecondAdapter(){}

    public SecondAdapter(Context context , List<SecondEntity> list  , int index ){
        super();
        this.context = context;
        this.list = list;
        this.index = index ;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.selected_items, parent , false);
            holder = new ViewHolder() ;
            holder.text01 = (TextView) convertView.findViewById(R.id.text01);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();

        }
        holder.text01.setText(list.get(index).getChild().get(position).getSystemName());
        return convertView;
    }

    static class  ViewHolder{
        TextView text01 ;
    }

    public void addAll(Collection<? extends SecondEntity> collection) {

        list.addAll(collection);
        notifyDataSetChanged();

    }
    public void clear(){

        list.clear();
        notifyDataSetChanged();
    }
}

