package com.example.a2023_2_proj_final_hush_hush_app.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2023_2_proj_final_hush_hush_app.R
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityTermsOfUseBinding
import com.example.a2023_2_proj_final_hush_hush_app.databinding.FragmentUserProfileBinding
import com.example.a2023_2_proj_final_hush_hush_app.viewModel.UserProfileViewModel

class UserProfileFragment : Fragment(R.layout.fragment_user_profile) {
    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!
//    companion object {
//        fun newInstance() = UserProfileFragment()
//    }

    private lateinit var viewModel: UserProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        binding.recyclerListHushHush.layoutManager = LinearLayoutManager(this.context) // pode dar erro
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[UserProfileViewModel::class.java]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}