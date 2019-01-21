package com.example.rxjavatest;

import android.util.Log;
import android.view.View;

import com.example.rxjavatest.activity.CombineObservableActivity;
import com.example.rxjavatest.activity.CreateObservableActivity;
import com.example.rxjavatest.activity.ErrorObservableActivity;
import com.example.rxjavatest.activity.FilterObservableActivity;
import com.example.rxjavatest.activity.TransformObservableActivity;
import com.example.rxjavatest.base.BaseActivity;
import com.example.rxjavatest.base.BaseSubscriber;

import rx.Observable;
import rx.Subscriber;

public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    public void create_observable(View view) {
        CreateObservableActivity.actionStart(mContext);
    }

    public void transform_observable(View view) {
        TransformObservableActivity.actionStart(mContext);
    }

    public void filter_observable(View view) {
        FilterObservableActivity.actionStart(mContext);
    }

    public void combine_observable(View view) {
        CombineObservableActivity.actionStart(mContext);
    }

    public void error_observable(View view) {
        ErrorObservableActivity.actionStart(mContext);
    }

    private void createNewSample(){
        //第一步：创建被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //以下为订阅事件
                subscriber.onNext("这是一个关于Rxjava的例子");
                subscriber.onCompleted();
            }
        });

        //第二步：创建观察者
        Subscriber<String> subscriber = new BaseSubscriber<String>() {
            @Override
            public void onNext(String s) {
                Log.e("TAG", "onNext result="+s);
            }
        };

        //第三步：订阅事件
        observable.subscribe(subscriber);
    }

}
