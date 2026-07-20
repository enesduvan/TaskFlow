package com.enesduvan.taskflow.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.room.util.copy

class HomeViewModel : ViewModel() {

    // 1. Görev listesini Compose'un izleyebileceği bir state listesi olarak tutuyoruz
    val taskList = mutableStateListOf<TaskModel>()

    init {
        taskList.addAll(
            listOf(
        TaskModel(
            "Market Alışverişi",
            "Süt, ekmek, yumurta ve meyve al",
            "21.07.2026",
            "Yüksek",
            false
        ),
        TaskModel(
            "Ödevi Bitir",
            "Kotlin HomeScreen ödevini tamamla",
            "22.07.2026",
            "Orta",
            false
        ),
        TaskModel(
            "Spor Yap",
            "45 dakika koşu ve 15 dakika esneme",
            "21.07.2026",
            "Düşük",
            true
        ),
        TaskModel(
            "Doktor Randevusu",
            "Saat 14:30'da diş kontrolüne git",
            "23.07.2026",
            "Yüksek",
            false
        ),
        TaskModel(
            "Kitap Oku",
            "Kotlin TaskFlow uygulaması 1 saat kod yaz",
            "24.07.2026",
            "Orta",
            true
        ),
        TaskModel(
            "İlaç İç",
            "İlaçlarını almayı unutma",
            "24.07.2026",
            "Yüksek",
            true
        ),
        TaskModel(
            "Uyu",
            "Uyumayı unutma 😁",
            "24.07.2026",
            "Orta",
            true
        ),
                TaskModel(
                    "Flipping Master UI",
                    "Pazar yeri ekranı için Jetpack Compose bileşenlerini tasarla (V1).",
                    "22.07.2026",
                    "Yüksek",
                    false
                ),
                TaskModel(
                    "Yapay Zeka Araştırması",
                    "Derin öğrenme dersi için LLM istatistiksel sapmaları hakkında notlar çıkar.",
                    "24.07.2026",
                    "Orta",
                    false
                ),
                TaskModel(
                    "Halı Saha",
                    "7'ye 7 maç için Ali ve diğerlerini organize et, kadroyu kur.",
                    "25.07.2026",
                    "Orta",
                    false
                ),
                TaskModel(
                    "Kafa Dağıtma",
                    "Cities: Skylines II'de yeni modları dene veya The 100'den birkaç bölüm izle.",
                    "21.07.2026",
                    "Düşük",
                    false
                )

            )
        )
    }

    // 2. Checkbox'a tıklandığında çalışacak fonksiyon
    fun onCheckedChange(task: TaskModel, isChecked: Boolean) {
        // Tıklanan görevin listedeki sırasını (index) buluyoruz
        val index = taskList.indexOf(task)

        if (index != -1) {
            // Compose'un değişikliği fark edip ekranı çizmesi için,
            // eski elemanı alıp, sadece 'checked' değerini değiştirerek YENİ bir kopya ile eziyoruz.
            taskList[index] = taskList[index].copy(Checked = isChecked)
        }
    }
}