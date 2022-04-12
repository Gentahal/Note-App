package com.abdi.noteapp.data

import androidx.lifecycle.LiveData
import com.abdi.noteapp.data.entity.Notes
import com.abdi.noteapp.data.room.NoteDao

class NotesRepostory(private val notesDao: NoteDao) {

    fun getAllData() : LiveData<List<Notes>> = notesDao.getAllData()

    suspend fun insertNotes(notes: Notes) = notesDao.insertNotes(notes)


    fun sortByHighPriority() : LiveData<List<Notes>> = notesDao.sortByHighPriority()
    fun sortByLowPriority() : LiveData<List<Notes>> = notesDao.sortByLowPriority()

    suspend fun deleteAllData() = notesDao.deleteAllData()

    fun searchByQuery(query: String) : LiveData<List<Notes>> = notesDao.searchByQuery(query)

    suspend fun deleteNote(notes: Notes) = notesDao.deleteNote(notes)

    suspend fun updateNote(notes: Notes) = notesDao.updateNote(notes)
}