package com.enesduvan.taskflow.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.enesduvan.taskflow.presentation.PriorityColor

class AddTaskViewModel : ViewModel() {
    var taskName = mutableStateOf("")
    var taskDescription = mutableStateOf("")
    var selectedPriority = mutableStateOf("Medium") // otomatik olarak orta


    fun onTaskNameChange(newName: String) {
        taskName.value = newName
    }
    fun onTaskDescriptionChange(newDescription: String) {
        taskDescription.value = newDescription
    }
    fun onSelectedPriorityChange(newPriority: String) {
        selectedPriority.value = newPriority
        PriorityColor(selectedPriority.value)
    }
    fun onDateChange(newDate: String) {
        // tarihi telefondan almak lazım
    }
    fun onTimeChange(newTime: String) {
        // görebin ne kadar süre kalacağı bwelirlenebilir
    }
    /*
    fun PriorityColor(): Color { //kendime not şuanlık priority renkleri statik olarak verdim daha sonra dinamik yapmak lazım mvvm power 😁
        return when (selectedPriority.value) {
            "High" -> Color(0xFFFF0000) // Kırmızı
            "Medium" -> Color(0xFFFFA500) // Turuncu
            "Low" -> Color(0xFF008000) // Yeşil
            else -> Color(0xFF000000) // Varsayılan siyah
        }
    }
    */
}