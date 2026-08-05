package com.enesduvan.taskflow.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.enesduvan.taskflow.roomDB.data.Task
import com.enesduvan.taskflow.roomDB.data.TaskDataBase
import com.enesduvan.taskflow.roomDB.data.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val taskList = mutableStateListOf<Task>()
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
        readAllTaskOrderByDate.observeForever { list ->
            taskList.clear()
            taskList.addAll(list)
        }
    }
    fun onCheckedChange(task: Task, isChecked: Boolean) {
        val index = taskList.indexOf(task)
        val checked : Task
        if (index != -1) {
            taskList[index] = taskList[index].copy(Checked = isChecked)
            checked = taskList[index].copy(Checked = isChecked)
            viewModelScope.launch(Dispatchers.IO) {
                repository.updateTask(checked)
            }
        }

    }

}