package com.tnnowu.retrofit2sample;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

/**
 * Created by Tnno Wu on 2017/12/4.
 */

public interface MainService {

    // 将 ResponseBody 作为了返回类型。Retrofit 会试图解析并转换它，所以你不能使用任何其他返回类型，
    // 否则当你下载文件的时候，是毫无意义的。
    // 防止大文件，使用 @Streaming
    @Streaming
    @GET("u/13990136?v=4")
    Call<ResponseBody> downloadFileWithFixedUrl();
}
