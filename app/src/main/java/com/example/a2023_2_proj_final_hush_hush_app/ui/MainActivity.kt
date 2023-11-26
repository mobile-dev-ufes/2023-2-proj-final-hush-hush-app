package com.example.a2023_2_proj_final_hush_hush_app.ui

import com.example.a2023_2_proj_final_hush_hush_app.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a2023_2_proj_final_hush_hush_app.bodies.user.LoginBody
import com.example.a2023_2_proj_final_hush_hush_app.clients.RetrofitClient
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityMainBinding
import com.example.a2023_2_proj_final_hush_hush_app.responses.user.StoreLoginResponse
import com.example.a2023_2_proj_final_hush_hush_app.services.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() ,  View.OnClickListener {
    private var userService = RetrofitClient.createService(UserService::class.java)

    private lateinit var binding : ActivityMainBinding
//    private lateinit var navController: NavController

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_test)
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonRegister.setOnClickListener(this)
        binding.buttonLogin.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_register) {
            val intent = Intent(this, RegisterActivity::class.java )
            startActivity(intent)

        }else if(view.id == R.id.button_login){
            val intent = Intent(this, LoginActivity::class.java )
            startActivity(intent)
        }

    }
}