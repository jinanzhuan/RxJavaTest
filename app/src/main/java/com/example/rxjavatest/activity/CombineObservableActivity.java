package com.example.rxjavatest.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.rxjavatest.R;
import com.example.rxjavatest.base.BaseActivity;

/**
 * Created by shuwei on 2019/1/19.
 */

public class CombineObservableActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_combine_observable;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, CombineObservableActivity.class);
        context.startActivity(starter);
    }

    public void zip(View v) {

    }

    public void merge(View v) {

    }

    public void start_with(View v) {

    }

    public void combine_latest(View v) {

    }

    public void jion(View v) {

    }

    public void switch_on_next(View v) {

    }
}
