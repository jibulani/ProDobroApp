package com.eugene.prodobroapp.data.source.remote;


import com.eugene.prodobroapp.data.model.Message;
import com.eugene.prodobroapp.data.model.RemoteAnswer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageServer {

    private static final String BASE_URL = "http://100.100.151.5:8080/";
    private final Api api;

    public MessageServer() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        api = retrofit.create(Api.class);
    }

    public Single<Response<Void>> sendMessage(Message message) {
        return api.sendMessage(message);
    }

    public Single<RemoteAnswer> getRemoteAnswer(String messageId) {
        return api.getRemoteAnswer(messageId);
    }
}
