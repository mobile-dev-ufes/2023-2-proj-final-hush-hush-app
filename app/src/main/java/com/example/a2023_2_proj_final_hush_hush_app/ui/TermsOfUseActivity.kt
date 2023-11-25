package com.example.a2023_2_proj_final_hush_hush_app.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a2023_2_proj_final_hush_hush_app.HomeFragment
import com.example.a2023_2_proj_final_hush_hush_app.R
import com.example.a2023_2_proj_final_hush_hush_app.RegisterActivity
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityLoginBinding
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityTermsOfUseBinding

class TermsOfUseActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityTermsOfUseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsOfUseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonSignUp.setOnClickListener(this)



    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_signUp){
//            val intent = Intent(this, HomeActivity::class.java)
            val intent = Intent(this, MenuActivity::class.java )
            startActivity(intent)
        }
    }
}