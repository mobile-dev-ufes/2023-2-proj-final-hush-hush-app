package com.example.a2023_2_proj_final_hush_hush_app.ui

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a2023_2_proj_final_hush_hush_app.R
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityMenuBinding
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityRegisterBinding
import com.google.android.material.navigation.NavigationView

class MenuActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.showToast("ON CREATE ENTIEWRDJRFJIKFJK")
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
        this.showToast("ONCREATE")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        this.showToast("entrou 1")

       return when (item.itemId) {
            R.id.logout -> {
                this.showToast("entrou 2")
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }

        }
//        if (item.itemId == R.id.logout) {
//            this.showToast("entrou 2")
//            return true
//        }
//
//        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }
}