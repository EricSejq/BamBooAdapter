package com.example.eric.bambooadapter.activity;

import androidx.core.app.ActivityOptionsCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.example.eric.bambooadapter.R;
import com.example.eric.bambooadapter.constant.Constant;
import com.example.eric.bambooadapter.data.DBBean.DiaryBean;
import com.example.eric.bambooadapter.database.DataManager;
import com.example.eric.bambooadapter.utils.GlideUtil;

/**
 * @author eric
 * @date 2019-4-27 21:00
 */
public class DiaryDetailActivity extends BaseActivity {

    private ImageView diary_detail_pic;

    @SuppressLint("ResourceType")
    @Override
    protected void initView() {
        diary_detail_pic = findViewById(R.id.diary_detail_pic);
        GlideUtil.loadResImage(diary_detail_pic, R.drawable.pikaqiu);
        diary_detail_pic.setOnClickListener(this);
    }

    @Override
    protected void initData() {

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
        DataManager.getInstance().diaryEntityBox
                .put(new DiaryBean(
                        "eric",
                        "askjhdkjashdkjashdkjashdkajshdkajshdkajshdkjashdkjashdkjashdkjashdkjashdkas",
                        System.currentTimeMillis()));
    }

    public static void startWithShareAnimation(Activity context, String diatyId, ImageView imageView) {
        Intent starter = new Intent(context, DiaryDetailActivity.class);
        starter.putExtra(Constant.DIARY_ID, diatyId);
        context.startActivity(starter,
                ActivityOptionsCompat.makeSceneTransitionAnimation(context, imageView, context.getString(R.string.trans_diary_pic)).toBundle());
    }

}
