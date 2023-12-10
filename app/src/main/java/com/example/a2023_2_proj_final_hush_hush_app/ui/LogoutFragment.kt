package com.example.a2023_2_proj_final_hush_hush_app.ui

import android.app.Dialog
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.a2023_2_proj_final_hush_hush_app.viewModel.LogoutViewModel
import com.example.a2023_2_proj_final_hush_hush_app.R
import com.example.a2023_2_proj_final_hush_hush_app.clients.RetrofitClient
import com.example.a2023_2_proj_final_hush_hush_app.databinding.FragmentHomeBinding
import com.example.a2023_2_proj_final_hush_hush_app.databinding.FragmentLogoutBinding
import com.example.a2023_2_proj_final_hush_hush_app.services.PostService
import com.example.a2023_2_proj_final_hush_hush_app.services.UserService
import com.example.a2023_2_proj_final_hush_hush_app.utils.Preferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class LogoutFragment : DialogFragment() {
    private var _binding: FragmentLogoutBinding? = null
    private val binding get() = _binding!!
    private lateinit var sp: Preferences
    private var userService = RetrofitClient.createService(UserService::class.java)

    private lateinit var viewModel: LogoutViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        sp = Preferences(requireContext().applicationContext)
        _binding = FragmentLogoutBinding.inflate(inflater, container, false)

        binding.logoutButton.setOnClickListener {
            this.logout()
        }

        binding.cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LogoutViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun logout() {
//        loginVM.setIsLoading(true)
        val currentLocale: Locale = resources.configuration.locales[0]
        val language: String = currentLocale.language
        val token = sp.getToken()

        val call = userService.logout(token, language)

        call.enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>,) {
                if(response.isSuccessful) {
                    sp.clearSharedPreferences()
                    changeActivity(MainActivity::class.java)
                    showToast("Logout successful!")

                }else{
                    showToast("Error on logout.")
                }

//                loginVM.setIsLoading(false)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
//                loginVM.setIsLoading(false)
                showToast("Internal Server Error!")
            }
        })
    }

    fun <S> changeActivity(sourceActivity: Class<S>) {
        val intent = Intent(requireContext().applicationContext, sourceActivity)
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext().applicationContext, message, Toast.LENGTH_LONG).show()
    }
}