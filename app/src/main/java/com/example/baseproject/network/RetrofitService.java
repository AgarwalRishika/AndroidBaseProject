package com.example.baseproject.network;

import com.example.baseproject.common.AppConstant;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * created by Rishika Agarwal on 6/24/2019.
 */
public interface RetrofitService {


    @GET(AppConstant.TOP_HEADLINES)
    Call<BaseResponse> getTopHeadlines();

}
