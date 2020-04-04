package com.example.notepad.features.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI

import com.example.notepad.R
import com.example.notepad.core.ui.safeNavigate
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_addNote_home.setOnClickListener {
//            safeNavigate(findNavController(),HomeFragmentDirections.actionHomeFragmentToNoteFragment())
//            safeNavigate(findNavController(),R.id.noteFragment,R.id.homeFragment)
            findNavController().navigate(R.id.action_homeFragment_to_noteFragment )
        }

    }

    fun  initValue(){

    }
    fun initView(){

    }


}
