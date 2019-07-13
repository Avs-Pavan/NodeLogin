package com.kevin.nodelogin.Login.login.di.module;


import com.kevin.nodelogin.Login.login.di.scopes.ActivityScope;
import com.kevin.nodelogin.Login.login.login.presenter.LoginContract;

import dagger.Module;
import dagger.Provides;


@Module
public class LoginMvpModule {
    private final LoginContract.View mView;


    public LoginMvpModule(LoginContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @ActivityScope
    LoginContract.View provideView() {
        return mView;
    }


}
