package com.kevin.nodelogin.Login.login.home.presenter;

import android.content.Context;

import com.kevin.nodelogin.Login.login.di.Retrofit.ApiInterface;
import com.kevin.nodelogin.Login.login.home.view.Home;
import com.kevin.nodelogin.Login.login.login.model.pojo.BasicResponce;
import com.kevin.nodelogin.Login.login.login.model.pojo.UserListResponce;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;

    private ApiInterface apiInterface;
    private Context context;

    @Inject
    public HomePresenter(HomeContract.View view, ApiInterface apiInterface, Home context) {
        this.view = view;
        this.apiInterface = apiInterface;
        this.context = context;
    }

    @Override
    public void fetchList() {

        view.showProgressBar();

        apiInterface.fetchUsers().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserListResponce>() {
                    @Override
                    public void onCompleted() {
                        view.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onFailure("Error occurred : " + e.getLocalizedMessage());
                        view.hideProgress();
                    }

                    @Override
                    public void onNext(UserListResponce data) {
                        view.hideProgress();

                        if (data.getStatus()) {
                            view.onUserFetched(data.getUsers());
                        }
                        else view.onFailure(data.getMessage());
                    }
                });

    }

    @Override
    public void delete(String id) {
        view.showProgressBar();

        apiInterface.delete(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BasicResponce>() {
                    @Override
                    public void onCompleted() {
                        view.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgress();
                        view.onFailure("Error occurred : " + e.getLocalizedMessage());

                    }

                    @Override
                    public void onNext(BasicResponce data) {
                        view.hideProgress();

                        if (data.getStatus()) {
                            view.onSuccess(data.getMessage());
                        }
                        else view.onFailure(data.getMessage());
                    }
                });
    }
}
