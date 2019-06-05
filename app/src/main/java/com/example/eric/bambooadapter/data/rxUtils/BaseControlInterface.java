package com.example.eric.bambooadapter.data.rxUtils;

import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Data：2019/5/11-22:22
 *
 * @author: eric
 */
public interface BaseControlInterface<T> {

    abstract void onControl(T data);

    void onError(Throwable e);

    void onSubscribe(Disposable disposable);
}
