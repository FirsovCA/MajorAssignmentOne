package com.example.majorassignmentone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.majorassignmentone.databinding.ActivityLogin2Binding
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityLogin2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val firestore = FirebaseFirestore.getInstance()
        val usersCollection = firestore.collection("Users")

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            usersCollection.document(username).get()
                .addOnSuccessListener { report ->
                    val savedUsername = report.data?.get("username")
                    val savedPassword = report.data?.get("password")

                    if (username == savedUsername && password == savedPassword) {
                        val intentWelcome = Intent(this, WelcomeActivity::class.java)
                        intentWelcome.putExtra("USERNAME", username)
                        startActivity(intentWelcome)
                    } else {
                        Toast.makeText(this, "Username or password is incorrect. Try again!", Toast.LENGTH_LONG).show()
                    }
                }
                .addOnFailureListener { error ->
                    Log.w("SignUp2", "Error receiving data", error)
                    Toast.makeText(this, "Error receiving data", Toast.LENGTH_LONG).show()
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