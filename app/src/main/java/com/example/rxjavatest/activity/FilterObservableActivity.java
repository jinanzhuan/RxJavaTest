package com.example.rxjavatest.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.rxjavatest.R;
import com.example.rxjavatest.base.BaseActivity;

/**
 * Created by shuwei on 2019/1/19.
 */

public class FilterObservableActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_filter_observable;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, FilterObservableActivity.class);
        context.startActivity(starter);
    }

    public void debounce(View v) {

    }

    public void distinct(View v) {

    }

    public void element_at(View v) {

    }

    public void filter(View v) {

    }

    public void first(View v) {

    }

    public void ignore_element(View v) {

    }

    public void last(View v) {

    }

    public void sample(View v) {

    }

    public void skip(View v) {

    }

    public void skip_last(View v) {

    }

    public void take(View v) {

    }

    public void take_last(View v) {

    }

}
