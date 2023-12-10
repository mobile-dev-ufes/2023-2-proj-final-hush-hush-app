package com.example.a2023_2_proj_final_hush_hush_app.ui

import com.example.a2023_2_proj_final_hush_hush_app.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityMainBinding
import com.example.a2023_2_proj_final_hush_hush_app.utils.Preferences

class MainActivity : AppCompatActivity() ,  View.OnClickListener {
    private lateinit var binding : ActivityMainBinding
    private lateinit var sp: Preferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonRegister.setOnClickListener(this)
        binding.buttonLogin.setOnClickListener(this)
        sp = Preferences(applicationContext)
        if (sp.tokenIsFilled()) {
            this.changeActivity(MenuActivity::class.java)
            finish()
        }
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_register) {
            this.changeActivity(RegisterActivity::class.java)

        }else if(view.id == R.id.button_login){
            this.changeActivity(LoginActivity::class.java)
        }
    }

    private fun <S> changeActivity(sourceActivity: Class<S>) {
        val intent = Intent(this, sourceActivity)
        startActivity(intent)
    }
}