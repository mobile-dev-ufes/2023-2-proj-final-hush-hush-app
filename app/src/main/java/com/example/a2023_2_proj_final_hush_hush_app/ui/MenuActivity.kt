package com.example.a2023_2_proj_final_hush_hush_app.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.a2023_2_proj_final_hush_hush_app.R
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityMenuBinding
import com.example.a2023_2_proj_final_hush_hush_app.utils.Preferences

class MenuActivity() : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMenuBinding
    private lateinit var sp: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        this.setToolbarDrawerAndNavButton()

//        binding.menu.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.navigation_dashboard -> {
//                    showToast("CLICOU")
//                    findNavController().navigate(R.id.action)
//                    true
//                } else -> false
//            }
//        }

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
}
