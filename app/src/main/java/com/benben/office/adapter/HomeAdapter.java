package com.benben.office.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.benben.office.R;
import com.benben.office.entities.HomeEntity;
import com.benben.office.views.RoundAngleImageView;
import com.lidroid.xutils.BitmapUtils;

import java.util.Collection;
import java.util.List;

/**
 * Created by 牛海丰 on 2017/5/24.
 */

public class HomeAdapter extends BaseAdapter {

    private Context context ;
    private List<HomeEntity> list ;

    public HomeAdapter(){}

    public HomeAdapter(Context context , List<HomeEntity> list ){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.home_items, parent , false);
            holder = new ViewHolder() ;
            holder.title_text = (TextView) convertView.findViewById(R.id.title_text);
            holder.des_text = (TextView) convertView.findViewById(R.id.des_text);
            holder.time= (TextView) convertView.findViewById(R.id.time);
            holder.imageView= (RoundAngleImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title_text.setText(list.get(position).getSystemName());
        holder.des_text.setText(list.get(position).getTitle());
        holder.time.setText(list.get(position).getType() + "：" + list.get(position).getTime());
        //    badgeView = new BadgeView(context);
        //    badgeView.setTargetView(holder.imageView);
        //    badgeView.setBackground(10, Color.parseColor("#ff0000"));
        //    badgeView.setText("10");

        BitmapUtils bitmapUtils = new BitmapUtils(context ) ;
        bitmapUtils.display(holder.imageView , list.get(position).getUrl() );

        return convertView;
    }


    static class  ViewHolder{
        RoundAngleImageView imageView ;
        TextView title_text ;
        TextView des_text ;
        TextView time ;
    }

    public void addAll(Collection<? extends HomeEntity> collection) {

        list.addAll(collection);
        notifyDataSetChanged();

    }
    public void clear(){

        list.clear();
        notifyDataSetChanged();
    }
}
