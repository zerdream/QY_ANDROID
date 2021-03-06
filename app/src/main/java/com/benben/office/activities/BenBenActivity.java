package com.benben.office.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.benben.office.application.MyApplication;


/**
 * 基类
 */
public abstract class BenBenActivity extends AppCompatActivity {
    //声明MyApplication变量
    private MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 定制流程
        setContentView();
        initViews();
        initListeners();
        initData();
        //获得activity的Task路径
        getActivityTask() ;
    }

    private void getActivityTask() {
        app = (MyApplication) getApplication();
        this.getApplication() ;
        app.addActivity(this);
    }

    public abstract void setContentView();

    public abstract void initViews();

    public abstract void initListeners();

    public abstract void initData();
}