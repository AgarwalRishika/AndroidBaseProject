package com.example.baseproject.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.baseproject.common.AppConstant;
import com.example.baseproject.models.TaskModel;

/**
 * Created by husaynhakeem on 6/12/17.
 */

@Database(entities = {TaskModel.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {



    public abstract TaskDao taskDao();




}
