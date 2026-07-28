    package com.enesduvan.taskflow.roomDB

    import androidx.lifecycle.LiveData

    class TaskRepository(private val taskDao: TaskDao) {
        val allTasksOrderedByDate: LiveData<List<Task>> = taskDao.getTasksOrderedByDate()
        val allTasksOrderedByPriority: LiveData<List<Task>> = taskDao.getTasksOrderedByPriority()
        val getTaskById: LiveData<Task> = taskDao.getTaskById("")

        suspend fun addTask(task: Task) {
            taskDao.insertTask(task)
        }
        suspend fun updateTask(task: Task) {
            taskDao.updateTask(task)
        }
        suspend fun deleteTask(task: Task) {
            taskDao.deleteTask(task)
        }
    }