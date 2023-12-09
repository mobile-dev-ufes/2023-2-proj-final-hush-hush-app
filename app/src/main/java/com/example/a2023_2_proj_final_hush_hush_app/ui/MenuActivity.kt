package com.example.a2023_2_proj_final_hush_hush_app.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.example.a2023_2_proj_final_hush_hush_app.R
import com.example.a2023_2_proj_final_hush_hush_app.clients.RetrofitClient
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityMenuBinding
import com.example.a2023_2_proj_final_hush_hush_app.responses.user.ShowResponse
import com.example.a2023_2_proj_final_hush_hush_app.services.UserService
import com.example.a2023_2_proj_final_hush_hush_app.utils.Preferences
import com.example.a2023_2_proj_final_hush_hush_app.viewModel.CardProfileViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale


class MenuActivity() : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var sp: Preferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)





        setContentView(binding.root)
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        this.showToast("entrou sdlfjsdjkf")
//        menuInflater.inflate(R.menu.menu, menu)
//        return true
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


       return when (item.itemId) {
            R.id.logout -> {
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }

        }
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    //request to API



}