package com.example.todoapp.data;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/** creating interface to follow the design pattern
    crud operation
    dao using query method
    contains all methods required to access database
 **/

@Dao
public interface TodoDao {

    @Query("select * from todos order by updated_date DESC")
    LiveData<List<Task>> getAllTasks();

    @Update
    void update(Task task);

    @Insert(onConflict = OnConflictStrategy.IGNORE) //to avoid duplication
    void insert(Task task);

    @Query("delete from todos")
    void deleteAll();

    @Delete
    void deleteTask(Task task);
}