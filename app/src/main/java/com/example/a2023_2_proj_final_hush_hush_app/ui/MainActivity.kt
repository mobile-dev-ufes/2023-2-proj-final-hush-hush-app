package com.example.a2023_2_proj_final_hush_hush_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityMainBinding

//class MainActivity : AppCompatActivity() ,  View.OnClickListener {
class MainActivity : AppCompatActivity()  {

    private lateinit var binding : ActivityMainBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        //navController = Navigation.findNavController(this, R.id.mainActivity)
//        navController = Navigation.findNavController(this, R.id.activity_main)
        //navController = Navigation.findNavController(this, R.id.activity_main)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)
//        //binding.buttonRegister.setOnClickListener(this)
//        binding.buttonRegister.setOnClickListener {
//            navController.navigate(R.id.register)
//        }
    }





//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.register)
//}

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.terms_of_use)
//    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.login)
//    }
//    override fun onClick(view: View) {
//        if (view.id == R.id.button_register) {
//            val text = "O botão foi clicadooooooooooo!"
//            val duration = Toast.LENGTH_SHORT
//            val toast = Toast.makeText(applicationContext, text, duration)
//            toast.show()
////            try {
////                val intent = Intent(this, Register::class.java)
////                startActivity(intent)
////            } catch (e: Exception) {
////                e.printStackTrace()
////            }
//        }
////        }else{
////            val text = "O botão foi clicadooooooooooo!"
////            val duration = Toast.LENGTH_SHORT
////            val toast = Toast.makeText(applicationContext, text, duration)
////            toast.show()
////        }
//    }
}