package com.kevin.nodelogin.Login.login.home.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kevin.nodelogin.Login.login.MyApplication;
import com.kevin.nodelogin.Login.login.adapters.UsersAdapter;
import com.kevin.nodelogin.Login.login.di.component.ApplicationComponent;
import com.kevin.nodelogin.Login.login.di.component.DaggerHomeComponent;
import com.kevin.nodelogin.Login.login.di.component.HomeComponent;
import com.kevin.nodelogin.Login.login.di.module.HomeContextmodule;
import com.kevin.nodelogin.Login.login.di.module.HomeMvpModule;
import com.kevin.nodelogin.Login.login.home.presenter.HomeContract;
import com.kevin.nodelogin.Login.login.home.presenter.HomePresenter;
import com.kevin.nodelogin.Login.login.home.model.User;
import com.kevin.nodelogin.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Home extends AppCompatActivity implements HomeContract.View, HomeContract.Listener {

    @Inject
    HomePresenter presenter;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.rv)
    RecyclerView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        HomeComponent homeComponent;
        homeComponent = DaggerHomeComponent.builder().homeContextmodule(new HomeContextmodule(this))
                .homeMvpModule(new HomeMvpModule(this))
                .applicationComponent(applicationComponent).build();
        homeComponent.injectHome(this);

        presenter.fetchList();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onUserFetched(List<User> users) {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        list.setLayoutManager(manager);
        list.setAdapter(new UsersAdapter(users, this));
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        presenter.fetchList();
    }

    @Override
    public void onFailure(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDelete(String id) {
        presenter.delete(id);
    }
}
