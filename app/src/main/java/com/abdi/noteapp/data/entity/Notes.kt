package com.abdi.noteapp.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "note_table")
@Parcelize
data class Notes(
    //Untuk id di dalam table supaya tidak duplikat
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var priority: Priority,
    var description: String,
    var date: String
) : Parcelable