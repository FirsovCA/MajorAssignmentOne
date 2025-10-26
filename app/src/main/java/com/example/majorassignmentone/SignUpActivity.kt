package com.example.majorassignmentone

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.majorassignmentone.databinding.ActivitySignUpBinding
import kotlin.toString

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreateAccount.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val passwordConfirmation = binding.etConfirmPassword.text.toString()

            if (password != passwordConfirmation) {
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show()
            } else {
                val localStorage : SharedPreferences = getSharedPreferences("Local_Storage", MODE_PRIVATE)
                val editor : SharedPreferences.Editor = localStorage.edit()

                editor.putString("USERNAME", username)
                editor.putString("PASSWORD", password)
                editor.apply()

                Toast.makeText(this, "Account created successfully", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnBack.setOnClickListener { finish() }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}