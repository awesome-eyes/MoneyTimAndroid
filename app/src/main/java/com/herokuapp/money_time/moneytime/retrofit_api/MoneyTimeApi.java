package com.herokuapp.money_time.moneytime.retrofit_api;

import com.herokuapp.money_time.moneytime.retrofit_api.authentication.AuthToken;
import com.herokuapp.money_time.moneytime.retrofit_api.models.ExpenseCategoryModel;
import com.herokuapp.money_time.moneytime.retrofit_api.models.ExpenseModel;
import com.herokuapp.money_time.moneytime.retrofit_api.models.LocationModel;
import com.herokuapp.money_time.moneytime.retrofit_api.models.UserModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface MoneyTimeApi {
    @GET("/api/v1/user/")
    Call<JsonList<UserModel>> getUserData(@Header("Authorization") String authHeader);


    @GET("/api/v1/expense/")
    Call<JsonList<ExpenseModel>> getExpenseData(@Header("Authorization") String authHeader);

    @GET("/api/v1/expense_category/")
    Call<JsonList<ExpenseCategoryModel>> getExpenseCategoryData(@Header("Authorization") String authHeader);

    @GET("/api/v1/location/")
    Call<JsonList<LocationModel>> getLocationData(@Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("/api/obtain_auth_token/")
    Call<AuthToken> obtainAuthToken(@Field("username") String email,
                                    @Field("password") String password);
//    Call<List<UserModel>> getData(@Query("name") String resourceName, @Query("num") int count);
}