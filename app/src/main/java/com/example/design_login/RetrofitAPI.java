package com.example.design_login;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface RetrofitAPI
{
    @FormUrlEncoded
    @POST("Login")
    Call<JsonObject> getUserLogin(@Field("Token") String mAccessKey,
                                  @Field("username") String userID,
                                  @Field("password") String password,
                                  @Field("IPAddress") String mDeviceID);


}
