package com.example.dangfiztssi.flicks.utils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DangF on 10/12/16.
 */

public class RetrofitUtils {

    public static Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URL)
                .client(client())
                .addConverterFactory(GsonConverterFactory.create()) // convert xml to json object purpose
                .build();
    }

    private static OkHttpClient client(){
        return new OkHttpClient.Builder()
                .addInterceptor(apiKeyInterceptor())
                .build();
    }

    private static Interceptor apiKeyInterceptor(){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url()
                        .newBuilder()
                        .addQueryParameter("api_key",AppConstant.API_KEY)
                        .build();

                request = request.newBuilder()
                        .url(url)
                        .build();
                return chain.proceed(request);
            }
        };
    }
}
