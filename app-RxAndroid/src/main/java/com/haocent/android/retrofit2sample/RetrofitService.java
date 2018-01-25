package com.haocent.android.retrofit2sample;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tnno Wu on 2018/01/25.
 */

public class RetrofitService {

    private static final String BASE_URL = "https://api.github.com";

    public GitHubService getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(GitHubService.class);
    }
}
