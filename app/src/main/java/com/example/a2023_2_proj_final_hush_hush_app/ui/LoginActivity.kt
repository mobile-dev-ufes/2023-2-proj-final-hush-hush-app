package com.example.a2023_2_proj_final_hush_hush_app


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.a2023_2_proj_final_hush_hush_app.bodies.user.LoginBody
import com.example.a2023_2_proj_final_hush_hush_app.clients.RetrofitClient
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityLoginBinding
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityMainBinding
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityRegisterBinding
import com.example.a2023_2_proj_final_hush_hush_app.responses.user.StoreLoginResponse
import com.example.a2023_2_proj_final_hush_hush_app.services.UserService
import com.example.a2023_2_proj_final_hush_hush_app.ui.HomeActivity
import com.example.a2023_2_proj_final_hush_hush_app.ui.MenuActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private var userService = RetrofitClient.createService(UserService::class.java)


    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonSignUp.setOnClickListener(this)
        binding.buttonLogin.setOnClickListener(this)




        //testt
//        val body = LoginBody()
//        body.username = "testSeeder"
//        body.password = "password"
//
//
//        val call = userService.login(body);
//
//        call.enqueue(object: Callback<StoreLoginResponse> {
//            override fun onResponse(call: Call<StoreLoginResponse>, response: Response<StoreLoginResponse>,) {
//                val res = response.message()
//
//                Toast.makeText(applicationContext,
//                    res, Toast.LENGTH_LONG).show();
//            }
////                Toast.makeText(applicationContext,
////                    "Your Message", Toast.LENGTH_LONG).show();
////                }
//
//            override fun onFailure(call: Call<StoreLoginResponse>, t: Throwable) {
//                Toast.makeText(applicationContext,
//                    t.message, Toast.LENGTH_LONG).show();
//            }
//        })


    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_login) {
            val intent = Intent(this, MenuActivity::class.java)
            var editText: EditText = findViewById(R.id.editTextUsername)
            val textUsername: String = editText.text.toString()
//            var duration = Toast.LENGTH_SHORT
//            var toast = Toast.makeText(applicationContext, textUsername, duration)
//            toast.show()
            editText  =  findViewById(R.id.editTextPassword)
            val textPassword: String = editText.text.toString()

            val body = LoginBody()
            body.username = textUsername
            body.password = textPassword

            val call = userService.login(body);

            call.enqueue(object: Callback<StoreLoginResponse> {
                override fun onResponse(call: Call<StoreLoginResponse>, response: Response<StoreLoginResponse>,) {
                    val res = response.message()

                    Toast.makeText(applicationContext,
                        res, Toast.LENGTH_LONG).show();

                    if(res == "OK"){
                        startActivity(intent)
                    }

                }


                override fun onFailure(call: Call<StoreLoginResponse>, t: Throwable) {
                    Toast.makeText(applicationContext,
                        t.message, Toast.LENGTH_LONG).show();
                }
            })





        } else if (view.id == R.id.button_signUp) {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

}
