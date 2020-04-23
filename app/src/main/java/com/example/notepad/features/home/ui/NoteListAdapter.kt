package com.example.notepad.features.home.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.DiffUtil
import com.example.notepad.R
import com.example.notepad.core.ui.safeNavigate
import com.example.notepad.core.util.binding.DataBoundListAdapter
import com.example.notepad.databinding.ItemNoteListBinding

class NoteListAdapter (private val context: Context?):
    DataBoundListAdapter<NoteItemInfoView, ItemNoteListBinding>(
        diffCallback = object : DiffUtil.ItemCallback<NoteItemInfoView>() {
            override fun areItemsTheSame(
                oldItem: NoteItemInfoView,
                newItem: NoteItemInfoView
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: NoteItemInfoView,
                newItem: NoteItemInfoView
            ): Boolean {
                return oldItem.title == newItem.title
            }
        }
    ) {
//    private val context: Context

    override fun createBinding(parent: ViewGroup): ItemNoteListBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_note_list,
            parent,
            false
        )
    }

    @SuppressLint("ShowToast", "LogNotTimber")
    override fun bind(binding: ItemNoteListBinding, item: NoteItemInfoView, position: Int) {
        binding.apply {

            noteItem = item
            root.setOnClickListener {
                noteItem.let {
                    Log.d("you click on me","yes im work")
                }
            }

        }
    }

    private var noteItemInfoView: MutableList<NoteItemInfoView>? = null

    override fun submitList(list: MutableList<NoteItemInfoView>?) {
        noteItemInfoView = list
        super.submitList(list)
    }

}