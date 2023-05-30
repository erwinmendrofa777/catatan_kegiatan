package com.example.todoapp.data;


import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This abstract class is the Database
 * contains the database holder and serves as the main access point for the underlying connection to your app's persisted, relational data
 */

@Database(entities = {Task.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public  abstract class AppDatabase extends RoomDatabase {

    public  static AppDatabase INSTANCE;
    public abstract TodoDao todoDao();
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public static AppDatabase getDatabase(Context context){

        //if instance is null create a new instance else return the existing instance

        if(INSTANCE == null){
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "todo.db")
                            //.allowMainThreadQueries()
                            .addCallback(CALLBACK)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback CALLBACK = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {

            super.onCreate(db);

            AppDatabase.databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    TodoDao dao = INSTANCE.todoDao();
                    dao.deleteAll();



                    Task task = new Task("Sample Task", "Initial Tasks", new Date(),new Date(), 1);
                    dao.insert(task);


                    task = new Task("First Task", "Todo is ordered by date", new Date(),new Date(), 1);
                    dao.insert(task);
                }
            });
        }


        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
}