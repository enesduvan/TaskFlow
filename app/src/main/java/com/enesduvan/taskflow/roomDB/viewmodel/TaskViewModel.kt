package com.enesduvan.taskflow.roomDB.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.enesduvan.taskflow.roomDB.data.Task
import com.enesduvan.taskflow.roomDB.data.TaskDataBase
import com.enesduvan.taskflow.roomDB.data.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val readAllTaskOrderByDate : LiveData<List<Task>>
    private val readAllTaskOrderByPriority : LiveData<List<Task>>
    private val getTaskById : LiveData<Task>
    private val repository : TaskRepository
    init {
        val taskDao = TaskDataBase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        readAllTaskOrderByDate = repository.allTasksOrderedByDate
        readAllTaskOrderByPriority = repository.allTasksOrderedByPriority
        getTaskById = repository.getTaskById
    }
    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) { //eşzamanlı parçacık işlem thread
            repository.addTask(task)
        }
    }
    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTask(task)
        }
    }
    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(task)
        }
    }

}