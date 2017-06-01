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

public class ChatAttenceAdapter extends BaseAdapter {

    private Context context ;
    private List<ChatEntity> list ;
    final int TYPE_01 = 1;
    final int TYPE_02 = 2;
    final int TYPE_03 = 3;
    final int TYPE_COUNT = 20;
    public ChatAttenceAdapter(){}


    public ChatAttenceAdapter(Context context , List<ChatEntity> list ){
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {

        if ("0001".equals(list.get(position).getFunctionName())) {
            return TYPE_01;// 消费类型
        } else if ("0002".equals(list.get(position).getFunctionName())) {
            return TYPE_02;// 充值类型
        } else {
            return TYPE_03;
        }
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
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

        int type = getItemViewType(position);
        ViewHolder01 holder01=null ;
        ViewHolder02 holder02=null ;

        if(convertView == null ){
            //region 按当前所需的样式，确定new的布局
            switch(type){
                case TYPE_01:
                    convertView = LayoutInflater.from(context).inflate(R.layout.chat_item_word, parent , false);
                    holder01 = new ViewHolder01() ;
                    holder01.title_text = (TextView) convertView.findViewById(R.id.chat_item);
                    convertView.setTag(holder01);
                    break;
                case TYPE_02:
                    convertView = LayoutInflater.from(context).inflate(R.layout.chat_item_image, parent , false);
                    holder02 = new ViewHolder02() ;
                    holder02.title_text = (TextView) convertView.findViewById(R.id.chat_item);
                    convertView.setTag(holder02);
                    break;
                case TYPE_03:
                    break;
            }
            //endregion
        }else {
            //region 有convertView，按样式，取得不用的布局
            switch(type) {
                case TYPE_01:
                    holder01 = (ViewHolder01) convertView.getTag();
                    break;
                case TYPE_02:
                    holder02 = (ViewHolder02) convertView.getTag();
                    break;
            }
            //endregion
        }
        //region 设置资源
        switch(type) {
            case TYPE_01:
                holder01.title_text.setText(list.get(position).getFunctionName());
                break;
            case TYPE_02:
                holder02.title_text.setText(list.get(position).getFunctionName());
                break;
        }
        //endregion

        return convertView;
    }

    //region 各个布局的控件资源
    static class  ViewHolder01{
        TextView title_text ;
    }

    //各个布局的控件资源
    static class  ViewHolder02{
        TextView title_text ;
    }
    //endregion

    public void addAll(Collection<? extends ChatEntity> collection) {
        list.addAll(collection);
        notifyDataSetChanged();
    }
    public void clear(){
        list.clear();
        notifyDataSetChanged();
    }
}
