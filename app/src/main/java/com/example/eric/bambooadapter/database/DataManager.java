package com.example.eric.bambooadapter.database;

import com.example.eric.bambooadapter.application.BamBooApplication;
import com.example.eric.bambooadapter.data.ClassBean;
import com.example.eric.bambooadapter.data.DBBean.TestBean;

import io.objectbox.Box;
import io.objectbox.BoxStore;

/**
 * Description:
 * Data：2019/4/27-22:26
 *
 * @author: eric
 */
public class DataManager {

    private static DataManager dataManager;

    public static synchronized DataManager getInstance() {
        if (dataManager == null) {
            dataManager = new DataManager();
        }
        return dataManager;
    }

    public BoxStore boxStore;
    public Box<TestBean> userEntityBox;

    public void init(BamBooApplication bamBooApplication) {
        boxStore = bamBooApplication.getBoxStore();
        initUserEntityBox();
    }

    private void initUserEntityBox() {
        //对应操作对应表的类
        userEntityBox = boxStore.boxFor(TestBean.class);
    }

}
