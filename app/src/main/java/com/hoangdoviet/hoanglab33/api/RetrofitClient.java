package com.hoangdoviet.hoanglab33.api;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hoangdoviet.hoanglab33.util.Constant;

import java.io.IOException;


import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static JsonPlaceHolderAPI jsonPlaceHolderAPI;
    public static JsonPlaceHolderAPI getJsonPlaceHolderAPI(){
        if(jsonPlaceHolderAPI == null){
            Gson gson = new GsonBuilder().serializeNulls().create();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @NonNull
                        @Override
                        public Response intercept(@NonNull Chain chain) throws IOException {
                            Request originalRequest = chain.request();
                            Request newRequest = originalRequest.newBuilder()
                                    .header("Interceptor-Header","abc")
                                    .build();
                            return chain.proceed(newRequest);
                        }
                    })
                    .addInterceptor(loggingInterceptor)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
            jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
        }
        return jsonPlaceHolderAPI;
    }
}
