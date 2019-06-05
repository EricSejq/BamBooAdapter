package com.example.eric.bambooadapter.database;

import com.example.eric.bambooadapter.application.BamBooApplication;
import com.example.eric.bambooadapter.data.ClassBean;
import com.example.eric.bambooadapter.data.DBBean.DiaryBean;
import com.example.eric.bambooadapter.data.DBBean.TestBean;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;

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
    public Box<DiaryBean> diaryEntityBox;

    public void init(BamBooApplication bamBooApplication) {
        boxStore = bamBooApplication.getBoxStore();
        initEntityBox();
    }

    private void initEntityBox() {
        //对应操作对应表的类
        userEntityBox = boxStore.boxFor(TestBean.class);

        diaryEntityBox = boxStore.boxFor(DiaryBean.class);


    }

    public static Observable<Box<DiaryBean>> getDiary() {
        isNull();
        return Observable.create(emitter ->
                emitter.onNext(dataManager.diaryEntityBox)
        );
    }

    public static void insertDiary(DiaryBean data) {
        isNull();
        dataManager.diaryEntityBox.put(data);
    }

    public static void insertDiarys(List<DiaryBean> datas) {
        isNull();
        dataManager.diaryEntityBox.put(datas);
    }

    public static void isNull() {
        if (dataManager == null) {
            throw new NullPointerException("dataManager is null");
        }
    }

}
