package com.kevin.nodelogin.Login.login;

import android.app.Activity;
import android.app.Application;


import com.kevin.nodelogin.Login.login.di.component.ApplicationComponent;
import com.kevin.nodelogin.Login.login.di.component.DaggerApplicationComponent;
import com.kevin.nodelogin.Login.login.di.module.ContextModule;
import com.kevin.nodelogin.R;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class MyApplication extends Application {



    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/MavenPro-Regular.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);

    }

    public static MyApplication get(Activity activity){
        return (MyApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }


}
