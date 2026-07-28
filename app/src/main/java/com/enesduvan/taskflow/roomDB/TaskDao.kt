package com.enesduvan.taskflow.roomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface TaskDao {
    //homescreende görev listelemesi için tarih sıralı
    @Query("SELECT * FROM task_table ORDER BY Date ASC")
    fun getTasksOrderedByDate(): LiveData<List<Task>>
    //homescreende görev listelemesi için öncelik sıralı
    @Query("SELECT * FROM task_table ORDER BY Priority ASC")
    fun getTasksOrderedByPriority(): LiveData<List<Task>>
    //detailscreen için id ile görev çekme
    @Query("SELECT * FROM task_table WHERE Id = :id")
    fun getTaskById(id: String): LiveData<Task>

    @Insert
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}
/*
sql sorguları gibi düşün
tabloya crud işlemi burada yaplırı buradan fonksiyona istek atacağız sadece
suspend senkron işlemde kullanılır
//NOT ORDER BY ile güne göre sıraladım sonra dan önceliğe göre olursa değiştiririm **** -/

*/