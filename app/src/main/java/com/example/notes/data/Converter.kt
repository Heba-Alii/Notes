package com.example.notes.data

import androidx.room.TypeConverter
import java.util.Date

class Converter {
    @TypeConverter
    fun dateToLong(date: Date) = date.time

    @TypeConverter
    fun longToDate(long: Long): Date {
        return Date(long)
    }
}