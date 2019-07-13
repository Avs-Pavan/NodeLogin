package com.kevin.nodelogin.Login.login.di.module;


import com.kevin.nodelogin.Login.login.di.scopes.ActivityScope;
import com.kevin.nodelogin.Login.login.home.presenter.HomeContract;
import com.kevin.nodelogin.Login.login.login.presenter.LoginContract;

import dagger.Module;
import dagger.Provides;


@Module
public class HomeMvpModule {
    private final HomeContract.View mView;


    public HomeMvpModule(HomeContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @ActivityScope
    HomeContract.View provideView() {
        return mView;
    }


}
