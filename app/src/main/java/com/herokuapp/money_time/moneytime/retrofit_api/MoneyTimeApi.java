package com.herokuapp.money_time.moneytime.retrofit_api;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoneyTimeApi {
    @GET("/api/v1/user/")
    Call<JsonList> getData();
//    Call<List<UserModel>> getData(@Query("name") String resourceName, @Query("num") int count);
}