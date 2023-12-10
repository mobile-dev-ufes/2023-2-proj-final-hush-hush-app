package com.example.a2023_2_proj_final_hush_hush_app.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.a2023_2_proj_final_hush_hush_app.R
import com.example.a2023_2_proj_final_hush_hush_app.bodies.user.StoreBody
import com.example.a2023_2_proj_final_hush_hush_app.clients.RetrofitClient
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityTermsOfUseBinding
import com.example.a2023_2_proj_final_hush_hush_app.responses.user.StoreLoginResponse
import com.example.a2023_2_proj_final_hush_hush_app.services.UserService
import com.example.a2023_2_proj_final_hush_hush_app.utils.Preferences
import com.example.a2023_2_proj_final_hush_hush_app.viewModel.TermsOfUseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TermsOfUseActivity : AppCompatActivity(), View.OnClickListener {
    private var userService = RetrofitClient.createService(UserService::class.java)
    private lateinit var binding: ActivityTermsOfUseBinding
    private lateinit var termsOfUseVM: TermsOfUseViewModel
    private lateinit var sp: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sp = Preferences(applicationContext)
        termsOfUseVM = ViewModelProvider(this)[TermsOfUseViewModel::class.java]
        termsOfUseVM.setIsAccepted(false)

        binding = ActivityTermsOfUseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonSignUp.setOnClickListener(this)
        binding.checkbox.setOnCheckedChangeListener {
                _, isChecked ->
            if(isChecked) {
                termsOfUseVM.setIsAccepted(true)
            } else {
                termsOfUseVM.setIsAccepted(false)
            }
        }
        this.setObserver()
    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_signUp){
            this.handleClickSignUpButton()
        }
    }

    private fun handleClickSignUpButton() {
        if(termsOfUseVM.isAccepted().value == false){
            this.showToast("You need to accept the terms to create an account")
            return
        }
        termsOfUseVM.setUsername(intent.getStringExtra("textUsername"))
        termsOfUseVM.setPassword(intent.getStringExtra("textPassword"))
        termsOfUseVM.setRepeatPassword(intent.getStringExtra("textConfirmPassword"))

//        val checkbox: CheckBox = findViewById(R.id.checkbox)
//
//        if(checkbox.isChecked){

        termsOfUseVM.setIsLoading(true)

        val body = StoreBody()

        body.username = termsOfUseVM.username().value.toString()
        body.password = termsOfUseVM.password().value.toString()
        body.repeatPassword = termsOfUseVM.repeatPassword().value.toString()

        val call = userService.store(body);

        call.enqueue(object: Callback<StoreLoginResponse> {
            override fun onResponse(call: Call<StoreLoginResponse>, response: Response<StoreLoginResponse>,) {
                if(response.isSuccessful) {
                    // Saving user data
                    sp.setUsername(response.body()!!.username)
                    sp.setProfilePicture(response.body()!!.profilePicture)
                    sp.setToken("${response.body()!!.token.tokenType} ${response.body()!!.token.accessToken}")

                    showToast("Welcome to Hush-Hush!")
                    changeActivity(MenuActivity::class.java)
                    finishAffinity()
                } else {
                    showToast("Invalid username or password.")
                    finish()
                }

                termsOfUseVM.setIsLoading(false)
            }

            override fun onFailure(call: Call<StoreLoginResponse>, t: Throwable) {
                termsOfUseVM.setIsLoading(false)
                showToast("Internal Server Error!")
            }
        })
    }

    private fun setObserver() {
        termsOfUseVM.isLoading().observe(this) {
            binding.buttonSignUp.isEnabled = !it
        }

        termsOfUseVM.isAccepted().observe(this) {
            binding.buttonSignUp.isEnabled = it
        }
    }

    fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show();
    }

    fun <S> changeActivity(sourceActivity: Class<S>) {
        val intent = Intent(this, sourceActivity)
        startActivity(intent)
    }
}
