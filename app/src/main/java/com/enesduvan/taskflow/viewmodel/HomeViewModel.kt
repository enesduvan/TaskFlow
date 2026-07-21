package com.enesduvan.taskflow.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.room.util.copy

class HomeViewModel : ViewModel() {
    val taskList = mutableStateListOf<TaskModel>()

    init {
        taskList.addAll(
            listOf(
        TaskModel(
            "1",
            "Market Alışverişi",
            "Süt, ekmek, yumurta ve meyve al",
            "21.07.2026",
            "Yüksek",
            false
        ),
        TaskModel(
            "2",
            "Ödevi Bitir",
            "Kotlin HomeScreen ödevini tamamla",
            "22.07.2026",
            "Orta",
            false
        ),
        TaskModel(
            "3",
            "Spor Yap",
            "45 dakika koşu ve 15 dakika esneme",
            "21.07.2026",
            "Düşük",
            true
        ),
        TaskModel(
            "4",
            "Doktor Randevusu",
            "Saat 14:30'da diş kontrolüne git",
            "23.07.2026",
            "Yüksek",
            false
        ),
        TaskModel(
            "5",
            "Kitap Oku",
            "Kotlin TaskFlow uygulaması 1 saat kod yaz",
            "24.07.2026",
            "Orta",
            true
        ),
        TaskModel(
            "6",
            "İlaç İç",
            "İlaçlarını almayı unutma",
            "24.07.2026",
            "Yüksek",
            true
        ),
        TaskModel(
            "7",
            "Uyu",
            "Uyumayı unutma 😁",
            "24.07.2026",
            "Orta",
            true
        ),
                TaskModel(
                    "8",
                    "Flipping Master UI",
                    "Pazar yeri ekranı için Jetpack Compose bileşenlerini tasarla (V1).",
                    "22.07.2026",
                    "Yüksek",
                    false
                ),
                TaskModel(
                    "9",
                    "Yapay Zeka Araştırması",
                    "Derin öğrenme dersi için LLM istatistiksel sapmaları hakkında notlar çıkar.",
                    "24.07.2026",
                    "Orta",
                    false
                ),
                TaskModel(
                    "10",
                    "Halı Saha",
                    "7'ye 7 maç için Ali ve diğerlerini organize et, kadroyu kur.",
                    "25.07.2026",
                    "Orta",
                    false
                ),
                TaskModel(
                    "11",
                    "Kafa Dağıtma",
                    "Cities: Skylines II'de yeni modları dene veya The 100'den birkaç bölüm izle.",
                    "21.07.2026",
                    "Düşük",
                    false
                )

            )
        )
    }


    fun onCheckedChange(task: TaskModel, isChecked: Boolean) {
        val index = taskList.indexOf(task)
        if (index != -1) {
            taskList[index] = taskList[index].copy(Checked = isChecked)
        }
    }
}