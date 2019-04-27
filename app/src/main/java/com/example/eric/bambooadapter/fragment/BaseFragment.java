package com.example.eric.bambooadapter.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Description:
 * Data：2019/4/27-16:58
 *
 * @author: eric
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        initView(view);
        initData();
        return view;
    }

    /**
     * 获取布局视图
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * 初始化控件
     */
    protected abstract void initData();
}
