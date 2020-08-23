package com.jwshi.supermummy.ui.activity;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jwshi.supermummy.R;
import com.jwshi.supermummy.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {


    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean showToolbar() {
        return false;
    }

    @Override
    public void onInit(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public int getOrientation() {
        return Configuration.ORIENTATION_PORTRAIT;
    }
}
