package com.example.baseproject.database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.*;
import com.example.baseproject.models.TaskModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by husaynhakeem on 6/12/17.
 */

@Dao
public interface TaskDao {

    @Insert
    long insert(TaskModel task);

    @Update
    void update(TaskModel... task);

    @Delete
    void delete(TaskModel... task);

    @Query("Select * FROM TaskModel")
    LiveData<List<TaskModel>> taskAll();

    @Query("Select * FROM TaskModel where date >= :minDate AND date<= :maxDate")
    LiveData<List<TaskModel>> taskByDate(long minDate , long maxDate);

    @Query("DELETE FROM TaskModel WHERE id = :id")
    void deleteTask(int id);
}