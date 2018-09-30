package com.eugene.prodobroapp.data.source.remote;


import com.eugene.prodobroapp.data.model.Message;
import com.eugene.prodobroapp.data.model.RemoteAnswer;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @GET("answer")
    Single<RemoteAnswer> getRemoteAnswer(@Query("messageId") String messageId);

    @POST("message")
    Single<Response<Void>> sendMessage(@Body Message message);
}
