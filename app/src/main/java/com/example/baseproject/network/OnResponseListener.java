package com.example.baseproject.network;

/**
 * created by Rishika Agarwal on 6/24/2019.
 */
public interface OnResponseListener {
    void onResponse(int requestCode, BaseResponse response);
    void onValidationFailure(int requestCode, String message, BaseResponse response);
    void onFailure(int requestCode, Throwable t);
    void commonCall(int requestCode);
}
