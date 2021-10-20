package com.example.notes.helper

import androidx.room.TypeConverter
import com.example.notes.model.NotesColor

class Converter {

    @TypeConverter
    fun fromNoteColor(value:NotesColor) = value.name

    @TypeConverter
    fun toNoteColor(value:String) = enumValueOf<NotesColor>(value)
}