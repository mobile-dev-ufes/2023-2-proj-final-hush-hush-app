package com.example.a2023_2_proj_final_hush_hush_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class CreateHushHushFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        super.onCreateView(inflater, container, savedInstanceState)
        val fragView = inflater.inflate(R.layout.fragment_create_hush_hush, container, false)
        return fragView

    }



}