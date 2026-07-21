package com.enesduvan.taskflow.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.navigationevent.NavigationEventDispatcher

data class TaskModel(val Id: String, val Task : String, val Description : String, val Date : String, val Priority : String ,val Checked : Boolean) {
    fun PriorityColor(): Color { //kendime not şuanlık priority renkleri statik olarak verdim daha sonra dinamik yapmak lazım mvvm power 😁
        return when (Priority) {
            "Yüksek" -> Color(0xFFFF0000) // Kırmızı
            "Orta" -> Color(0xFFFFA500) // Turuncu
            "Düşük" -> Color(0xFF008000) // Yeşil
            else -> Color(0xFF000000) // Varsayılan siyah
        }
    }
}