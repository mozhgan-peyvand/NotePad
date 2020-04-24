package com.example.notepad.features.home.ui

import android.icu.text.CaseMap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    /**
     * use live data in normal way with first init
     */

     private var _noteListHome = MutableLiveData<List<NoteItemInfoView>>(listOf())
    val noteListHome: LiveData<List<NoteItemInfoView>>
        get() = _noteListHome
    lateinit var noteItemInfoView: NoteItemInfoView

    /**
     * first init recycler view that extended by list Adapter
     *  without save data in dataBase with retrofit
     *  this fun is for add item to listAdapter with plus
     *  for delete item used minus
     */

    fun addNoteItem( title: String){
         noteItemInfoView = NoteItemInfoView(title)
        _noteListHome.value = _noteListHome.value?.plus(noteItemInfoView)
    }
}