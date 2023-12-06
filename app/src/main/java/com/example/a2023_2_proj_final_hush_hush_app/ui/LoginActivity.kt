package com.example.a2023_2_proj_final_hush_hush_app.ui

import com.example.a2023_2_proj_final_hush_hush_app.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.a2023_2_proj_final_hush_hush_app.CreateHushHushFragment
import com.example.a2023_2_proj_final_hush_hush_app.bodies.user.LoginBody
import com.example.a2023_2_proj_final_hush_hush_app.clients.RetrofitClient
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityLoginBinding
import com.example.a2023_2_proj_final_hush_hush_app.responses.user.StoreLoginResponse
import com.example.a2023_2_proj_final_hush_hush_app.services.UserService
import com.example.a2023_2_proj_final_hush_hush_app.utils.Preferences
import com.example.a2023_2_proj_final_hush_hush_app.viewModel.LoginViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private var userService = RetrofitClient.createService(UserService::class.java)
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginVM: LoginViewModel
    private lateinit var sp: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sp = Preferences(applicationContext)
        loginVM = ViewModelProvider(this)[LoginViewModel::class.java]

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignUp.setOnClickListener(this)
        binding.buttonLogin.setOnClickListener(this)

        binding.editTextUsername.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loginVM.setUsername(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.editTextPassword.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loginVM.setPassword(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        this.setObserver()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_login) {
            this.handleClickLoginButton()
        } else if (view.id == R.id.button_signUp) {
            this.handleClickSignUpButton()
        }
    }

    fun <S> changeActivity(sourceActivity: Class<S>) {
        val intent = Intent(this, sourceActivity)
        startActivity(intent)
    }

    fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show();
    }

    private fun handleClickLoginButton() {
        loginVM.setIsLoading(true)

        val body = LoginBody()
        body.username = loginVM.username().value.toString()
        body.password = loginVM.password().value.toString()

        val call = userService.login(body);

        call.enqueue(object: Callback<StoreLoginResponse> {
            override fun onResponse(call: Call<StoreLoginResponse>, response: Response<StoreLoginResponse>,) {
                if(response.isSuccessful) {
                    // Saving user data
                    sp.setUsername(response.body()!!.username)
                    sp.setProfilePicture(response.body()!!.profilePicture)
                    sp.setToken("${response.body()!!.token.tokenType} ${response.body()!!.token.accessToken}")
                    showToast("${response.body()!!.token.tokenType} ${response.body()!!.token.accessToken}")
                    changeActivity(MenuActivity::class.java)

                }else{
                    showToast("Wrong username or password.")
                }

                loginVM.setIsLoading(false)
            }

            override fun onFailure(call: Call<StoreLoginResponse>, t: Throwable) {
                loginVM.setIsLoading(false)
                showToast("Internal Server Error!")
            }
        })
    }

    private fun handleClickSignUpButton() {
        changeActivity(RegisterActivity::class.java)
    }

    private fun setObserver() {
        loginVM.isLoading().observe(this) {
            binding.buttonLogin.isEnabled = !it
        }
    }
}
