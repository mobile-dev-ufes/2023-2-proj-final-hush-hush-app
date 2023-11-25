package com.example.a2023_2_proj_final_hush_hush_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.a2023_2_proj_final_hush_hush_app.bodies.user.LoginBody
import com.example.a2023_2_proj_final_hush_hush_app.clients.RetrofitClient
import com.example.a2023_2_proj_final_hush_hush_app.responses.user.StoreLoginResponse
import com.example.a2023_2_proj_final_hush_hush_app.services.CommentService
import com.example.a2023_2_proj_final_hush_hush_app.services.DashboardsService
import com.example.a2023_2_proj_final_hush_hush_app.services.EvaluationService
import com.example.a2023_2_proj_final_hush_hush_app.services.PostService
import com.example.a2023_2_proj_final_hush_hush_app.services.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestActivity : AppCompatActivity() {
    private var userService = RetrofitClient.createService(UserService::class.java)
    private var postService = RetrofitClient.createService(PostService::class.java)
    private var commentService = RetrofitClient.createService(CommentService::class.java)
    private var evaluationService = RetrofitClient.createService(EvaluationService::class.java)
    private var dashboardsService = RetrofitClient.createService(DashboardsService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val body = LoginBody()
        body.username = "testSeeder"
        body.password = "password"

        val call = userService.login(body);

        call.enqueue(object: Callback<StoreLoginResponse> {
            override fun onResponse(call: Call<StoreLoginResponse>, response: Response<StoreLoginResponse>,) {
                val res = response.message()

                Toast.makeText(applicationContext,
                    "Your Message", Toast.LENGTH_LONG).show();
            }

            override fun onFailure(call: Call<StoreLoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext,
                    t.message, Toast.LENGTH_LONG).show();
            }
        })
    }
}