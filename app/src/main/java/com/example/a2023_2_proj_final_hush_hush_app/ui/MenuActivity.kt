package com.example.a2023_2_proj_final_hush_hush_app.ui

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.a2023_2_proj_final_hush_hush_app.R
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityMenuBinding
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityRegisterBinding

class MenuActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)




        setContentView(binding.root)
//        binding.buttonLogin.setOnClickListener(this)
//        binding.buttonNext.setOnClickListener(this)

//        val cardLayout: View = layoutInflater.inflate(R.layout.card_hush_hush, null)
//        val containerLayout = findViewById(R.layout.activity_menu)
//        containerLayout.addView(cardLayout)

    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}