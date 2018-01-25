package com.tnnowu.retrofit2sample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Tnno Wu on 2017/9/17.
 */

public interface MainService {

    @GET("/repos/square/retrofit/contributors")
    Call<List<Contributor>> getCall();
}
