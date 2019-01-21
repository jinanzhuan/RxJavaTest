package com.example.rxjavatest.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.rxjavatest.R;
import com.example.rxjavatest.base.BaseActivity;
import com.example.rxjavatest.base.BaseSubscriber;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;

/**
 * Created by shuwei on 2019/1/19.
 */

public class CreateObservableActivity extends BaseActivity {
    private TextView mTvContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_observable;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, CreateObservableActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initView() {
        super.initView();
        mTvContent = (TextView)findViewById(R.id.tv_content);
    }

    /**
     * create方法为Observable原始的创建方法
     * @param view
     */
    public void create(View view) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                        Log.e("TAG", "我已经进行创建了");
                        subscriber.onNext("练习RxJava");
                        subscriber.onCompleted();
                    }
                })
                .subscribe(new BaseSubscriber<String>() {
                    @Override
                    public void onNext(String s) {
                        Log.e("subscriber", "onNext result="+s);
                    }
                });
    }

    /**
     * just为create方法的简写方式
     * 一般会将该方法配合项目已经有的function进行使用。
     * 会按照传参的先后顺序来执行。
     * 无论是否observable.subscribe()被调用。其中参数传递的函数均会被执行。
     * 本质上just方法是传递了一个值。
     * @param view
     */
    public void just(View view) {
        Observable.just("create的简写方式just方式")
                .subscribe(new BaseSubscriber<String>() {
                    @Override
                    public void onNext(String s) {
                        Log.e("subscriber", "onNext result="+s);
                    }
                });
    }

    /**
     * from convert various other objects and data types into Observables
     * 可以传Future/Callable,Iterable,Array。
     * Iterable同Array类似，会将其中的内容push给observer。
     * 需要注意的是，使用future，如果future未执行完成，则会将线程卡住。
     * 因为实际上observer会执行future.get()方法，而该方法是线程阻塞的。
     * 所以对于生产者传递Future的场景RXJava提供了额外的参数用来设置超时时间。
     * @param view
     */
    public void from(View view) {
        Observable.from(new String[]{"from的用途", "数组或者列表皆可以"})
                .subscribe(new BaseSubscriber<String>() {
                    @Override
                    public void onNext(String s) {
                        Log.e("subscriber", "onNext result="+s);
                    }
                });
    }

    String content = "";
    /**
     * do not create the Observable util the observer subscribes, and create a fresh Observable for each observer
     * 只有调用subscriber()方法的时候才会创建observable对象
     * @param view
     */
    public void defer(View view) {
        Observable<String> observable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just(content);
            }
        });
        content = "这时候我才进行赋值";
        observable.subscribe(new BaseSubscriber<String>() {
            @Override
            public void onNext(String s) {
                Log.e("TAG", "onNext result = "+s);
            }
        });
    }

    /**
     * empty:create an observable that emits no items but terminates normally
     * never:create an Observable that emits no items and does not terminate.
     * throw:create an Observable that emits no items and terminates with an error, 意思就是会直接调用onError()方法，结束本次调用
     * @param view
     */
    public void empty_throw_never(View view) {
        Observable.empty()
                .subscribe(new BaseSubscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        Log.e("TAG", "onCompleted empty()方法");
                    }

                    @Override
                    public void onNext(Object o) {
                        Log.e("TAG", "onNext empty()方法");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError empty()方法");
                    }
                });
        Observable.never()
                .subscribe(new BaseSubscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        Log.e("TAG", "onCompleted never()方法");
                    }

                    @Override
                    public void onNext(Object o) {
                        Log.e("TAG", "onNext never()方法");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError never()方法");
                    }
                });
        Observable.error(new Throwable())
                .subscribe(new BaseSubscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        Log.e("TAG", "onCompleted error()方法");
                    }

                    @Override
                    public void onNext(Object o) {
                        Log.e("TAG", "onNext error()方法");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError error()方法");
                    }
                });
    }

    /**
     * interval:定时
     * interval(long initialDelay, long period, TimeUnit unit)
     * first param: delay time
     * second param: period time
     * third param: time unit
     * 如何实现定时一定时间，串行take(int count)即可
     * @param view
     */
    public void interval(View view) {
        Log.e("TAG", "开始执行 time= "+ System.currentTimeMillis());
        Observable.interval(2, 1, TimeUnit.SECONDS)
                .take(10)
                .subscribe(new BaseSubscriber<Long>() {
                    @Override
                    public void onNext(Long aLong) {
                        Log.e("TAG", "long = "+aLong+" time= "+System.currentTimeMillis());
                    }
                });
    }

    /**
     * range(int start, int count)
     * for example: if start is n, count is m ,then : n, n+1, n+2, ... , n+m-1
     * @param view
     */
    public void range(View view) {
        Observable.range(3, 5)
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        Log.e("TAG", "onNext result = "+integer);
                    }
                });
    }

    /**
     * repeat
     * @param view
     */
    public void repeat(View view) {
        Observable.just(2, 3, 4)
                .repeat(2)
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        Log.e("TAG", "onNext result = "+integer);
                    }
                });
    }

    /**
     * 在队列前插入
     * @param view
     */
    public void start(View view) {
        Observable.just(9, 10, 11, 12)
                .startWith(07, 8)
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        Log.e("TAG", "onNext result = "+integer);
                    }
                });

    }

    /**
     *  returns an Observable that emits a single number zero after a delay period you specify.
     * 延迟设置的时间后，执行0
     * @param view
     */
    public void timer(View view) {
        Observable.timer(1, TimeUnit.SECONDS)
                .repeat(10)
                .subscribe(new BaseSubscriber<Long>() {
                    @Override
                    public void onNext(Long aLong) {
                        Log.e("TAG", "onNext result = "+aLong);
                    }
                });
    }
}
