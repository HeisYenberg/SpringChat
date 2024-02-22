package com.heisyenberg.springchatclient.services;

import com.heisyenberg.springchatclient.models.Message;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface MessagesService {
    @GET("/{user}/{room}/getMessages")
    Call<List<Message>> getMessages(@Path("room") Long room);
}
