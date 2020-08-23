package com.jwshi.supermummy.di.component;


import com.jwshi.supermummy.app.SuperMummyApplication;
import com.jwshi.supermummy.di.module.ActivityModule;
import com.jwshi.supermummy.di.module.AppModule;
import com.jwshi.supermummy.di.module.DataModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityModule.class, DataModule.class})
public interface AppComponent {

   void inject(SuperMummyApplication application);

    SuperMummyApplication provideApplication();

}
