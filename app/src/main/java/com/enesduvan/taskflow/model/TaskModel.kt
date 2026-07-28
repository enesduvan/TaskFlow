package com.enesduvan.taskflow.model

import androidx.compose.ui.graphics.Color

data class TaskModel(val Id: String, val Task : String, val Description : String, val Date : String, val Priority : String ,val Checked : Boolean) {
    fun PriorityColor(): Color { //kendime not şuanlık priority renkleri statik olarak verdim daha sonra dinamik yapmak lazım mvvm power 😁
        return when (Priority) {
            "High" -> Color(0xFFFF0000) // Kırmızı
            "Medium" -> Color(0xFFFFA500) // Turuncu
            "Low" -> Color(0xFF008000) // Yeşil
            else -> Color(0xFF000000) // Varsayılan siyah
        }
    }
}