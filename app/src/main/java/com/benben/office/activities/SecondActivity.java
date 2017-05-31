package com.benben.office.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.benben.office.R;
import com.benben.office.adapter.ChatAdapter;
import com.benben.office.adapter.SecondAdapter;
import com.benben.office.entities.ChatEntity;
import com.benben.office.entities.SecondEntity;
import com.benben.office.entities.SystemEntity;
import com.benben.office.tools.ToastUtils;
import com.benben.office.tools.Url;
import com.benben.office.tools.benben;
import com.benben.office.views.WheelView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

public class SecondActivity extends BenBenActivity implements View.OnClickListener{

    private RelativeLayout pre ;
    private TextView title ;
    private ImageView set ;
    private LinearLayout foot ;

    private RelativeLayout mid ;

    private RelativeLayout relative_one , relative_two , relative_three ;
    private TextView text_one , text_two , text_three ;
    private ListView chatList ;
    private ChatAdapter chatAdapter ;
    private TextView chatItem ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData() ;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_second);
    }

    @Override
    public void initViews() {
        pre = (RelativeLayout)findViewById(R.id.pre ) ;
        title = (TextView)findViewById(R.id.title ) ;
        set = (ImageView)findViewById(R.id.set ) ;

        relative_one = (RelativeLayout) findViewById(R.id.relative_one ) ;
        relative_two = (RelativeLayout) findViewById(R.id.relative_two ) ;
        relative_three = (RelativeLayout) findViewById(R.id.relative_three ) ;
        mid = (RelativeLayout) findViewById(R.id.mid ) ;

        text_one = (TextView) findViewById(R.id.text_one ) ;
        text_two = (TextView) findViewById(R.id.text_two ) ;
        text_three = (TextView) findViewById(R.id.text_three ) ;
        foot = (LinearLayout) findViewById(R.id.foot ) ;
        chatList=(ListView) findViewById(R.id.chat_list);

    }

    @Override
    public void initListeners() {
        pre.setOnClickListener(this);
        title.setOnClickListener(this);
        set.setOnClickListener(this);

    }

    @Override
    public void initData() {
        Intent intent = getIntent() ;
        String systemId = intent.getStringExtra("systemId");
        switch (systemId){
            case "1" :
                title.setText("考勤");
                break;
            case "2" :
                title.setText("人力");
                break;
            case "3" :
                title.setText("财务");
                break;
            case "4" :
                title.setText("邮件");
                break;
            case "5" :
                title.setText("会议");
                break;
            case "6" :
                title.setText("项目管理");
                break;
            case "7" :
                title.setText("客户关系");
                break;
            case "8" :
                title.setText("工作管理");
                break;
            case "9" :
                title.setText("知识系统");
                break;
            case "10" :
                title.setText("企业文化");
                break;
            case "11" :
                title.setText("进销存");
                break;
            case "12" :
                title.setText("企业驾驶舱");
                break;
            case "13" :
                title.setText("小助手");
                break;
            default:
                title.setText("数据异常");
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pre :
                finish();
                break;
            case R.id.title :
                loadSystem();
                break;
            case R.id.set :
                ToastUtils.shortToast(SecondActivity.this , "设置");
                break;
            default:
                break;
        }
    }

    //region loadData
    private void loadData() {
        String urls = Url.getOrder ;
        HttpUtils httpUtils = new HttpUtils() ;
        RequestParams params = new RequestParams() ;
        httpUtils.send(HttpRequest.HttpMethod.POST, urls, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.e("getOrder" , responseInfo.result ) ;
                JSONObject object = JSON.parseObject(responseInfo.result);
                String status_code = object.getString("status_code");
                switch (status_code){
                    case "200" :
                        JSONArray data = object.getJSONArray("data");
                        final List<SecondEntity> list = JSON.parseArray(data.toJSONString(), SecondEntity.class);
                        text_one.setText(list.get(0).getSystemName());
                        text_two.setText(list.get(1).getSystemName());
                        text_three.setText(list.get(2).getSystemName());
                        relative_one.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popListView(list , 0 ) ;
                            }
                        });
                        relative_two.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popListView(list , 1 ) ;
                            }
                        });
                        relative_three.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popListView(list , 2 ) ;
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
                ToastUtils.shortToast(SecondActivity.this , "网络连接异常");
            }
        });
    }
    //endregion

    //region popListView 弹出功能菜单
    /*
     * 方法名：popListView
     * 功能：  弹出功能菜单
     * 参数：  List<SecondEntity>
     *        int
     * 返回值：无
     */
    private void popListView(final List<SecondEntity> list , final int index ) {
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.pop_listview, null);
        final PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        SecondAdapter adapter = new SecondAdapter(SecondActivity.this , list  , index ) ;

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //测试开始
                final List<ChatEntity> listChat = JSON.parseArray("[{\"functionName\": \"0001\"}]", ChatEntity.class);
                ChatEntity ae=new ChatEntity();
                ae.setFunctionName("0002");
                listChat.add(ae);
                listChat.add(ae);
                listChat.add(ae);
                listChat.add(ae);
                listChat.add(ae);
                listChat.add(ae);
                listChat.add(ae);
                listChat.add(ae);
                listChat.add(ae);
                listChat.add(ae);
                listChat.add(ae);
                listChat.add(ae);
                chatAdapter = new ChatAdapter(SecondActivity.this , listChat ) ;
                chatList.setAdapter(chatAdapter);
                chatAdapter.notifyDataSetInvalidated();
                //测试结束

                ToastUtils.shortToast(SecondActivity.this , list.get(index).getChild().get(position).getUrl() );
                window.dismiss();

            }
        });
        window.setFocusable(true);
        //点击空白的地方关闭PopupWindow
        window.setBackgroundDrawable(new BitmapDrawable());
        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        switch (index){
            case 0 :
                window.showAtLocation(mid, Gravity.BOTTOM | Gravity.LEFT, 0, (foot.getHeight() + benben.getNavigationBarSize(SecondActivity.this).y));
                break;
            case 1 :
                window.showAtLocation(mid, Gravity.BOTTOM | Gravity.CENTER, 0, (foot.getHeight() + benben.getNavigationBarSize(SecondActivity.this).y));
                break;
            case 2 :
                window.showAtLocation(mid, Gravity.BOTTOM | Gravity.RIGHT, 0, (foot.getHeight() + benben.getNavigationBarSize(SecondActivity.this).y));
                break;
            default:
                break;
        }

        //window.showAsDropDown(set);
        backgroundAlpha(0.8f);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
    }
    //endregion

    //region loadSystem 切换系统
    /*
     * 方法名：loadSystem
     * 功能：  切换系统
     * 参数：  无
     * 返回值：无
     */
    private void loadSystem() {
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
                        popUpWindow(list) ;
                        break;
                    default:
                        break;

                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                error.printStackTrace();
                ToastUtils.shortToast(SecondActivity.this , "网络连接异常");
            }
        });
    }
    //endregion

    //region popUpWindow 弹出系统选择组件
    /*
     * 方法名：popUpWindow(List<SystemEntity> list)
     * 功能：  弹出系统选择组件
     * 参数：  List<SystemEntity>
     * 返回值：无
     */
    private void popUpWindow(List<SystemEntity> list) {

        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.common_window_wheel, null);
        final PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        final WheelView picker= (WheelView) view.findViewById(R.id.wheel);

        for (int i = 0; i < list.size(); i++) {
            picker.addData(list.get(i).getSystemName());
        }

        TextView left = (TextView) view.findViewById(R.id.left);
        TextView right = (TextView) view.findViewById(R.id.right);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object centerItem = picker.getCenterItem();
                title.setText(centerItem.toString());
                window.dismiss();
            }
        });

        //public void setLineColor(int lineColor);            //设置中间线条的颜色
        //public void setTextColor(int textColor);            //设置文字的颜色
        //public void setTextSize(float textSize);            //设置文字大小
        picker.setCenterItem(0);
        picker.setTextColor(Color.rgb(106,201,210));
        picker.setLineColor(Color.rgb(106,201,210) ) ;
        window.setFocusable(true);
        //点击空白的地方关闭PopupWindow
        window.setBackgroundDrawable(new BitmapDrawable());
        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.showAtLocation(title, Gravity.BOTTOM, 0, 0);
        backgroundAlpha(0.4f);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
    }
    //endregion

    //region backgroundAlpha 设置添加屏幕的背景透明度
    /*
     * 方法名：backgroundAlpha(float bgAlpha)
     * 功能：  设置添加屏幕的背景透明度
     * 参数：  float
     * 返回值：无
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }
    //endregion
}
