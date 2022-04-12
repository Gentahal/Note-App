package com.abdi.noteapp.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.abdi.noteapp.data.entity.Notes

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNotes(notes: Notes)

    @Query("SELECT * FROM note_table")
    fun  getAllData() : LiveData<List<Notes>>

    @Query("SELECT * FROM note_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority() : LiveData<List<Notes>>

    @Query("SELECT * FROM note_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun sortByLowPriority() : LiveData<List<Notes>>

    @Query("DELETE FROM note_table")
    suspend fun deleteAllData()

    @Query("SELECT * FROM note_table WHERE title LIKE :query")
    fun searchByQuery(query: String) : LiveData<List<Notes>>

    @Delete
    suspend fun deleteNote(notes: Notes)

    @Update
    suspend fun updateNote(notes: Notes)
}