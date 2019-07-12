package com.example.baseproject.common;

/**
 *created by Rishika Agarwal on 5/16/2019.
 */

public class AppConstant{

    public static final String BASE_URL ="https://newsapi.org/v2/";
    public static final String NEWS_API_KEY = "7170bf3522c44c53a61aefc454ac2a85";
    public static final String TOP_HEADLINES = "top-headlines?country=us&apiKey="+NEWS_API_KEY;
    public static final String DATABASE_NME = "to_do.db";

    public static final int PERMISSION_GRANTED = 0;
    public static final int PERMISSION_DENIED = -1;

    public static final int PERMISSION_CAMERA = 1;
    public static final int PERMISSION_GALLERY_IMAGE = 2;
    public static final int PERMISSION_GALLERY_VIDEO = 3;


    public static final int RESULT_GALLERY_IMAGE = 21 ;
    public static final int RESULT_GALLERY_VIDEO = 22 ;

    public static final String FULL_DATE_FORMAT = "MM-dd-yyyy hh:mm:ss";
    public static final String DATE_FORMAT = "MM-dd-yyyy";
    public static final String TIME_FORMAT = "hh:mm:ss";

}