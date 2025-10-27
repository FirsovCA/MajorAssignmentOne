package com.example.majorassignmentone

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.majorassignmentone.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            val localStorage : SharedPreferences = getSharedPreferences("Local_Storage", MODE_PRIVATE)

            val savedUsername = localStorage.getString("USERNAME", null)
            val savedPassword = localStorage.getString("PASSWORD", null)

            if (username == savedUsername && password == savedPassword) {
                val intentWelcome = Intent(this, WelcomeActivity::class.java)
                intentWelcome.putExtra("USERNAME", username)
                startActivity(intentWelcome)
            } else {
                Toast.makeText(this, "Username or password is incorrect. Try again!", Toast.LENGTH_LONG).show()
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