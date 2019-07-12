package com.example.baseproject.database;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.*;
import com.example.baseproject.common.AppConstant;
import com.example.baseproject.common.AppUtils;
import com.example.baseproject.interfaces.AfterDbOperationListener;
import com.example.baseproject.models.TaskModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * created by Rishika Agarwal on 7/2/2019.
 */

public class TaskRespository {
    

    @Inject
    AppDataBase appDataBase;
    AfterDbOperationListener listener;

    @Inject
    public TaskRespository(){}


    public void insertTask(final TaskModel task) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                appDataBase.taskDao().insert(task);
                return 1;
            }

            @Override
            protected void onPostExecute(Integer result) {
                super.onPostExecute(result);
                listener.dbOperationCompleted(result);
            }
        }.execute();
    }


    public void setListener(AfterDbOperationListener listener){
        this.listener = listener;
    }

//    public void updateTask(final Note note) {
//        note.setModifiedAt(AppUtils.getCurrentDateTime());
//
//        new AsyncTask<Void, Void, Void>() {
//            @Override
//            protected Void doInBackground(Void... voids) {
//                appDataBase.daoAccess().updateTask(note);
//                return null;
//            }
//        }.execute();
//    }
//
//    public void deleteTask(final int id) {
//        final LiveData<Note> task = getTask(id);
//        if(task != null) {
//            new AsyncTask<Void, Void, Void>() {
//                @Override
//                protected Void doInBackground(Void... voids) {
//                    appDataBase.daoAccess().deleteTask(task.getValue());
//                    return null;
//                }
//            }.execute();
//        }
//    }
//
//    public void deleteTask(final Note note) {
//        new AsyncTask<Void, Void, Void>() {
//            @Override
//            protected Void doInBackground(Void... voids) {
//                appDataBase.daoAccess().deleteTask(note);
//                return null;
//            }
//        }.execute();
//    }
//
//    public LiveData<Note> getTask(int id) {
//        return appDataBase.daoAccess().getTask(id);
//    }
//
public LiveData<List<TaskModel>> getTasks() {
    return appDataBase.taskDao().taskAll();
}

public LiveData<List<TaskModel>> getTasksByDate(long minDate , long maxDate){
        return appDataBase.taskDao().taskByDate(minDate , maxDate);
}
}

