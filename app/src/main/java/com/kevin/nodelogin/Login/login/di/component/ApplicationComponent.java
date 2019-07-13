package com.kevin.nodelogin.Login.login.di.component;

import android.content.Context;


import com.kevin.nodelogin.Login.login.MyApplication;
import com.kevin.nodelogin.Login.login.di.Retrofit.ApiInterface;
import com.kevin.nodelogin.Login.login.di.module.ContextModule;
import com.kevin.nodelogin.Login.login.di.module.NetworkModule;
import com.kevin.nodelogin.Login.login.di.qualifier.ApplicationContext;
import com.kevin.nodelogin.Login.login.di.scopes.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, NetworkModule.class})
public interface ApplicationComponent {

    ApiInterface getApiInterface();

    @ApplicationContext
    Context getContext();

    void injectApplication(MyApplication myApplication);

}
