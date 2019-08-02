package com.kevin.nodelogin.Login.login.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kevin.nodelogin.Login.login.home.model.User;

public class LoginPojo {

	@SerializedName("status")
	@Expose
	private Boolean status;
	@SerializedName("message")
	@Expose
	private String message;
	@SerializedName("user")
	@Expose
	private User user;
	@SerializedName("token")
	@Expose
	private String token;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "LoginPojo{" +
				"status=" + status +
				", message='" + message + '\'' +
				", user=" + user +
				", token='" + token + '\'' +
				'}';
	}
}