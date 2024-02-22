package com.heisyenberg.springchatclient.services;

import com.google.gson.Gson;
import lombok.Getter;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Getter
@Service
public class RetrofitService {
    private static final String BASE_URL = "http://localhost:8080";
    private final Retrofit retrofit;

    public RetrofitService() {
        this.retrofit = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create(new Gson())).
                build();
    }
}
