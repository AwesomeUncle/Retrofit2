package com.haocent.android.retrofit2sample;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Tnno Wu on 2018/01/25.
 */

public interface GitHubService {

    // Contributors
    @GET("/repos/square/retrofit/contributors")
    Observable<List<GitHubBean>> getContributors();
}
