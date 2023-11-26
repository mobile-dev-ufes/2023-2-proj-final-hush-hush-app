package com.example.a2023_2_proj_final_hush_hush_app.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a2023_2_proj_final_hush_hush_app.HomeFragment
import com.example.a2023_2_proj_final_hush_hush_app.R
import com.example.a2023_2_proj_final_hush_hush_app.RegisterActivity
import com.example.a2023_2_proj_final_hush_hush_app.bodies.user.LoginBody
import com.example.a2023_2_proj_final_hush_hush_app.bodies.user.StoreBody
import com.example.a2023_2_proj_final_hush_hush_app.clients.RetrofitClient
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityLoginBinding
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityTermsOfUseBinding
import com.example.a2023_2_proj_final_hush_hush_app.responses.user.StoreLoginResponse
import com.example.a2023_2_proj_final_hush_hush_app.services.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TermsOfUseActivity : AppCompatActivity(), View.OnClickListener {
    private var userService = RetrofitClient.createService(UserService::class.java)
    private lateinit var binding: ActivityTermsOfUseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsOfUseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonSignUp.setOnClickListener(this)



    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_signUp){
            val intent = Intent(this, MenuActivity::class.java )

            val textUsername = intent.getStringExtra("textUsername")
            val textPassword = intent.getStringExtra("textPassword")
            val textConfirmPassword = intent.getStringExtra("textConfirmPassword")
            val checkbox: CheckBox = findViewById(R.id.checkbox)


            if(checkbox.isChecked){

                val body = StoreBody()
                if (textUsername != null && textPassword != null && textConfirmPassword != null ) {
                    body.username = textUsername
                    body.password = textPassword
                    body.repeatPassword = textConfirmPassword
                }

                val call = userService.store(body);

                call.enqueue(object: Callback<StoreLoginResponse> {
                    override fun onResponse(call: Call<StoreLoginResponse>, response: Response<StoreLoginResponse>,) {
                        val res = response.message()

                        Toast.makeText(applicationContext,
                            res, Toast.LENGTH_LONG).show();

                        if(res == "OK"){
                            startActivity(intent)

                        }else{
                            val text = "didn't work"
                            val duration = Toast.LENGTH_SHORT
                            val toast = Toast.makeText(applicationContext, text, duration)
                            toast.show()
                        }


                    }

                    override fun onFailure(call: Call<StoreLoginResponse>, t: Throwable) {
                        Toast.makeText(applicationContext,
                            t.message, Toast.LENGTH_LONG).show();
                    }
                })



            }else{
                val text = "You have to accept the terms of use to prossegue!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }




        }
    }
}