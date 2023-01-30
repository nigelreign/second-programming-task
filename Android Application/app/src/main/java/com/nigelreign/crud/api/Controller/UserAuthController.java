package com.nigelreign.crud.api.Controller;

import com.google.gson.JsonObject;
import com.nigelreign.crud.api.models.LoginResults;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserAuthController {
    @Headers({
            "Accept: application/json",
    })
    @POST("/api/v1/token/fetch/")
    Call<LoginResults> login(@Body JsonObject gsonObject);

}
