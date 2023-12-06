package com.example.a2023_2_proj_final_hush_hush_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.a2023_2_proj_final_hush_hush_app.bodies.post.StoreUpdateBody
import com.example.a2023_2_proj_final_hush_hush_app.clients.RetrofitClient
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityLoginBinding
import com.example.a2023_2_proj_final_hush_hush_app.databinding.FragmentCreateHushHushBinding
import com.example.a2023_2_proj_final_hush_hush_app.responses.post.StoreUpdatePatchResponse
import com.example.a2023_2_proj_final_hush_hush_app.responses.user.StoreLoginResponse
import com.example.a2023_2_proj_final_hush_hush_app.services.PostService
import com.example.a2023_2_proj_final_hush_hush_app.services.UserService
import com.example.a2023_2_proj_final_hush_hush_app.ui.MenuActivity
import com.example.a2023_2_proj_final_hush_hush_app.utils.Preferences
import com.example.a2023_2_proj_final_hush_hush_app.viewModel.CreateHushHushViewModel
import com.example.a2023_2_proj_final_hush_hush_app.viewModel.TermsOfUseViewModel
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        super.onCreateView(inflater, container, savedInstanceState)
//        val fragView = inflater.inflate(R.layout.fragment_create_hush_hush, container, false)
        _binding = FragmentCreateHushHushBinding.inflate(inflater , container, false)


//        binding.buttonCreate.setOnClickListener{
//            showToast("entrei no toast")
//
//            this.onClick(it)}
        binding.buttonCreate.setOnClickListener(this)
//        this.setObserver()
        return binding.root

    }

    override fun onClick(view: View) {
        showToast("entrei no on Clickkkkkkkkk")
        if(view.id == R.id.button_create){
            this.handleClickSignUpButton()
        }
    }

    private fun handleClickSignUpButton() {
        showToast("entrei na funcao")

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
                    showToast("hiiiii")
                    changeActivity(HomeFragment::class.java) //TODO Change for the correct framgment and implement with NAVIGATION
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
        //            }
        //            else{
        //                val text = "You have to accept the terms of use to prossegue!"
        //                val duration = Toast.LENGTH_SHORT
        //                val toast = Toast.makeText(applicationContext, text, duration)
        //                toast.show()
        //            }
    }

    fun showToast(message: String) {
        val applicationContext: Context = requireContext().applicationContext
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show();
    }

    fun <S> changeActivity(sourceActivity: Class<S>) {
//        val intent = Intent(this, sourceActivity)
        val intentFromActivity = requireActivity().intent //TODO : Change for naviation!!!!!!!!!!
        startActivity(intentFromActivity)
    }


    private fun setObserver() {
        createHushHushVM.isLoading().observe(viewLifecycleOwner) {
            binding.buttonCreate.isEnabled = !it
//            binding.buttonLogin.isEnabled = !it
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}