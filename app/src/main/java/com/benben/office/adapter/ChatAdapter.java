package com.benben.office.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.benben.office.R;
import com.benben.office.entities.ChatEntity;
import com.benben.office.tools.ToastUtils;
import com.benben.office.tools.Url;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.Collection;
import java.util.List;

/**
 * Created by 牛海丰 on 2017/5/24.
 */

public class ChatAdapter extends BaseAdapter {

    private Context context ;
    private List<ChatEntity> list ;
    private String orderCount ;
    private String urls ;
    private final int TYPE_01 = 1;
    private final int TYPE_02 = 2;
    private final int TYPE_03 = 3;
    private int TYPE_COUNT = 3;

    public ChatAdapter(){}

    public ChatAdapter(Context context , List<ChatEntity> list , String orderCount , String urls){
        super();
        this.context = context;
        this.list = list;
        this.orderCount = orderCount ;
        this.urls = urls ;
    }

    @Override
    public int getItemViewType(int position) {
        if ("1".equals(list.get(position).getTypeId())) {
            return TYPE_01;
        } else if ("2".equals(list.get(position).getTypeId())) {
            return TYPE_02;
        }
        return TYPE_03 ;
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
            switch(type){
                case TYPE_01:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item01_text02_btn00, parent , false);
                    holder01 = new ViewHolder01() ;
                    holder01.time = (TextView) convertView.findViewById(R.id.time);
                    holder01.title = (TextView) convertView.findViewById(R.id.title);
                    holder01.message = (TextView) convertView.findViewById(R.id.message);
                    convertView.setTag(holder01);
                    break;
                case TYPE_02:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item02_text02_btn01, parent , false);
                    holder02 = new ViewHolder02() ;
                    holder02.time = (TextView) convertView.findViewById(R.id.time);
                    holder02.title = (TextView) convertView.findViewById(R.id.title);
                    holder02.message = (TextView) convertView.findViewById(R.id.message);
                    holder02.imageView = (ImageView) convertView.findViewById(R.id.imageView);
                    convertView.setTag(holder02);
                    break;
                case TYPE_03:
                    break;
            }
        }else {
            switch(type) {
                case TYPE_01:
                    holder01 = (ViewHolder01) convertView.getTag();
                    break;
                case TYPE_02:
                    holder02 = (ViewHolder02) convertView.getTag();
                    break;
            }
        }

        switch(type) {
            case TYPE_01:
                holder01.time.setText(list.get(position).getUpdateTime());
                holder01.title.setText(list.get(position).getTitle());
                holder01.message.setText(list.get(position).getMessage());
                break;
            case TYPE_02:
                holder02.time.setText(list.get(position).getUpdateTime());
                holder02.title.setText(list.get(position).getTitle());
                holder02.message.setText(list.get(position).getMessage());
                holder02.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (orderCount){
                            case "1" :
                                //ToastUtils.shortToast(context , urls + "/001" );
                                loadData(urls + "/001" , position ) ;
                                break;
                            default:
                        }
                    }
                });
                break;
        }

        return convertView;
    }

    private void loadData(String urls , final int position ) {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        httpUtils.send(HttpRequest.HttpMethod.POST, urls, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.e("chatOrderData", responseInfo.result);
                JSONObject object = JSON.parseObject(responseInfo.result);
                JSONObject datas = object.getJSONObject("datas");
                String status_code = datas.getString("status_code");
                switch (status_code) {
                    case "200":
                        String Title = datas.getString("Title");
                        String message = datas.getString("message");
                        String typeId = datas.getString("typeId");
                        list.get(position).setTitle(Title );
                        list.get(position).setMessage(message );
                        list.get(position).setTypeId(typeId );
                        notifyDataSetChanged();
                        break;
                    default:
                        break;

                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                error.printStackTrace();
                ToastUtils.shortToast(context, "网络连接异常");
            }
        });
    }

    static class  ViewHolder01{
        TextView time ;
        TextView title ;
        TextView message ;
    }

    static class  ViewHolder02{
        TextView time ;
        TextView title ;
        TextView message ;
        ImageView imageView ;
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
