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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
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

    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel = ViewModelProviders.of(requireActivity())[HomeViewModel::class.java]

        initView()

//        btn_addNote_home.setOnClickListener {
//            safeNavigate(findNavController(),HomeFragmentDirections.actionHomeFragmentToNoteFragment())
//            safeNavigate(findNavController(),R.id.noteFragment,R.id.homeFragment)
//            findNavController().navigate(R.id.action_homeFragment_to_noteFragment )
//        }

    }

    @SuppressLint("FragmentLiveDataObserve")
    fun initView() {

        /**
         * init adapter and get it to bind
        * */

        Toast.makeText(context, "im adapter", Toast.LENGTH_LONG).show()
        adapter = NoteListAdapter(context)
        binding.noteList.adapter = adapter

        /**
         * first init adapter for test or other things
         * */

//        adapter.submitList(mutableListOf(NoteItemInfoView("mozhgan"),NoteItemInfoView("milad")))

        /**
         * init adapter after change
        * */

        homeViewModel.noteListHome.observe(viewLifecycleOwner, Observer {
            val uiModel = it
            adapter.submitList(uiModel.toMutableList())
        })

        /**
         * add button
        * */

        btn_addNote_home.setOnClickListener {
            homeViewModel.addNoteItem(et_titleNote_home.text.toString())
        }

    }

}
