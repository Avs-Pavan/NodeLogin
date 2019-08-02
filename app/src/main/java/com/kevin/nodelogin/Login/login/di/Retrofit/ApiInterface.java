package com.kevin.nodelogin.Login.login.di.Retrofit;


import com.kevin.nodelogin.Login.login.login.model.BasicResponce;
import com.kevin.nodelogin.Login.login.login.model.LoginPojo;
import com.kevin.nodelogin.Login.login.login.model.Responce;
import com.kevin.nodelogin.Login.login.home.model.UserListResponce;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;


public interface ApiInterface {
    @FormUrlEncoded
    @POST("users/login")
    Observable<LoginPojo> login(@Field("username") String username,
                                @Field("password") String password
    );

    @FormUrlEncoded
    @POST("users/signup")
    Observable<Responce> signup(@Field("username") String username,
                                @Field("password") String password
    );

    @GET("users/")
    Observable<UserListResponce> fetchUsers();

    @DELETE("users/{id}")
    Observable<BasicResponce> delete(@Path("id") String id);



}




