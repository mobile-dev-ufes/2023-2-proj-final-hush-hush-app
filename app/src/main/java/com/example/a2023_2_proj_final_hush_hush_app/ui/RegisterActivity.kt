package com.example.a2023_2_proj_final_hush_hush_app


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityMainBinding
import com.example.a2023_2_proj_final_hush_hush_app.databinding.ActivityRegisterBinding
import com.example.a2023_2_proj_final_hush_hush_app.ui.TermsOfUseActivity


class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonLogin.setOnClickListener(this)
        binding.buttonNext.setOnClickListener(this)



    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_login){
            val intent = Intent(this, LoginActivity::class.java )
            startActivity(intent)
        }else if(view.id == R.id.button_next){
            val intent = Intent(this, TermsOfUseActivity::class.java)

            //Passing values to terms of use
            var editText: EditText = findViewById(R.id.editTextUsername)
            val textUsername: String = editText.text.toString()
            intent.putExtra("textUsername", textUsername)

            editText  =  findViewById(R.id.editTextPassword)
            val textPassword: String = editText.text.toString()
            intent.putExtra("textPassword", textPassword)

            editText  =  findViewById(R.id.editTextConfirmPassword)
            val textConfirmPassword: String = editText.text.toString()
            intent.putExtra("textConfirmPassword", textConfirmPassword)

            startActivity(intent)

        }
    }




}

