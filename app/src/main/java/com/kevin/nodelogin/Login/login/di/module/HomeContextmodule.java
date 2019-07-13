package com.kevin.nodelogin.Login.login.di.module;

import android.content.Context;

import com.kevin.nodelogin.Login.login.di.qualifier.ActivityContext;
import com.kevin.nodelogin.Login.login.di.scopes.ActivityScope;
import com.kevin.nodelogin.Login.login.home.view.Home;
import com.kevin.nodelogin.Login.login.login.view.LoginActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeContextmodule {
    private Home home;

    public Context context;

    public HomeContextmodule(Home home) {
        this.home = home;
        context = home;
    }

    @Provides
    @ActivityScope
    public Home providesMainActivity() {
        return home;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }

}
