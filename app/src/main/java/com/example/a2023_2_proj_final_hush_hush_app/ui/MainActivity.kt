package com.example.a2023_2_proj_final_hush_hush_app

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

//class MainActivity : AppCompatActivity()  {

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



        //filipe modifications
        val body = LoginBody()
        body.username = "testSeeder"
        body.password = "password"

        val call = userService.login(body);

        call.enqueue(object: Callback<StoreLoginResponse> {
            override fun onResponse(call: Call<StoreLoginResponse>, response: Response<StoreLoginResponse>,) {
                val res = response.message()

                Toast.makeText(applicationContext,
                    res, Toast.LENGTH_LONG).show();
            }
//                Toast.makeText(applicationContext,
//                    "Your Message", Toast.LENGTH_LONG).show();
//                }

            override fun onFailure(call: Call<StoreLoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext,
                    t.message, Toast.LENGTH_LONG).show();
            }
        })




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