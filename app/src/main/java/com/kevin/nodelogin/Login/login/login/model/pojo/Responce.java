package com.kevin.nodelogin.Login.login.login.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Responce implements Serializable {

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    @Override
    public String toString() {
        return "Responce{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
