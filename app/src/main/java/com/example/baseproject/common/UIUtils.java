package com.example.baseproject.common;


import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * created by Rishika Agarwal on 6/26/2019.
 */
public class UIUtils {

    public static AlertDialog createDialog(Context context , String message , String title , String posString , DialogInterface.OnClickListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
         builder.setMessage(message);
         builder.setTitle(title);
         builder.setPositiveButton(posString, listener);
        return builder.create();


    }


    public static void showToast(AppCompatActivity activity,String msg){
        Toast.makeText(activity ,msg, Toast.LENGTH_LONG).show();
    }
}
