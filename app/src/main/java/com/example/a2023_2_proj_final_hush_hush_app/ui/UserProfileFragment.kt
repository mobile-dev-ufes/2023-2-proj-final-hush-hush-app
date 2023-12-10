package com.example.a2023_2_proj_final_hush_hush_app.ui

import android.icu.text.SimpleDateFormat
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2023_2_proj_final_hush_hush_app.R
import com.example.a2023_2_proj_final_hush_hush_app.clients.RetrofitClient
import com.example.a2023_2_proj_final_hush_hush_app.databinding.FragmentUserProfileBinding
import com.example.a2023_2_proj_final_hush_hush_app.interfaces.CardViewHolderClickListener
import com.example.a2023_2_proj_final_hush_hush_app.responses.post.IndexResponse
import com.example.a2023_2_proj_final_hush_hush_app.responses.user.ShowResponse
import com.example.a2023_2_proj_final_hush_hush_app.services.PostService
import com.example.a2023_2_proj_final_hush_hush_app.services.UserService
import com.example.a2023_2_proj_final_hush_hush_app.ui.view.ListHushHushAdapter
import com.example.a2023_2_proj_final_hush_hush_app.utils.Preferences
import com.example.a2023_2_proj_final_hush_hush_app.utils.Utils
import com.example.a2023_2_proj_final_hush_hush_app.viewModel.CardProfileViewModel
import com.example.a2023_2_proj_final_hush_hush_app.viewModel.UserProfileViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale


class UserProfileFragment : Fragment(R.layout.fragment_user_profile), CardViewHolderClickListener {
    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var sp: Preferences
    private lateinit var cardProfileVM: CardProfileViewModel

    private var userService = RetrofitClient.createService(UserService::class.java)
    private var postService = RetrofitClient.createService(PostService::class.java)

    private lateinit var viewModel: UserProfileViewModel
    private val adapter = ListHushHushAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        cardProfileVM = ViewModelProvider(this)[CardProfileViewModel::class.java]
        sp = Preferences(requireContext().applicationContext)
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        binding.recyclerListHushHush.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerListHushHush.adapter = adapter

        this.getListHushHush()
        this.setObserver()
        this.getCardProfileData()

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

    private fun getCardProfileData(){
        val currentLocale: Locale = resources.configuration.locales[0]
        val language: String = currentLocale.language
        val token = sp.getToken()
//        showToast(token)
        val call = userService.showByLoggedUser(token, language)

        call.enqueue(object: Callback<ShowResponse> {
            override fun onResponse(call: Call<ShowResponse>, response: Response<ShowResponse>) {
                if(response.isSuccessful) {
                    val res = response.body()
                    cardProfileVM.setUsername(res!!.username)
                    cardProfileVM.setMemberSince(res!!.createdAt)
                    cardProfileVM.setNumHushHush(res!!.postsCount)

                }else{
                    showToast("An error has occurred.")
                }
            }

            override fun onFailure(call: Call<ShowResponse>, t: Throwable) {
                showToast("Internal Server Error!")
            }
        })
    }

    override fun onClickCard(index: Int) {
        val hushHush = adapter.getHushHushByIndex(index)
        val action = UserProfileFragmentDirections.actionUserProfileFragmentToShowHushHushFragment(hushHush.id)
        findNavController().navigate(action)
    }

    private fun setObserver() {
        cardProfileVM.username().observe(viewLifecycleOwner){
            binding.cardProfile.profileUsername.text = it
        }

        cardProfileVM.numHushHush().observe(viewLifecycleOwner){
            binding.cardProfile.qtdHushHush.text = getString(R.string.qtd_hush_hush, it)
        }

        cardProfileVM.memberSince().observe(viewLifecycleOwner){
            var date = it
            date = date.split(" ").first().toString()
            date = Utils.formatDateAccordingUserLocale(date, "yyyy-MM-dd", false)

            val memberSince = getString(R.string.member_since, date)
            binding.cardProfile.memberSince.text = memberSince
        }
    }
}