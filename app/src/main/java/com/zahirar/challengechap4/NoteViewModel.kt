package com.zahirar.challengechap4

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.zahirar.challengechap4.room.DataNote
import com.zahirar.challengechap4.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class NoteViewModel(app: Application) : AndroidViewModel(app) {
    var allNotes : MutableLiveData<List<DataNote>?>

    init {
        allNotes = MutableLiveData()
        getAllNotes()
    }

    fun getAllNotesObserver() : MutableLiveData<List<DataNote>?> {
        return allNotes
    }

    fun getAllNotes(){
        GlobalScope.launch {
            val notesDAO = NoteDatabase.getInstance((getApplication()))!!.noteDao()
            val listNotes = notesDAO.getDataNote()
            allNotes.postValue(listNotes)
        }
    }

    fun insertNotes(notes: DataNote){
        GlobalScope.async {
            val notesDAO = NoteDatabase.getInstance((getApplication()))!!.noteDao()
            notesDAO.insertNote(notes)
            getAllNotes()
        }
    }

    fun deleteNotes(notes : DataNote){
        GlobalScope.launch {
            val notesDAO = NoteDatabase.getInstance((getApplication()))!!.noteDao()
            notesDAO.deleteNote(notes)
            getAllNotes()
        }
    }

    fun updateNotes(notes: DataNote){
        GlobalScope.async {
            val notesDAO = NoteDatabase.getInstance((getApplication()))!!.noteDao()
            notesDAO.updateNote(notes)
            getAllNotes()
        }
    }
}