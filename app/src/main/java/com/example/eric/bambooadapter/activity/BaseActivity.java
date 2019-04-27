package com.example.eric.bambooadapter.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.eric.bambooadapter.R;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

/**
 * Description: baseActivity
 * Data：2019/4/25-22:46
 *
 * @author: eric
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setExitTransition(new Slide(Gravity.LEFT));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setStatusTrans();
        setContentView(getLayoutId());
        initView();
        initData();
    }

    /**
     * 初始控件
     */
    protected abstract void initView();

    /**
     * 初始数据
     */
    protected abstract void initData();

    /**
     * 获取xml
     *
     * @return
     */
    protected abstract int getLayoutId();


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                break;
        }
        innerClick(view);
    }

    protected void innerClick(View view) {
    }

    /**
     * 设置status透明
     */
    private void setStatusTrans() {
//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        // 设置文字是否黑色
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        decorView.setSystemUiVisibility(option);
    }
}
