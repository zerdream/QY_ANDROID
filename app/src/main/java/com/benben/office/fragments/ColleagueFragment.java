package com.benben.office.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.benben.office.R;
import com.benben.office.adapter.ColleagueAdapter;
import com.benben.office.entities.ColleagueEntity;
import com.benben.office.tools.ToastUtils;
import com.benben.office.tools.Url;
import com.benben.office.views.RoundAngleImageView;
import com.jauker.widget.BadgeView;
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

public class ColleagueFragment extends Fragment implements View.OnClickListener {

    private TextView title ;
    private ImageView set ;

    private ListView listView ;
    private ColleagueAdapter adapter ;

    private FrameLayout frameLayout ;
    //private TextView title_text ;
    //private TextView des_text ;
    //private TextView time ;
    private RelativeLayout relative ;
    private BadgeView badgeView ;

    //private ImageView imageView ;
    //无参构造
    public ColleagueFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_colleague, container, false);
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

        // TODO: 2017/5/23  添加假数据 需要删除
        loadFalseData() ;
    }

    private void initListeners() {
        set.setOnClickListener(this);
    }

    private void initData() {
        title.setText(R.string.colleague );
        set.setImageResource(R.mipmap.settings);
    }

    private void initViews(View v) {
        title = (TextView) v.findViewById(R.id.title ) ;
        set = (ImageView) v.findViewById(R.id.set ) ;
        listView = (ListView)v.findViewById(R.id.listView ) ;


        relative = (RelativeLayout) v.findViewById(R.id.relative ) ;
        frameLayout = (FrameLayout)v.findViewById(R.id.frameLayout ) ;
        //title_text = (TextView)v.findViewById(R.id.title_text ) ;
        //des_text = (TextView)v.findViewById(R.id.des_text ) ;
        //time = (TextView)v.findViewById(R.id.time ) ;
        //imageView = (ImageView) v.findViewById(R.id.imageView ) ;

    }

    @Override
    public void onResume() {
        super.onResume();


        //加载数据
        loadData() ;
    }

    private void loadFalseData() {
//        title_text.setText("小助手");
//        des_text.setText("不以颜值惊天下，必以技术动世人。不以颜值惊天下，必以技术动世人。");
//        time.setText("下午：12:13");

//        badgeView = new BadgeView(getActivity());
//        badgeView.setTargetView(imageView);
//        badgeView.setBackground(10, Color.parseColor("#ff0000"));
//        badgeView.setText("10");
//
//        imageView.setImageResource(R.mipmap.icon_relation);


        RelativeLayout headRelativeLayout = (RelativeLayout) LayoutInflater.from(getActivity()).inflate(R.layout.colleague_head , null ) ;
        TextView title_text01 = (TextView) headRelativeLayout.findViewById(R.id.title_text);
        TextView des_text01 = (TextView) headRelativeLayout.findViewById(R.id.des_text);
        TextView time01= (TextView) headRelativeLayout.findViewById(R.id.time);
        RoundAngleImageView imageView= (RoundAngleImageView) headRelativeLayout.findViewById(R.id.imageView);

        title_text01.setText("小助手");
        des_text01.setText("不以颜值惊天下，必以技术动世人。不以颜值惊天下，必以技术动世人。");
        time01.setText("下午：12:13");

        badgeView = new BadgeView(getActivity());
        badgeView.setTargetView(imageView);
        badgeView.setBackground(10, Color.parseColor("#ff0000"));
        badgeView.setText("10");

        imageView.setImageResource(R.mipmap.icon_relation);

        listView.addHeaderView(headRelativeLayout);


    }

    private void loadData() {
        String urls = Url.colleague ;
        HttpUtils httpUtils = new HttpUtils() ;
        RequestParams params = new RequestParams() ;
        httpUtils.send(HttpRequest.HttpMethod.POST, urls, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.e("colleague" , responseInfo.result ) ;
                JSONObject object = JSON.parseObject(responseInfo.result);
                String status_code = object.getString("status_code");
                switch (status_code){
                    case "200" :
                        JSONArray data = object.getJSONArray("data");
                        List<ColleagueEntity> list = JSON.parseArray(data.toJSONString(), ColleagueEntity.class);
                        adapter = new ColleagueAdapter(getActivity() , list ) ;
                        listView.setAdapter(adapter);
                        adapter.notifyDataSetInvalidated();
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
