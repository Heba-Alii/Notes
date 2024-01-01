package com.example.notes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "NOTE_TABLE")
data class NotesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    //To change name of column
    @ColumnInfo(name = "content")
    val content: String,
    val date: Date,
    val isImportant: Boolean,
    val reminderTime: Int,
    val reminderDate: Int
)
