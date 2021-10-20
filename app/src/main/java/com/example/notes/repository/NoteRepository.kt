package com.example.notes.repository

import androidx.lifecycle.LiveData
import com.example.notes.dao.NotesDao
import com.example.notes.model.Notes

class NoteRepository(val dao: NotesDao) {

    fun getAllNotes():LiveData<List<Notes>> {
        return dao.getNotes()
    }
    fun insertNotes(notes: Notes){
        dao.insertNote(notes)
    }
    fun deleteNote(id:Int){
        dao.deleteNotes(id)
    }
    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }
}