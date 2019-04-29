package com.example.eric.bambooadapter.application;

import android.app.Application;
import android.util.Log;

import com.example.eric.bambooadapter.data.DBBean.MyObjectBox;
import com.example.eric.bambooadapter.database.DataManager;

import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;
import io.objectbox.android.BuildConfig;

/**
 * Description:
 * Data：2019/4/27-22:24
 *
 * @author: eric
 */
public class BamBooApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initObjectBox();
    }

    private BoxStore boxStore;

    private void initObjectBox() {
        //第一次没运行之前，MyObjectBox默认会有报错提示，可以忽略。创建实体类， make之后报错就会不提示
        boxStore = MyObjectBox.builder().androidContext(this).build();
        //        if (BuildConfig.DEBUG) {//开启浏览器访问ObjectBox
        boolean started = new AndroidObjectBrowser(boxStore).start(this);
        Log.i("ObjectBrowser", "Started: " + started);
        //        }
        DataManager.getInstance().init(this);
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }
}
