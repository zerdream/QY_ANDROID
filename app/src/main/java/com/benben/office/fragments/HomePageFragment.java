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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.benben.office.R;
import com.benben.office.activities.SecondActivity;
import com.benben.office.adapter.HomeAdapter;
import com.benben.office.entities.HomeEntity;
import com.benben.office.tools.ToastUtils;
import com.benben.office.tools.Url;
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

public class HomePageFragment extends Fragment implements View.OnClickListener {

    private ListView listView ;
    private HomeAdapter adapter ;
    private TextView title ;

    private ImageView imageView ;
    private LinearLayout headLinearLayout ;
    private TextView text ;

    //无参构造
    public HomePageFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
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

        //加载数据
        loadData() ;
    }

    private void initListeners() {
    }

    private void initData() {
        title.setText(R.string.home );

        headLinearLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.home_head , null ) ;
        imageView = (ImageView) headLinearLayout.findViewById(R.id.imageView);
        text = (TextView) headLinearLayout.findViewById(R.id.text);
    }

    private void initViews(View v) {
        title = (TextView) v.findViewById(R.id.title ) ;
        listView = (ListView) v.findViewById(R.id.listView ) ;
        //imageView = (ImageView )v.findViewById(R.id.imageView ) ;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void loadData() {
        String urls = Url.getMes ;
        HttpUtils httpUtils = new HttpUtils() ;
        RequestParams params = new RequestParams() ;
        httpUtils.send(HttpRequest.HttpMethod.POST, urls, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.e("getMes" , responseInfo.result ) ;
                JSONObject object = JSON.parseObject(responseInfo.result);
                String status_code = object.getString("status_code");
                switch (status_code){
                    case "200" :
                        JSONArray data = object.getJSONArray("data");
                        final List<HomeEntity> list = JSON.parseArray(data.toJSONString(), HomeEntity.class);
                        listView.addHeaderView(headLinearLayout);
                        adapter = new HomeAdapter(getActivity() , list ) ;
                        listView.setAdapter(adapter);
                        adapter.notifyDataSetInvalidated();
                        BitmapUtils bitmapUtils = new BitmapUtils(getActivity()) ;
                        JSONObject picture = object.getJSONObject("picture");
                        String picture1 = picture.getString("picture");
                        String companyName = picture.getString("companyName");
                        text.setText(companyName);
                        bitmapUtils.display(imageView , picture1 );

                        //添加点击事件
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                if (position!=0){
                                    Intent intent = new Intent(getActivity() , SecondActivity.class ) ;
                                    intent.putExtra("systemId" , list.get(position-1).getSystemId() ) ;
                                    startActivity(intent);
                                }

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
            default:
                break;
        }
    }

}
