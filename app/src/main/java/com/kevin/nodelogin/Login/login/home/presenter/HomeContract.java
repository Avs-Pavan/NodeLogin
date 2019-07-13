package com.kevin.nodelogin.Login.login.home.presenter;

import com.kevin.nodelogin.Login.login.login.model.pojo.User;

import java.util.List;

public interface HomeContract {
    interface Presenter {
        void fetchList();
        void delete(String id);
    }

    interface View {

        void showProgressBar();

        void hideProgress();

        void onUserFetched(List<User> users);

        void onSuccess(String message);

        void onFailure(String error);

    }

   interface Listener{
       void  onDelete(String id);
    }
}
