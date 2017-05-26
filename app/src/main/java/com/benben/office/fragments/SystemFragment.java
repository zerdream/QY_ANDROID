package com.benben.office.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.benben.office.R;
import com.benben.office.activities.SecondActivity;
import com.benben.office.adapter.ColleagueAdapter;
import com.benben.office.adapter.SystemAdapter;
import com.benben.office.entities.ColleagueEntity;
import com.benben.office.entities.SystemEntity;
import com.benben.office.tools.ToastUtils;
import com.benben.office.tools.Url;
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
public class SystemFragment extends Fragment implements View.OnClickListener {

    private TextView title ;
    private ImageView set ;

    private GridView gridView ;
    private SystemAdapter adapter ;

    private ImageView quit_image ;
    private FrameLayout foot  ;

    //无参构造
    public SystemFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_system, container, false);
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
        quit_image.setOnClickListener(this);
    }

    private void initData() {
        title.setText(R.string.system );
        set.setImageResource(R.mipmap.settings);
    }

    private void initViews(View v) {
        title = (TextView) v.findViewById(R.id.title ) ;
        set = (ImageView) v.findViewById(R.id.set ) ;
        gridView = (GridView) v.findViewById(R.id.gridView ) ;

        quit_image = (ImageView) v.findViewById(R.id.quit_image ) ;
        foot = (FrameLayout) v.findViewById(R.id.foot ) ;

    }

    @Override
    public void onResume() {
        super.onResume();

        //加载数据
        loadData() ;
    }

    private void loadData() {
        String urls = Url.system ;
        HttpUtils httpUtils = new HttpUtils() ;
        RequestParams params = new RequestParams() ;
        httpUtils.send(HttpRequest.HttpMethod.POST, urls, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.e("system" , responseInfo.result ) ;
                JSONObject object = JSON.parseObject(responseInfo.result);
                String status_code = object.getString("status_code");
                switch (status_code){
                    case "200" :
                        JSONArray data = object.getJSONArray("data");
                        final List<SystemEntity> list = JSON.parseArray(data.toJSONString(), SystemEntity.class);
                        adapter = new SystemAdapter(getActivity() , list ) ;
                        gridView.setAdapter(adapter);
                        adapter.notifyDataSetInvalidated();
                        //添加点击事件
                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getActivity() , SecondActivity.class ) ;
                                intent.putExtra("systemId" , list.get(position).getSystemId() ) ;
                                startActivity(intent);
                            }
                        });
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
            case R.id.quit_image :
                foot.setVisibility(View.GONE);
                break;

            default:
                break;
        }
    }
}
