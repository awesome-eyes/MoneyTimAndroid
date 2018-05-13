package com.herokuapp.money_time.moneytime.retrofit_api;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.provider.Settings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.herokuapp.money_time.moneytime.retrofit_api.authentication.AuthToken;
import com.herokuapp.money_time.moneytime.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends android.support.multidex.MultiDexApplication {

    private static MoneyTimeApi moneyTimeApi;
    private static AuthToken authToken;
    private Retrofit retrofit;

//    private static String apiUrl = "http://money-time.herokuapp.com/api/v1/";
    private static String apiUrl = "http://10.0.2.2:8000/api/v1/";

    @Override
    public void onCreate() {
        super.onCreate();

        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // check if enabled and if not send user to the GSP settings
        // Better solution would be to display a dialog and suggesting to
        // go to the settings
        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(App.apiUrl)
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        moneyTimeApi = retrofit.create(MoneyTimeApi.class); //Создаем объект, при помощи которого будем выполнять запросы

        SharedPreferences sharedPref = getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE);
        String storageToken = sharedPref.getString(Constants.SAVED_AUTH_TOKEN, "");
        authToken = new AuthToken(storageToken);
    }

//    public <S> S createService(Class<S> serviceClass) {
//        return createService(serviceClass, null, null);
//    }
//
//    public <S> S createService(Class<S> serviceClass, String username, String password) {
//        if (!TextUtils.isEmpty(username)
//                && !TextUtils.isEmpty(password)) {
//            String authToken = Credentials.basic(username, password);
//            return createService(serviceClass, authToken);
//        }
//
//        return createService(serviceClass, null);
//    }
//
//    public <S> S createService(
//            Class<S> serviceClass, final String authToken) {
//        if (!TextUtils.isEmpty(authToken)) {
//            AuthenticationInterceptor interceptor =
//                    new AuthenticationInterceptor(authToken);
//
//            if (!httpClient.interceptors().contains(interceptor)) {
//                httpClient.addInterceptor(interceptor);
//
//                builder.client(httpClient.build());
//                retrofit = builder.build();
//            }
//        }
//
//        return retrofit.create(serviceClass);
//    }

    public static MoneyTimeApi getApi() {
        return moneyTimeApi;
    }

    public static String getAuthToken() {
        return authToken.getToken();
    }

    public static void setAuthToken(String token) {
        App.authToken = new AuthToken(token);
    }
}
