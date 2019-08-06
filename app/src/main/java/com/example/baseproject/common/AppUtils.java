package com.example.baseproject.common;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.example.baseproject.activity.BaseActivity;
import com.example.baseproject.activity.MainActivity;
import com.example.baseproject.database.AppDataBase;
import com.example.baseproject.database.DateTypeConverter;
import com.example.baseproject.databinding.FragmentSignUpBinding;
import com.example.baseproject.interfaces.FirebaseOperationListener;
import com.example.baseproject.services.MyService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import static android.content.Context.ALARM_SERVICE;

/**
 * created by Rishika Agarwal on 6/25/2019.
 */
public class AppUtils<T> {

//    public String TAG = getClass().getSimpleName();


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


    private static AlarmManager getAlarmManager(Context context) {
        return (AlarmManager) context.getSystemService(ALARM_SERVICE);
    }

    private boolean doesPendingIntentExist(Intent i, int requestCode, Context context) {
        PendingIntent pi = PendingIntent.getService(context, requestCode, i, PendingIntent.FLAG_NO_CREATE);
        return pi != null;
    }

    public static void createAlarm(Context context,Intent i, long requestCode, long timeInMillis) {
        AlarmManager am = getAlarmManager(context);
        PendingIntent pi = PendingIntent.getService(context, (int)requestCode, i, PendingIntent.FLAG_UPDATE_CURRENT);
        am.set(AlarmManager.RTC_WAKEUP, timeInMillis, pi);
    }

    public static Intent createAlarmIntenet(Context context, long id){
        Intent intent = new Intent(context , MyService.class);
        intent.putExtra(AppConstant.INTENT_ID , id);
        return intent;

    }

    public static boolean isUserLogIn(FirebaseAuth mAuth){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        return currentUser!=null;
    }


    public static void firebaseSignUpEmailPass(FirebaseAuth mAuth , String email , String password , Activity context , FirebaseOperationListener listener){
        mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                UIUtils.showToast(context , "Create Account Successful");
                listener.firebaseOperationSuccess(authResult , FirebaseAuth.class);
            }
        })
                .addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

listener.firebaseOperationCompleted(task , FirebaseAuth.class);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                UIUtils.showToast(context , "Fail");
                listener.firebaseOperationFailed(e , FirebaseAuth.class);
            }
        });

    }



    public static void firebaseLogInEmailPass(FirebaseAuth mAuth , String email , String password , Activity context , FirebaseOperationListener listener) {

        mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                listener.firebaseOperationSuccess(authResult , FirebaseAuth.class);
                UIUtils.showToast(context , "LogIn SuccessFul");
            }
        }).addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       listener.firebaseOperationCompleted(task , FirebaseAuth.class);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.firebaseOperationFailed(e , FirebaseAuth.class);

            }
        });


    }


    public static void signOut(){
        FirebaseAuth.getInstance().signOut();

    }

    public static void saveDataToFireStore(Map<String,String> map , FirebaseFirestore db , Activity context , String email , FirebaseOperationListener listener){

//        DocumentReference messageRef = db
//                .collection("rooms").document("roomA")
//                .collection("messages").document("message1");
// Add a new document with a generated ID
        db.collection("usersDetail").document(email).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                UIUtils.showToast(context , "Success");
                listener.firebaseOperationSuccess("success" , FirebaseFirestore.class);
            }
        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        UIUtils.showToast(context , "Fail");
                        listener.firebaseOperationFailed("Fail" , FirebaseFirestore.class);
                    }
                }).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                listener.firebaseOperationCompleted(task , FirebaseFirestore.class);
            }
        });
    }


    public static void readDataFromFirestore(FirebaseFirestore db , String email){

//            DocumentReference user = db.collection("usersDetail").document(email);
//            user.get().addOnCompleteListener(new OnCompleteListener <DocumentSnapshot> () {
//                @Override
//                public void onComplete(@NonNull Task < DocumentSnapshot > task) {
//                    if (task.isSuccessful()) {
//                        DocumentSnapshot doc = task.getResult();
//                        StringBuilder fields = new StringBuilder("");
//                        fields.append("Name: ").append(doc.get("Name"));
//                        fields.append("\nEmail: ").append(doc.get("Email"));
//                        fields.append("\nPhone: ").append(doc.get("Phone"));
//                        textDisplay.setText(fields.toString());
//                    }
//                }
//            })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                        }
//                    });

    }

}
