package com.kevin.nodelogin.Login.login.login.presenter;

public interface LoginContract {
    interface Presenter {
        void login(String username ,String password);
        void  signup(String username ,String password);
    }

    interface View {

        void showProgressBar();
        void hideProgress();
        void onLoginSuccess(String message);
        void onlLoginFailure(String message);
        void onSignupSuccess(String message);
        void OnSignupFailure(String message);

    }
}
