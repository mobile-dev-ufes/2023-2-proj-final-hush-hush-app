package com.example.a2023_2_proj_final_hush_hush_app.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2023_2_proj_final_hush_hush_app.R
import com.example.a2023_2_proj_final_hush_hush_app.clients.RetrofitClient
import com.example.a2023_2_proj_final_hush_hush_app.databinding.FragmentHomeBinding
import com.example.a2023_2_proj_final_hush_hush_app.databinding.FragmentUserProfileBinding
import com.example.a2023_2_proj_final_hush_hush_app.interfaces.CardViewHolderClickListener
import com.example.a2023_2_proj_final_hush_hush_app.responses.post.IndexResponse
import com.example.a2023_2_proj_final_hush_hush_app.services.PostService
import com.example.a2023_2_proj_final_hush_hush_app.ui.view.ListHushHushAdapter
import com.example.a2023_2_proj_final_hush_hush_app.utils.Preferences
import com.example.a2023_2_proj_final_hush_hush_app.viewModel.HomeViewModel
import com.example.a2023_2_proj_final_hush_hush_app.viewModel.LoginViewModel
import com.example.a2023_2_proj_final_hush_hush_app.viewModel.UserProfileViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class HomeFragment : Fragment(R.layout.fragment_home), View.OnClickListener, CardViewHolderClickListener {
    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeVM: HomeViewModel
    private lateinit var sp: Preferences

    private val binding get() = _binding!!
    private var postService = RetrofitClient.createService(PostService::class.java)

    private val adapter = ListHushHushAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)

        sp = Preferences(requireContext().applicationContext)
        homeVM = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.recyclerListHushHush.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerListHushHush.adapter = adapter

        binding.cardSearch.searchHushHush.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                homeVM.setSearch(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.cardSearch.searchButton.setOnClickListener(this)

        this.setObserver()
        this.getListHushHush()
        return binding.root
    }

    override fun onClickCard(index: Int) {
        val hushHush = adapter.getHushHushByIndex(index)
        val action = HomeFragmentDirections.actionHomeFragmentToShowHushHushFragment(hushHush.id)
        findNavController().navigate(action)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.search_button) {
            this.handleClickSearchButton()
        }
    }

    private fun handleClickSearchButton() {
        val search = homeVM.search().value
        this.getListHushHush(search)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getListHushHush(search: String? = null){
        val currentLocale: Locale = resources.configuration.locales[0]
        val language: String = currentLocale.language
        val token = sp.getToken()

        val title = if (search == "") {
            null
        } else {
            search
        }

        val call = postService.index(token, language, title = title)

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

    private fun setObserver() {
        homeVM.isLoading().observe(viewLifecycleOwner) {
            binding.cardSearch.searchButton.isEnabled = !it
        }
    }
}
