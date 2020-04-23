package com.example.notepad.features.note.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.notepad.R
import com.example.notepad.core.util.autoCleared
import com.example.notepad.databinding.FragmentNoteBinding

class NoteFragment : Fragment() {

    private var binding by autoCleared<FragmentNoteBinding>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding =DataBindingUtil.inflate(inflater,R.layout.fragment_note, container, false)
        return binding.root
    }



}
