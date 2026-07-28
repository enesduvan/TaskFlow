package com.enesduvan.taskflow.roomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    @ColumnInfo(name = "Id")
    @PrimaryKey(autoGenerate = true)
    val Id: String,
    @ColumnInfo(name = "Task")
    val Task : String,
    @ColumnInfo(name = "Description")
    val Description : String,
    @ColumnInfo(name = "Date")
    val Date : String,
    @ColumnInfo(name = "Priority")
    val Priority : String ,
    @ColumnInfo(name = "Checked")
    val Checked : Boolean
)

//dao veriler burada tutulur aslında sql varlık tablo ilişkisi gibi
/* //normalizasyon kurallarına aykırı ama ne yapalım basit uygulamada gerk yok
task_table

id idendity primary key     - 1
task string                 - alışveriş
description string          - 2 adet ekmek al
date string                 - bugun
priority string             - yüksek
checked boolean             - false
*/