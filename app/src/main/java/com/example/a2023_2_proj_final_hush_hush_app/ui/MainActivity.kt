package com.example.a2023_2_proj_final_hush_hush_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() ,  View.OnClickListener {
//class MainActivity : AppCompatActivity()  {

    private lateinit var binding : ActivityMainBinding
//    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonRegister.setOnClickListener(this)
        binding.buttonLogin.setOnClickListener(this)

    }



//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_register)
//    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.terms_of_use)
//    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.login)
//    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_menu)
//    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.fragment_hush_hush)
//    }



    override fun onClick(view: View) {
        if (view.id == R.id.button_register) {
            val text = "O botão foi clicadooooooooooo!"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
            val intent = Intent(this, RegisterActivity::class.java )
            startActivity(intent)

        }else if(view.id == R.id.button_login){
            val text = "simmmmmm!"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
            val intent = Intent(this, LoginActivity::class.java )
            startActivity(intent)
        }

    }
}