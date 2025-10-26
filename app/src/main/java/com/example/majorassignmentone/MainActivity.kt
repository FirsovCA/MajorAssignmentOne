package com.example.majorassignmentone

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.majorassignmentone.databinding.ActivityMainBinding
import android.content.Intent
import android.net.Uri

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener {
            val intent_sign_up = Intent(this, SignUpActivity::class.java)
            startActivity(intent_sign_up)
        }

        binding.btnLogin.setOnClickListener {
            val intent_login = Intent(this, LoginActivity::class.java)
            startActivity(intent_login)
        }

        binding.btnSignUp2.setOnClickListener {
            val intent_sign_up_2 = Intent(this, SignUpActivity2::class.java)
            startActivity(intent_sign_up_2)
        }

        binding.btnLogin2.setOnClickListener {
            val intent_login_2 = Intent(this, LoginActivity2::class.java)
            startActivity(intent_login_2)
        }

        binding.btnUpdates.setOnClickListener {
            val url = "https://play.google.com/store/apps"
            val intent_open_website = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent_open_website)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}