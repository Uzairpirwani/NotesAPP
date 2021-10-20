package com.example.notes.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.model.Notes

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes")

    fun getNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(notes: Notes)

    @Query("DELETE From notes where id =:id")
    fun deleteNotes(id: Int)

    @Update
    fun updateNotes(notes: Notes)
}
