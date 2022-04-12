package com.abdi.noteapp.data.room

import android.content.Context
import androidx.room.*
import com.abdi.noteapp.data.entity.Notes
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(entities = [Notes::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao() : NoteDao

    companion object{
        @Volatile
        private var instance : NotesDatabase? = null


        @OptIn(InternalCoroutinesApi::class)
        @JvmStatic
        fun getDataBase(context: Context): NotesDatabase{
            if (instance == null){
                synchronized(NotesDatabase::class.java){
                    instance = Room.databaseBuilder(
                        context,
                        NotesDatabase::class.java,
                        "notes.db"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return instance as NotesDatabase
        }
    }
}