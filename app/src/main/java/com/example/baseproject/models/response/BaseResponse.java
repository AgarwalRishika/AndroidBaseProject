package com.example.baseproject.models.response;

import retrofit2.Response;

/**
 * created by Rishika Agarwal on 6/25/2019.
 */
public class BaseResponse<T> {
    public String status ;
    public int totalResults ;
    T response;

}
