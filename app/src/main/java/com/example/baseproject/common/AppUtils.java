package com.example.baseproject.common;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.example.baseproject.activity.BaseActivity;
import com.example.baseproject.database.AppDataBase;
import com.example.baseproject.database.DateTypeConverter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.ALARM_SERVICE;

/**
 * created by Rishika Agarwal on 6/25/2019.
 */
public class AppUtils<T> {


    public static void startActivity(Context context , Class c){
        Intent intent = new Intent(context , c);
        context.startActivity(intent);
    }


    public static void insertTask(AppDataBase appDataBase){

    }

    public static void checkPermission(AppCompatActivity activity, String[] permissions, int code){
        ArrayList arrayList = new ArrayList();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
arrayList.add(permission);
//           return PackageManager.PERMISSION_DENIED;
            }
        }

        if(arrayList.size()>0){
            askPermission(activity,(String[])arrayList.toArray(),code);
        }
//    return PackageManager.PERMISSION_GRANTED;


    }


    public static void askPermission(AppCompatActivity activity, String[] permissions , int code){
        ActivityCompat.requestPermissions(activity, permissions, code);
    }


    public static void openImageGallery(AppCompatActivity activity , int code){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"),code);
    }

    public static void openVideoGallery(AppCompatActivity activity , int code){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");
        activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"),code);
    }

    public static File getOutputFileFromGallery(AppCompatActivity activity , Intent data) {
        File outputFile = null;
        try {
            outputFile = new File(getGalleryImagePath(activity, data.getData()));
        } catch (Exception e) {
//            LogsManager.printLog(e);
        }

        return outputFile;
    }


    public static String getGalleryImagePath(Context context, Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};

        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(projection[0]);
        String picturePath = cursor.getString(columnIndex); // returns null
        cursor.close();
        return picturePath;
    }


    public static String convertListToString(ArrayList<String> arrayList){
        StringBuilder stringBuilder = new StringBuilder();
        for (String s :  arrayList){
            stringBuilder.append(s);
            stringBuilder.append("\n");
        }

        String string = stringBuilder.substring(0,stringBuilder.length()-1);
        return string;
    }

    public  static ArrayList<String> convertStringtoList(String string){
        ArrayList<String> arrayList;
       arrayList = new ArrayList<>(Arrays.asList(string.split("\n")));
       return arrayList;
    }

    public static long getCurrentDate(){
       return Calendar.getInstance().getTimeInMillis();

    }


    public static String parseDateFormat(Date date , String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String s = simpleDateFormat.format(date);

        return s;
    }

    public static boolean  isStringEmpty(String string) {
        if(string == null || string.trim().isEmpty()){
            return true;

        }
        return false;
    }

    public static boolean isListEmpty(ArrayList permList) {
        if(permList == null || permList.size() == 0){
            return true;
        }
        return false;
    }


    public static void animVibrate(View animateView){
        ObjectAnimator rotate = ObjectAnimator.ofFloat(animateView, "rotation", 0f, 20f, 0f, -20f, 0f); // rotate o degree then 20 degree and so on for one loop of rotation.
// animateView (View object)
        rotate.setRepeatCount(10); // repeat the loop 20 times
        rotate.setDuration(80); // animation play time 100 ms
        rotate.start();
    }


    private AlarmManager getAlarmManager(Context context) {
        return (AlarmManager) context.getSystemService(ALARM_SERVICE);
    }

    private boolean doesPendingIntentExist(Intent i, int requestCode, Context context) {
        PendingIntent pi = PendingIntent.getService(context, requestCode, i, PendingIntent.FLAG_NO_CREATE);
        return pi != null;
    }

    private void createAlarm(Context context,Intent i, int requestCode, long timeInMillis) {
        AlarmManager am = getAlarmManager(context);
        PendingIntent pi = PendingIntent.getService(context, requestCode, i, PendingIntent.FLAG_UPDATE_CURRENT);
        am.set(AlarmManager.RTC_WAKEUP, timeInMillis, pi);
//        Log.d("OskarSchindler", "createAlarm "+requestCode+" time: "+timeInMillis+" PI "+pi.toString());
    }
}
