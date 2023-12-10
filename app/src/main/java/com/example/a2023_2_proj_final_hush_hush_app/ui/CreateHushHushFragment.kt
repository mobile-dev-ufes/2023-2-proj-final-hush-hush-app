package com.example.a2023_2_proj_final_hush_hush_app.ui

import android.content.Context
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
import com.example.a2023_2_proj_final_hush_hush_app.R
import com.example.a2023_2_proj_final_hush_hush_app.bodies.post.StoreUpdateBody
import com.example.a2023_2_proj_final_hush_hush_app.clients.RetrofitClient
import com.example.a2023_2_proj_final_hush_hush_app.databinding.FragmentCreateHushHushBinding
import com.example.a2023_2_proj_final_hush_hush_app.responses.post.StoreUpdatePatchResponse
import com.example.a2023_2_proj_final_hush_hush_app.services.PostService
import com.example.a2023_2_proj_final_hush_hush_app.utils.Preferences
import com.example.a2023_2_proj_final_hush_hush_app.viewModel.CreateHushHushViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale


class CreateHushHushFragment : Fragment(R.layout.fragment_create_hush_hush) ,  View.OnClickListener {
    private var postService = RetrofitClient.createService(PostService::class.java)
    private var _binding: FragmentCreateHushHushBinding? = null


    private val binding get() = _binding!!


    private lateinit var createHushHushVM: CreateHushHushViewModel
    private lateinit var sp: Preferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentCreateHushHushBinding.inflate(inflater , container, false)
        sp = Preferences(requireContext().applicationContext)
        createHushHushVM = ViewModelProvider(this)[CreateHushHushViewModel::class.java]

        this.setListeners()
        this.setObserver()
        return binding.root
    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_create){
            this.handleClickCreateButton()
        }
    }

    private fun handleClickCreateButton() {
        this.createHushHush()
    }

    private fun createHushHush() {
        createHushHushVM.setIsLoading(true)

        val body = StoreUpdateBody()
        body.title = createHushHushVM.title().value.toString()
        body.content = createHushHushVM.content().value.toString()

        val token  = sp.getToken()
        val language : String = Locale.getDefault().language

        val call = postService.store(token, language, body);

        call.enqueue(object: Callback<StoreUpdatePatchResponse> {
            override fun onResponse(call: Call<StoreUpdatePatchResponse>, response: Response<StoreUpdatePatchResponse>,) {
                if(response.isSuccessful) {
                    showToast("Hush-Hush created successful!")

                    val postId = response.body()!!.id
                    redirectToShowHushHushFragment(postId)
                    createHushHushVM.executeClearContent()
                } else {
                    showToast("Error creating hush-hush")
                }

                createHushHushVM.setIsLoading(false)
            }

            override fun onFailure(call: Call<StoreUpdatePatchResponse>, t: Throwable) {
                createHushHushVM.setIsLoading(false)
                showToast("Internal Server Error!")
            }
        })
    }

    fun showToast(message: String) {
        val applicationContext: Context = requireContext().applicationContext
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show();
    }

    private fun setObserver() {
        createHushHushVM.isLoading().observe(viewLifecycleOwner) {
            binding.buttonCreate.isEnabled = !it
        }

        createHushHushVM.clearContent().observe(viewLifecycleOwner) {
            binding.createEditTitle.text.clear()
            binding.createHushHushBody.text.clear()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners() {
        binding.createEditTitle.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                createHushHushVM.setTitle(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.createHushHushBody.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                createHushHushVM.setContent(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.buttonCreate.setOnClickListener(this)
    }

    private fun redirectToShowHushHushFragment(postId: Int) {
        val action = CreateHushHushFragmentDirections.actionCreateHushHushFragmentToShowHushHushFragment(postId)
        findNavController().navigate(action)
    }
}
