package com.benben.office.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.benben.office.R;
import com.benben.office.entities.MyEntity;
import com.benben.office.tools.LoadImageAsyncTask;
import com.benben.office.tools.ToastUtils;
import com.benben.office.tools.Url;
import com.benben.office.views.MyIconImageView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

/**
 * Created by 牛海丰 on 2017/5/22.
 */

public class MyFragment extends Fragment implements View.OnClickListener {

    private TextView title ;
    private ImageView set ;

    private MyIconImageView user_pic ;
    private TextView name ;
    private TextView nickName ;
    private TextView companyName ;
    private ImageView pic_code ;

    private TextView address ;
    private TextView email ;
    private TextView phoneNumber ;
    private TextView department ;

    //无参构造
    public MyFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //实例化组件
        initViews(view) ;
        //实例化数据
        initData() ;
        //注册监听器
        initListeners() ;
    }

    private void initListeners() {
        set.setOnClickListener(this);
    }

    private void initData() {
        title.setText(R.string.me );
        set.setImageResource(R.mipmap.settings);
    }

    private void initViews(View v ) {
        title = (TextView) v.findViewById(R.id.title ) ;
        set = (ImageView) v.findViewById(R.id.set ) ;

        user_pic = (MyIconImageView)v.findViewById(R.id.user_pic ) ;
        name = (TextView) v.findViewById(R.id.name ) ;
        nickName = (TextView) v.findViewById(R.id.nickName ) ;
        companyName = (TextView) v.findViewById(R.id.companyName ) ;

        address = (TextView) v.findViewById(R.id.address ) ;
        email = (TextView) v.findViewById(R.id.email ) ;
        phoneNumber = (TextView) v.findViewById(R.id.phoneNumber ) ;
        department = (TextView) v.findViewById(R.id.department ) ;
        pic_code = (ImageView) v.findViewById(R.id.pic_code ) ;
    }

    @Override
    public void onResume() {
        super.onResume();
        //加载数据
        loadData() ;
    }

    private void loadData() {
        String urls = Url.auth_me ;
        HttpUtils httpUtils = new HttpUtils() ;
        RequestParams params = new RequestParams() ;
        httpUtils.send(HttpRequest.HttpMethod.POST, urls, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.e("auth_me" , responseInfo.result ) ;
                JSONObject object = JSON.parseObject(responseInfo.result);
                String status_code = object.getString("status_code");
                switch (status_code){
                    case "200" :
                        JSONArray data = object.getJSONArray("data");
                        List<MyEntity> list = JSON.parseArray(data.toJSONString(), MyEntity.class);
                        //加载头像
                        new LoadImageAsyncTask(new LoadImageAsyncTask.CallBack() {
                            @Override
                            public void setData(final Bitmap bitmap) {
                                user_pic.setImageBitmap(bitmap);
                            }
                        }).execute(list.get(0).getUserPic());
                        name.setText(list.get(0).getName());
                        nickName.setText(list.get(0).getNickName());
                        companyName.setText(list.get(0).getCompanyName());
                        address.setText("地址：" + list.get(0).getAddress());
                        email.setText("邮箱：" + list.get(0).getEmail());
                        phoneNumber.setText("联系方式：" + list.get(0).getPhoneNumber());
                        department.setText("部门：" + list.get(0).getDepartment());
                        BitmapUtils bitmapUtils = new BitmapUtils(getActivity()) ;
                        bitmapUtils.display(pic_code , list.get(0).getPicCode());
                        break;
                    default:
                        break;

                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                error.printStackTrace();
                ToastUtils.shortToast(getActivity() , "网络连接异常");
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.set :
                ToastUtils.shortToast(getActivity() , "设置") ;
                break;
            default:
                break;
        }
    }

}
