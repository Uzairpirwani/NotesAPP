package com.example.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notes.database.NotesDatabase
import com.example.notes.model.Notes
import com.example.notes.repository.NoteRepository

class NotesViewModel(application: Application): AndroidViewModel(application) {

    private val repository:NoteRepository

    init {

        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
         repository = NoteRepository(dao)
    }
    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }
    fun getNotes():LiveData<List<Notes>> =  repository.getAllNotes()

    fun deleteNotes(id:Int){
        repository.deleteNote(id)
    }
    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }
}