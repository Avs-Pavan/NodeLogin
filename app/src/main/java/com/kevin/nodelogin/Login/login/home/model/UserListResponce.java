package com.kevin.nodelogin.Login.login.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kevin.nodelogin.Login.login.home.model.User;

import java.util.List;

public class UserListResponce {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("users")
    @Expose
    private List<User> users = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
