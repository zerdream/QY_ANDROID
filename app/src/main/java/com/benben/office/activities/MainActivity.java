/*
* 文件名： BenBenActivity
* 描  述：
* 作  者：
* 时  间：
* 版  权：
*/

package com.benben.office.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.LinearLayout;
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

    private HomePageFragment homePageFragment ; //首页Fragment
    private SystemFragment systemFragment ; //系统Fragment
    private ColleagueFragment colleagueFragment ; //同事Fragment
    private MyFragment myFragment ; //我Fragment
    private BottomNavigationBar bottomNavigationBar; //底部导航
    private LinearLayout ll_content; //一级Fragment容器

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

        homePageFragment = new HomePageFragment();
        systemFragment = new SystemFragment();
        colleagueFragment = new ColleagueFragment();
        myFragment = new MyFragment();
        final List<Fragment> listFragment = new ArrayList() ;
        listFragment.add(0 , homePageFragment );
        listFragment.add(1 , systemFragment );
        listFragment.add(2 , colleagueFragment );
        listFragment.add(3 , myFragment );

        //底部导航监听
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {

            @Override
            public void onTabSelected(int position) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (position) {
                    case 0:
                        ActiveFragment(listFragment,transaction,homePageFragment);
                        break;
                    case 1:
                        ActiveFragment(listFragment,transaction,systemFragment);
                        break;
                    case 2:
                        ActiveFragment(listFragment,transaction,colleagueFragment);
                        break;
                    case 3:
                        ActiveFragment(listFragment,transaction,myFragment);
                        break;
                    default:
                        ActiveFragment(listFragment,transaction,homePageFragment);
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
    }

    @Override
    public void initData() {
        //底部导航特性设置
        bottomNavigationBar.setAutoHideEnabled(false);//自动隐藏
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        //底部导航颜色设置
        bottomNavigationBar.setBarBackgroundColor(R.color.white); //导航背景颜色
        bottomNavigationBar.setInActiveColor(R.color.bottom_unselected); //未选中时的颜色
        bottomNavigationBar.setActiveColor(R.color.bottom_selected); //选中时的颜色

        //添加底部导航图标和文字
        bottomNavigationBar
            .addItem(new BottomNavigationItem(R.mipmap.icon_index, R.string.home))
            .addItem(new BottomNavigationItem(R.mipmap.icon_system, R.string.system))
            .addItem(new BottomNavigationItem(R.mipmap.icon_partner, R.string.colleague))
            .addItem(new BottomNavigationItem(R.mipmap.icon_me, R.string.me))
            .initialise();

        //默认选择首页
        SetDefaultFragment();
    }

    //region 设置默认为首页Fragment
    /*
     * 方法名：SetDefaultFragment()
     * 功能：  设置默认为首页Fragment
     * 参数：  无
     * 返回值：无
     */
    private void SetDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.ll_content, homePageFragment);
        transaction.commit() ;
    }
    //endregion

    //region 设置活动的Fragment
    /*
     * 方法名：ActiveFragment(List<Fragment> listFragment,
     *                          FragmentTransaction transaction,
     *                          Fragment fragment)
     * 功能：  设置活动的Fragment
     * 参数：  List<Fragment>,
     *        FragmentTransaction,
     *        Fragment
     * 返回值：无
     */
    private void ActiveFragment(List<Fragment> listFragment,
                                FragmentTransaction transaction,
                                Fragment fragment)
    {
        for (int i = 0; i < listFragment.size(); i++) {
            if (listFragment.get(i).isVisible()){
                if (fragment.isAdded()){
                    transaction.hide(listFragment.get(i)).show(fragment) ;
                }else {
                    transaction.hide(listFragment.get(i)).add(R.id.ll_content , fragment) ;
                }
                break;
            }
        }
    }
    //endregion
}
