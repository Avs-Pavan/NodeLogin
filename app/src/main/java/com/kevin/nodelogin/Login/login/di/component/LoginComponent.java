package com.kevin.nodelogin.Login.login.di.component;



import com.kevin.nodelogin.Login.login.di.module.ContextModule;
import com.kevin.nodelogin.Login.login.di.module.LoginContextmodule;
import com.kevin.nodelogin.Login.login.di.module.LoginMvpModule;
import com.kevin.nodelogin.Login.login.di.scopes.ActivityScope;
import com.kevin.nodelogin.Login.login.login.view.LoginActivity;

import dagger.Component;


@ActivityScope
@Component(modules = { LoginMvpModule.class, ContextModule.class, LoginContextmodule.class}, dependencies = ApplicationComponent.class)
public interface LoginComponent {

    void injectMainActivity(LoginActivity loginActivity);
}
