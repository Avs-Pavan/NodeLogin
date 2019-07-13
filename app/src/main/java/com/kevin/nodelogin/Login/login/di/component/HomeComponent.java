package com.kevin.nodelogin.Login.login.di.component;



import com.kevin.nodelogin.Login.login.di.module.ContextModule;
import com.kevin.nodelogin.Login.login.di.module.HomeContextmodule;
import com.kevin.nodelogin.Login.login.di.module.HomeMvpModule;
import com.kevin.nodelogin.Login.login.di.scopes.ActivityScope;
import com.kevin.nodelogin.Login.login.home.view.Home;
import dagger.Component;


@ActivityScope
@Component(modules = { HomeMvpModule.class, HomeContextmodule.class, ContextModule.class}, dependencies = ApplicationComponent.class)
public interface HomeComponent {

    void injectHome(Home home);
}
