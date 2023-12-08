package com.example.a2023_2_proj_final_hush_hush_app.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2023_2_proj_final_hush_hush_app.R
import com.example.a2023_2_proj_final_hush_hush_app.clients.RetrofitClient
import com.example.a2023_2_proj_final_hush_hush_app.databinding.FragmentUserProfileBinding
import com.example.a2023_2_proj_final_hush_hush_app.responses.post.IndexResponse
import com.example.a2023_2_proj_final_hush_hush_app.services.PostService
import com.example.a2023_2_proj_final_hush_hush_app.ui.view.ListHushHushAdapter
import com.example.a2023_2_proj_final_hush_hush_app.utils.Preferences
import com.example.a2023_2_proj_final_hush_hush_app.viewModel.UserProfileViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale


class UserProfileFragment : Fragment(R.layout.fragment_user_profile) {
    private var _binding: FragmentUserProfileBinding? = null
    private lateinit var sp: Preferences

    private val binding get() = _binding!!
    private var postService = RetrofitClient.createService(PostService::class.java)


//    companion object {
//        fun newInstance() = UserProfileFragment()
//    }

    private lateinit var viewModel: UserProfileViewModel
    private val adapter = ListHushHushAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        sp = Preferences(requireContext().applicationContext)
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        binding.recyclerListHushHush.layoutManager = LinearLayoutManager(this.context) // pode dar erro
        binding.recyclerListHushHush.adapter = adapter
        this.getListHushHush()
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

    private fun getListHushHush(){
        val currentLocale: Locale = resources.configuration.locales[0]
        val language: String = currentLocale.language
        val token = sp.getToken()

        val call = postService.indexByLoggedUser(token, language)

        call.enqueue(object: Callback<IndexResponse> {
            override fun onResponse(call: Call<IndexResponse>, response: Response<IndexResponse>) {
                if(response.isSuccessful) {
                   adapter.updateHushHushList(response.body()!!.data)

                }else{
                    showToast("An error has occurred.")
                }

            }

            override fun onFailure(call: Call<IndexResponse>, t: Throwable) {
                showToast("Internal Server Error!")
            }
        })

    }

    fun showToast(message: String) {
        Toast.makeText(requireContext().applicationContext, message, Toast.LENGTH_LONG).show()
    }
}