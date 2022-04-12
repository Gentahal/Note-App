package com.abdi.noteapp.data.room

import androidx.room.TypeConverter
import com.abdi.noteapp.data.entity.Priority

class Converter {

    // Ini untuk konvert sebuah enum class menjadi string
    // fungsi ini di panggil ketika get sebuah data base
    @TypeConverter
    fun fromPriority(priority: Priority) : String{
        return priority.name
    }

    // Ini untuk konvert sebuah string menjadi enum class
    // fungsi ini di panggil ketika add dan update
    @TypeConverter
    fun toPriority(priority: String) : Priority {
        return Priority.valueOf(priority)
    }
}