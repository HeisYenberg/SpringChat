package com.heisyenberg.springchatclient.services;

import com.heisyenberg.springchatclient.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthenticationService {
    @POST("/signIn")
    Call<User> signIn(@Body User user);

    @POST("/signUp")
    Call<User> signUp(@Body User user);
}
