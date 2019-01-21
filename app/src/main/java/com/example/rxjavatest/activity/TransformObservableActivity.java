package com.example.rxjavatest.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.rxjavatest.DataBean;
import com.example.rxjavatest.R;
import com.example.rxjavatest.base.BaseActivity;
import com.example.rxjavatest.base.BaseSubscriber;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

/**
 * Created by shuwei on 2019/1/19.
 */

public class TransformObservableActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_transform_observable;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, TransformObservableActivity.class);
        context.startActivity(starter);
    }

    /**
     * transform the terms emitted by an Observable by applying a function to each item
     * 把一个对象转换成另一个对象
     * @param view
     */
    public void map(View view){
        Observable.just(123)
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return String.valueOf(integer);
                    }
                })
                .subscribe(new BaseSubscriber<String>() {
                    @Override
                    public void onNext(String s) {
                        Log.e("TAG", "onNext reuslt = "+ s);
                    }
                });
    }

    /**
     * transform the items emitted by an Observable into Observables, then flatten the emissions from those into a single Observable
     * 一般用于一个网络请求需要前一个的网络请求后的某个参数
     * 如果想让一些类的Observable按顺序，则需要用contactMap()
     * @param view
     */
    public void flatmap(View view){
        Observable.from(new Integer[]{1, 2, 3})
                .flatMap(new Func1<Integer, Observable<String>>() {
                    @Override
                    public Observable<String> call(Integer integer) {
                        return Observable.just(String.valueOf(integer));
                    }
                })
                .subscribe(new BaseSubscriber<String>() {
                    @Override
                    public void onNext(String s) {

                    }
                });

    }

    /**
     * divide an Observable into a set of Observables that each emit a different subset of items from the original Observable
     * @param view
     */
    public void group_by(View view){
        List<DataBean> list = createData();
        Observable.from(list)
                .groupBy(new Func1<DataBean, String>() {
                    @Override
                    public String call(DataBean dataBean) {
                        return dataBean.getDate();
                    }
                })
                .subscribe(new BaseSubscriber<GroupedObservable<String, DataBean>>() {
                    @Override
                    public void onNext(final GroupedObservable<String, DataBean> arg0) {
                        arg0.subscribe(new BaseSubscriber<DataBean>() {
                            @Override
                            public void onNext(DataBean dataBean) {
                                Log.e("TAG", "group:"+arg0.getKey()+" data:"+dataBean.toString());
                            }
                        });

                    }
                });
    }

    private List<DataBean> createData() {
        List<DataBean> list = new ArrayList<>();
        DataBean bean = new DataBean();
        bean.setDate("2019-01-10");
        list.add(bean);
        bean = new DataBean();
        bean.setDate("2019-01-11");
        list.add(bean);
        bean = new DataBean();
        bean.setDate("2019-01-10");
        list.add(bean);
        bean = new DataBean();
        bean.setDate("2019-01-12");
        list.add(bean);
        bean = new DataBean();
        bean.setDate("2019-01-10");
        list.add(bean);
        bean = new DataBean();
        bean.setDate("2019-01-11");
        list.add(bean);
        bean = new DataBean();
        bean.setDate("2019-01-10");
        list.add(bean);
        return list;
    }

    public void buffer(View view){

    }

    public void scan(View view){

    }

    public void window(View view){

    }
}
