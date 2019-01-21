package com.example.rxjavatest.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.rxjavatest.R;
import com.example.rxjavatest.base.BaseActivity;

/**
 * Created by shuwei on 2019/1/19.
 */

public class ErrorObservableActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_error_observable;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ErrorObservableActivity.class);
        context.startActivity(starter);
    }

    public void retry(View v) {

    }

    public void catch_exception(View v) {

    }
}
