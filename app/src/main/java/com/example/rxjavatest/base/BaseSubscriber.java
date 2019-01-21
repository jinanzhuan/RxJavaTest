package com.example.rxjavatest.base;

import android.util.Log;

import rx.Subscriber;

/**
 * 观察者重写，继承此类，则不必实现onCompleted()和onError()方法，如果需要只需重写即可
 */

public abstract class BaseSubscriber<T> extends Subscriber<T> {

    @Override
    public void onStart() {
        super.onStart();
        // do nothing by default
        //如果是耗时操作，可以在此处添加进度条等
    }

    @Override
    public void onCompleted() {
        Log.e("BaseSubscriber", "onCompleted");
    }

    @Override
    public void onError(Throwable e) {
        Log.e("BaseSubscriber", "onError message="+e.getMessage());
    }
}
