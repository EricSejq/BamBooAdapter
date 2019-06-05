package com.example.eric.bambooadapter.data.rxUtils;

import com.example.eric.bambooadapter.data.DBBean.DiaryBean;
import com.example.eric.bambooadapter.database.DataManager;

import io.objectbox.Box;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:rxjava 调用db工具类，实现线程切换
 * Data：2019/5/11-21:39
 *
 * @author: eric
 */
public class RxUtils {

    public static void ControlDiary(BaseControlInterface<Box<DiaryBean>> baseControlInterface) {
        DataManager.getDiary()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Box<DiaryBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        baseControlInterface.onSubscribe(d);
                    }

                    @Override
                    public void onNext(Box<DiaryBean> diaryBeanBox) {
                        baseControlInterface.onControl(diaryBeanBox);
                    }

                    @Override
                    public void onError(Throwable e) {
                        baseControlInterface.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
