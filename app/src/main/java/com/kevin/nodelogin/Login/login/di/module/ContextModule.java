package com.kevin.nodelogin.Login.login.di.module;

import android.content.Context;


import com.kevin.nodelogin.Login.login.di.qualifier.ApplicationContext;
import com.kevin.nodelogin.Login.login.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;


@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}
