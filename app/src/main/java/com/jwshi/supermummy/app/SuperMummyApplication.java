package com.jwshi.supermummy.app;

import android.app.Application;

import com.jwshi.supermummy.di.component.DaggerAppComponent;
import com.jwshi.supermummy.di.module.AppModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import rx_activity_result2.RxActivityResult;

/**
 * Created by shijiwei on 2020-08-23.
 *
 * @Desc:
 */
public class SuperMummyApplication extends Application implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    private static SuperMummyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        DaggerAppComponent.builder().appModule(new AppModule(this)).build().inject(this);
        RxActivityResult.register(this);
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }

    public static SuperMummyApplication getInstance() {
        return application;
    }
}

