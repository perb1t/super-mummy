package com.jwshi.supermummy.ui.activity;

<<<<<<< HEAD
import android.content.res.Configuration;
=======
>>>>>>> 30fdf2eee7084ff8bd97208812f811b5932c7db3
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
<<<<<<< HEAD
        return Configuration.ORIENTATION_PORTRAIT;
=======
        return 0;
>>>>>>> 30fdf2eee7084ff8bd97208812f811b5932c7db3
    }
}
