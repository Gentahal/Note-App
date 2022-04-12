package com.abdi.noteapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.abdi.noteapp.data.NotesRepostory
import com.abdi.noteapp.data.entity.Notes
import com.abdi.noteapp.data.room.NotesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application): AndroidViewModel(application) {
    private val notesDao = NotesDatabase.getDataBase(application).notesDao()
    private val repostory: NotesRepostory = NotesRepostory(notesDao)

    fun getAllData() : LiveData<List<Notes>> = repostory.getAllData()

    fun insertData(note: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repostory.insertNotes(note)
        }
    }

    fun searchByQuery(query: String) : LiveData<List<Notes>> {
        return repostory.searchByQuery(query)
    }

    fun sortByHighPriority() : LiveData<List<Notes>> = repostory.sortByHighPriority()
    fun sortByLowPriority() : LiveData<List<Notes>> = repostory.sortByLowPriority()



    fun deleteAllData(){
        viewModelScope.launch(Dispatchers.IO) {
            repostory.deleteAllData()
        }
    }

    fun deleteNote(notes : Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            repostory.deleteNote(notes)
        }
    }

    fun updateNote(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            repostory.updateNote(notes)
        }
    }
}