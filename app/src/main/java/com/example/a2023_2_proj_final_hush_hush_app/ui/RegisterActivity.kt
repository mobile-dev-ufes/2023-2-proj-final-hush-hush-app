package com.example.a2023_2_proj_final_hush_hush_app.ui

import com.example.a2023_2_proj_final_hush_hush_app.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityRegisterBinding
import com.example.a2023_2_proj_final_hush_hush_app.utils.Preferences
import com.example.a2023_2_proj_final_hush_hush_app.viewModel.RegisterViewModel


class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerVM: RegisterViewModel
    private lateinit var sp: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sp = Preferences(applicationContext)
        registerVM = ViewModelProvider(this)[RegisterViewModel::class.java]

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonLogin.setOnClickListener(this)
        binding.buttonNext.setOnClickListener(this)

        binding.editTextUsername.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                registerVM.setUsername(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.editTextPassword.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                registerVM.setPassword(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.editTextConfirmPassword.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                registerVM.setRepeatPassword(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        this.setObserver()
    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_login){
            this.handleClickLoginButton()
        }else if(view.id == R.id.button_next){
            this.handleClickNextButton()
        }
    }

    fun <S> changeActivity(sourceActivity: Class<S>) {
        val intent = Intent(this, sourceActivity)
        startActivity(intent)
    }

    private fun handleClickLoginButton() {
        this.changeActivity(LoginActivity::class.java)
    }

    private fun handleClickNextButton() {
        val intent = Intent(this, TermsOfUseActivity::class.java)

        // Passing values to terms of use
        intent.putExtra("textUsername", registerVM.username().value.toString())
        intent.putExtra("textPassword", registerVM.password().value.toString())
        intent.putExtra("textConfirmPassword", registerVM.repeatPassword().value.toString())

        startActivity(intent)
    }

    private fun setObserver() {
        registerVM.isLoading().observe(this) {
            binding.buttonNext.isEnabled = !it
        }
    }
}
