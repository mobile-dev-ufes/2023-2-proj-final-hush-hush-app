package com.example.a2023_2_proj_final_hush_hush_app.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.a2023_2_proj_final_hush_hush_app.R
import com.example.a2023_2_proj_final_hush_hush_app.clients.RetrofitClient
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityMenuBinding
import com.example.a2023_2_proj_final_hush_hush_app.services.UserService
import com.example.a2023_2_proj_final_hush_hush_app.utils.Preferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class MenuActivity() : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMenuBinding
    private lateinit var sp: Preferences
    private var userService = RetrofitClient.createService(UserService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)

        this.setToolbarDrawerAndNavButton()
        setContentView(binding.root)
    }

    private fun setToolbarDrawerAndNavButton() {
        val navHostFrag = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFrag.navController
        binding.menu.setupWithNavController(navController)
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    private fun handleClickLogout() {
        this.logout()
    }

    private fun logout() {
//        loginVM.setIsLoading(true)
        val currentLocale: Locale = resources.configuration.locales[0]
        val language: String = currentLocale.language
        val token = sp.getToken()

        val call = userService.logout(token, language)

        call.enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>,) {
                if(response.isSuccessful) {
                    sp.clearSharedPreferences()
                    changeActivity(MainActivity::class.java)
                    showToast("Logout successful!")
                    finish()

                }else{
                    showToast("Error on logout.")
                }

//                loginVM.setIsLoading(false)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
//                loginVM.setIsLoading(false)
                showToast("Internal Server Error!")
            }
        })
    }

    fun <S> changeActivity(sourceActivity: Class<S>) {
        val intent = Intent(this, sourceActivity)
        startActivity(intent)
    }
}
