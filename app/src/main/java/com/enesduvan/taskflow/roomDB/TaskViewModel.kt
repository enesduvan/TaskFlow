package com.enesduvan.taskflow.roomDB

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    suspend fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) { //eşzamanlı parçacık işlem thread
            repository.addTask(task)
        }
    }
    suspend fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTask(task)
        }
    }
    suspend fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(task)
        }
    }

}