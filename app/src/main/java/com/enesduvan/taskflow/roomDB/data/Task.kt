package com.enesduvan.taskflow.roomDB.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    @ColumnInfo(name = "Id")
    @PrimaryKey(autoGenerate = true)
    val Id: Int,
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