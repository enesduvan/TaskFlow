package com.enesduvan.taskflow.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.jvm.java

@Database(entities = [Task::class], version = 1 , exportSchema = false)
//task classımdan alacak ve dışa kapalı şema seçtim sadece buradan
abstract class TaskDataBase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    //senkron ve singelton işlem
    companion object {
        @Volatile
        private var INSTANCE: TaskDataBase? = null
        fun getDatabase(context: Context): TaskDataBase {
            return INSTANCE ?: synchronized(this) { //ekran donma kilitlenmeyi önleyecek
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDataBase::class.java,
                    "task_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}