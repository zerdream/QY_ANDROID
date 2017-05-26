package com.benben.office.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.benben.office.R;
import com.benben.office.entities.ColleagueEntity;

import java.util.Collection;
import java.util.List;

/**
 * Created by 牛海丰 on 2017/5/23.
 */

public class ColleagueAdapter extends BaseAdapter {

    private Context context ;
    private List<ColleagueEntity> list ;

    public ColleagueAdapter(){}

    public ColleagueAdapter(Context context , List<ColleagueEntity> list ){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.colleague_items, parent , false);
            holder = new ViewHolder() ;
            holder.department = (TextView)convertView.findViewById(R.id.department ) ;
            holder.account = (TextView)convertView.findViewById(R.id.account ) ;
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.department.setText(list.get(position).getDepartment());
        holder.account.setText(list.get(position).getAccount() + "人");

        return convertView;
    }

static class  ViewHolder{
    TextView department ;
    TextView account ;
}

    public void addAll(Collection<? extends ColleagueEntity> collection) {

        list.addAll(collection);
        notifyDataSetChanged();

    }
    public void clear(){

        list.clear();
        notifyDataSetChanged();
    }
}
