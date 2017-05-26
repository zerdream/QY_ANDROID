package com.benben.office.activities;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.benben.office.R;
import com.benben.office.fragments.ColleagueFragment;
import com.benben.office.fragments.HomePageFragment;
import com.benben.office.fragments.MyFragment;
import com.benben.office.fragments.SystemFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BenBenActivity {

    private HomePageFragment homePageFragment ;
    private SystemFragment systemFragment ;
    private ColleagueFragment colleagueFragment ;
    private MyFragment myFragment ;

    private BottomNavigationBar bottomNavigationBar;
    private LinearLayout ll_content;
    //private BadgeItem badgeItem ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initViews() {
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        ll_content = (LinearLayout) findViewById(R.id.ll_content);
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initData() {
        bottomNavigationBar.setAutoHideEnabled(false);//自动隐藏
        //BottomNavigationBar.MODE_SHIFTING;
        //BottomNavigationBar.MODE_FIXED;
        //BottomNavigationBar.MODE_DEFAULT;
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);

        // BottomNavigationBar.BACKGROUND_STYLE_DEFAULT;
        // BottomNavigationBar.BACKGROUND_STYLE_RIPPLE
        // BottomNavigationBar.BACKGROUND_STYLE_STATIC
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        //背景颜色
        bottomNavigationBar.setBarBackgroundColor(R.color.white);
        bottomNavigationBar.setInActiveColor(R.color.bottom_unselected);//未选中时的颜色
        bottomNavigationBar.setActiveColor(R.color.bottom_selected);//选中时的颜色

        //角标
        //badgeItem = new BadgeItem().setBackgroundColor(Color.RED).setText("99").setHideOnSelect(true);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.icon_index, R.string.home))
                .addItem(new BottomNavigationItem(R.mipmap.icon_system, R.string.system))
                .addItem(new BottomNavigationItem(R.mipmap.icon_partner, R.string.colleague))
                .addItem(new BottomNavigationItem(R.mipmap.icon_me, R.string.me))
                //.setFirstSelectedPosition(0)//设置默认选择item
                .initialise();

        homePageFragment = new HomePageFragment();
        systemFragment = new SystemFragment();
        colleagueFragment = new ColleagueFragment();
        myFragment = new MyFragment();
        final List<Fragment> list = new ArrayList() ;
        list.add(0 , homePageFragment );
        list.add(1 , systemFragment );
        list.add(2 , colleagueFragment );
        list.add(3 , myFragment );
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            /*底部NaV监听*/
            @Override
            public void onTabSelected(int position) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (position) {
                    case 0:
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).isVisible()){
                                if (homePageFragment.isAdded()){
                                    transaction.hide(list.get(i)).show(homePageFragment) ;
                                }else {
                                    transaction.hide(list.get(i)).add(R.id.ll_content , homePageFragment) ;
                                }
                                break;
                            }
                        }
                        break;
                    case 1:
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).isVisible()){
                                if (systemFragment.isAdded()){
                                    transaction.hide(list.get(i)).show(systemFragment) ;
                                }else {
                                    transaction.hide(list.get(i)).add(R.id.ll_content , systemFragment) ;
                                }
                                break;
                            }
                        }
                        break;
                    case 2:
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).isVisible()){
                                if (colleagueFragment.isAdded()){
                                    transaction.hide(list.get(i)).show(colleagueFragment) ;
                                }else {
                                    transaction.hide(list.get(i)).add(R.id.ll_content , colleagueFragment) ;
                                }
                                break;
                            }
                        }
                        break;
                    case 3:
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).isVisible()){
                                if (myFragment.isAdded()){
                                    transaction.hide(list.get(i)).show(myFragment) ;
                                }else {
                                    transaction.hide(list.get(i)).add(R.id.ll_content , myFragment) ;
                                }
                                break;
                            }
                        }
                        break;
                    default:
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).isVisible()){
                                if (homePageFragment.isAdded()){
                                    transaction.hide(list.get(i)).show(homePageFragment) ;
                                }else {
                                    transaction.hide(list.get(i)).add(R.id.ll_content , homePageFragment) ;
                                }
                                break;
                            }
                        }
                        break;
                }
                transaction.commit();
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
            }
        });
        //默认选择首页
        SelectedFragment();
    }


    private void SelectedFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.ll_content, homePageFragment);
        transaction.commit() ;
    }


}
