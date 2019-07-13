package com.kevin.nodelogin.Login.login.di.module;

import android.content.Context;

import com.kevin.nodelogin.Login.login.di.qualifier.ActivityContext;
import com.kevin.nodelogin.Login.login.di.scopes.ActivityScope;
import com.kevin.nodelogin.Login.login.login.view.LoginActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginContextmodule {
    private LoginActivity loginActivity;

    public Context context;

    public LoginContextmodule(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
        context = loginActivity;
    }

    @Provides
    @ActivityScope
    public LoginActivity providesMainActivity() {
        return loginActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }

}
