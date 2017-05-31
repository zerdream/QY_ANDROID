package com.benben.office.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.benben.office.R;
import com.benben.office.entities.ChatEntity;

import java.util.Collection;
import java.util.List;

/**
 * Created by 牛海丰 on 2017/5/24.
 */

public class ChatAdapter extends BaseAdapter {

    private Context context ;
    private List<ChatEntity> list ;

    public ChatAdapter(){}

    public ChatAdapter(Context context , List<ChatEntity> list ){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.chat_item, parent , false);
            holder = new ViewHolder() ;
            holder.title_text = (TextView) convertView.findViewById(R.id.chat_item);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title_text.setText(list.get(position).getFunctionName());

        return convertView;
    }

    static class  ViewHolder{
        TextView title_text ;
    }

    public void addAll(Collection<? extends ChatEntity> collection) {

        list.addAll(collection);
        notifyDataSetChanged();

    }
    public void clear(){

        list.clear();
        notifyDataSetChanged();
    }
}
