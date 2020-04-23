package com.example.notepad.features.home.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI

import com.example.notepad.R
import com.example.notepad.core.ui.safeNavigate
import com.example.notepad.core.util.autoCleared
import com.example.notepad.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.math.log

class HomeFragment : Fragment() {

    lateinit var navController: NavController

    private var binding by autoCleared<FragmentHomeBinding>()

    var adapter by autoCleared<NoteListAdapter>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()

//        btn_addNote_home.setOnClickListener {
//            safeNavigate(findNavController(),HomeFragmentDirections.actionHomeFragmentToNoteFragment())
//            safeNavigate(findNavController(),R.id.noteFragment,R.id.homeFragment)
//            findNavController().navigate(R.id.action_homeFragment_to_noteFragment )
//        }

    }

    fun initView() {
        adapter = NoteListAdapter(context)
        Toast.makeText(context,"im adapter",Toast.LENGTH_LONG).show()
        binding.noteList.adapter = adapter
        adapter.submitList(mutableListOf(NoteItemInfoView("mozhgan"),NoteItemInfoView("milad")))

    }

}
