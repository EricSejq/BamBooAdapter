package com.example.eric.bambooadapter.activity;


import com.example.eric.bambooadapter.R;
import com.example.eric.bambooadapter.application.BamBooApplication;
import com.example.eric.bambooadapter.data.DBBean.DiaryBean;
import com.example.eric.bambooadapter.database.DataManager;
import com.example.eric.bambooadapter.fragment.DiaryFragment;
import com.example.eric.bambooadapter.fragment.FragmentFactory;
import com.example.eric.bambooadapter.fragment.MineFragment;
import com.example.eric.bambooadapter.fragment.TipsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;

/**
 * @author eric
 * @date 2019-4-25
 */
public class HomeActivity extends BaseActivity {

    private int mCurrentIndex = -1;
    public static final int INDEX_DIARY = 1;
    public static final int INDEX_TIPS = 3;
    public static final int INDEX_MINE = 2;
    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener = menuItem -> {
        if (menuItem != null) {
            onMenuSelect(menuItem.getItemId());
            return true;
        }
        return false;
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        BottomNavigationView main_bnv = findViewById(R.id.main_bnv);
        main_bnv.setOnNavigationItemSelectedListener(selectedListener);
        chooseFragment(INDEX_DIARY);

    }

    @Override
    protected void initData() {
        //        DataManager.insertDiary(new DiaryBean("eric", "111111111111111111111", System.currentTimeMillis(), "标题1"));
        //        DataManager.insertDiary(new DiaryBean("eric", "222222222222222222222", System.currentTimeMillis(), "标题2"));
    }

    private void onMenuSelect(int viewId) {
        int index = -1;
        switch (viewId) {
            case R.id.menu_diary:
                index = INDEX_DIARY;
                break;
            case R.id.menu_mine:
                index = INDEX_MINE;
                break;
            case R.id.menu_tips:
                index = INDEX_TIPS;
                break;
            default:
                break;
        }
        chooseFragment(index);
    }

    private void chooseFragment(int index) {
        if (index == -1 || index == mCurrentIndex) {
            return;
        }
        //隐藏当前的
        if (isLoadedFragment(getFragmentTag(mCurrentIndex))) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .hide(getFragment(getFragmentTag(mCurrentIndex)))
                    .commitAllowingStateLoss();
        }
        //加载并显示要显示的
        if (isLoadedFragment(getFragmentTag(index))) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .show(getFragment(getFragmentTag(index)))
                    .commitAllowingStateLoss();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_fl, getFragment(getFragmentTag(index)), getFragmentTag(index))
                    .show(getFragment(getFragmentTag(index)))
                    .commitAllowingStateLoss();
        }
        mCurrentIndex = index;
    }

    private Fragment getFragment(String fragmentTag) {
        if (isLoadedFragment(fragmentTag)) {
            return getSupportFragmentManager().findFragmentByTag(fragmentTag);
        } else {
            return FragmentFactory.create(fragmentTag);
        }
    }

    private boolean isLoadedFragment(String fragmentTag) {
        return getSupportFragmentManager().findFragmentByTag(fragmentTag) != null;
    }

    private String getFragmentTag(int index) {
        if (index == INDEX_DIARY) {
            return DiaryFragment.class.toString();
        } else if (index == INDEX_MINE) {
            return MineFragment.class.toString();
        } else if (index == INDEX_TIPS) {
            return TipsFragment.class.toString();
        } else {
            return "";
        }
    }

}
