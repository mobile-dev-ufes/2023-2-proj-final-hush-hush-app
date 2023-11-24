package com.example.a2023_2_proj_final_hush_hush_app


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityLoginBinding
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityMainBinding
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityRegisterBinding
import com.example.a2023_2_proj_final_hush_hush_app.ui.HomeActivity


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonSignUp.setOnClickListener(this)
        binding.buttonLogin.setOnClickListener(this)


    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_login) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        } else if (view.id == R.id.button_signUp) {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

}
