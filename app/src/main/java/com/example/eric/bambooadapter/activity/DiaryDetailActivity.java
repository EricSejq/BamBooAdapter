package com.example.eric.bambooadapter.activity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.example.eric.bambooadapter.R;
import com.example.eric.bambooadapter.constant.Constant;
import com.example.eric.bambooadapter.data.DBBean.DiaryBean;
import com.example.eric.bambooadapter.utils.GlideUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * @author eric
 * @date 2019-4-27 21:00
 */
public class DiaryDetailActivity extends BaseActivity {

    private ImageView diary_detail_pic;
    private Toolbar toolbar;
    private DiaryBean mDiaryBean;

    @SuppressLint("ResourceType")
    @Override
    protected void initView() {
        diary_detail_pic = findViewById(R.id.diary_detail_pic);
        GlideUtil.loadResImage(diary_detail_pic, R.drawable.pikaqiu);
        diary_detail_pic.setOnClickListener(this);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(view -> {
            onBackPressed();
        });
    }

    @Override
    protected void initData() {
        toolbar.setTitle("askldhjalkfhjalksdjaslkjdalksjdlaskjdlaskdjs");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_diary_detail;
    }


    @Override
    protected void innerClick(View view) {
        super.innerClick(view);
        switch (view.getId()) {
            case R.id.diary_detail_pic:
                saveDiary();
                break;
            default:
                break;
        }
    }

    private void saveDiary() {
    }

    public static void startWithShareAnimation(Activity context, DiaryBean diaryBean, ImageView imageView, FloatingActionButton fab) {
        Intent starter = new Intent(context, DiaryDetailActivity.class);
        starter.putExtra(Constant.DIARY_DATA, diaryBean);
        context.startActivity(starter,
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        context,
                        new Pair<>(imageView, context.getString(R.string.trans_diary_pic))
                ).toBundle());
    }


    public static void startWithShareAnimation(Activity context, FloatingActionButton fab) {
        Intent starter = new Intent(context, DiaryDetailActivity.class);
        context.startActivity(starter,
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        context,
                        fab, context.getResources().getString(R.string.trans_fab)
                ).toBundle());
    }

}
