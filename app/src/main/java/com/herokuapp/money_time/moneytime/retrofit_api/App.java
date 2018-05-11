package com.herokuapp.money_time.moneytime.retrofit_api;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static MoneyTimeApi moneyTimeApi;
    private Retrofit retrofit;
    private static String apiUrl = "http://money-time.herokuapp.com/api/v1/";
//    private static String apiUrl = "http://10.0.2.2:8000/api/v1/";

    @Override
    public void onCreate() {
        super.onCreate();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(App.apiUrl) //Базовая часть адреса
//                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        moneyTimeApi = retrofit.create(MoneyTimeApi.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    public static MoneyTimeApi getApi() {
        return moneyTimeApi;
    }
}
