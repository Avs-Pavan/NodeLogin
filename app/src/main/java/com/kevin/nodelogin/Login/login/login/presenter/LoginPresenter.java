package com.kevin.nodelogin.Login.login.login.presenter;

import android.content.Context;

import com.kevin.nodelogin.Login.login.di.Retrofit.ApiInterface;
import com.kevin.nodelogin.Login.login.login.model.pojo.LoginPojo;
import com.kevin.nodelogin.Login.login.login.model.pojo.Responce;
import com.kevin.nodelogin.Login.login.login.view.LoginActivity;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;

    private ApiInterface apiInterface;
    private Context context;

    @Inject
    public LoginPresenter(LoginContract.View view, ApiInterface apiInterface, LoginActivity context) {
        this.view = view;
        this.apiInterface = apiInterface;
        this.context = context;
    }

    @Override
    public void login(String username ,String passwor) {

        view.showProgressBar();

        apiInterface.login(username, passwor).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginPojo>() {
                    @Override
                    public void onCompleted() {
                        view.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onlLoginFailure("Error occurred : " + e.getLocalizedMessage());
                        view.hideProgress();
                    }

                    @Override
                    public void onNext(LoginPojo data) {
                        view.hideProgress();
                        if (data.getStatus()) {
                            view.onLoginSuccess(data.getMessage());
                        }
                        else view.onlLoginFailure(data.getMessage());
                    }
                });


    }

    @Override
    public void signup(String username ,String password) {
        view.showProgressBar();

        apiInterface.signup(username,password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Responce>() {
                    @Override
                    public void onCompleted() {
                        view.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onlLoginFailure("Error occurred : " + e.getLocalizedMessage());
                        view.hideProgress();
                    }

                    @Override
                    public void onNext(Responce data) {
                        view.hideProgress();

                        if (data.isStatus()) {
                            view.onSignupSuccess(data.getMessage());
                        }
                        else view.OnSignupFailure(data.getMessage());
                    }
                });

    }
}
