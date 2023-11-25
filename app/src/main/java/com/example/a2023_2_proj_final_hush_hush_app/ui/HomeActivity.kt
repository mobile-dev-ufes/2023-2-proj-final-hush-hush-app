package com.example.a2023_2_proj_final_hush_hush_app.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.a2023_2_proj_final_hush_hush_app.R
import com.example.a2023_2_proj_final_hush_hush_app.RegisterActivity
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityHomeBinding
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityLoginBinding

class HomeActivity: AppCompatActivity(), View.OnClickListener  {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.buttonSingUp.setOnClickListener(this)

    }

    override fun onClick(view: View) {
//        if(view.id == R.id.button_singUp){
//            val intent = Intent(this, RegisterActivity::class.java)
//            startActivity(intent)
//        }
    }
}