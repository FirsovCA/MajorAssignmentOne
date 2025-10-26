package com.example.majorassignmentone

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.majorassignmentone.databinding.ActivitySignUp2Binding
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity2 : AppCompatActivity() {
    private lateinit var binding : ActivitySignUp2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignUp2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val firestore = FirebaseFirestore.getInstance()
        val usersCollection = firestore.collection("Users")

        binding.btnCreateAccount.setOnClickListener {
            var isUsernameCorrect = true
            var isPasswordCorrect = true

            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val passwordConfirmation = binding.etConfirmPassword.text.toString()

            if (
                !username.contains("@")
                || !username.contains(".")
                || username.length < 5
                ) {
                isUsernameCorrect = false
                val message = "The username must follow the email pattern"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            } else {
                if (password.length < 8 || password.length > 15) {
                    isPasswordCorrect = false
                    val message = "The password must be between 8 and 15 characters"
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }

                var hasCapitalLetter = false
                var hasSpecCharacter = false
                for (char in password) {
                    if (char.isUpperCase()) hasCapitalLetter = true
                    if (!char.isLetterOrDigit() && !char.isWhitespace()) hasSpecCharacter = true
                }

                if (isPasswordCorrect && (!hasCapitalLetter || !hasSpecCharacter)) {
                    isPasswordCorrect = false
                    val message = "The password must include at least one capital letter and one special character"
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }

                if (isPasswordCorrect && (password != passwordConfirmation)) {
                    isPasswordCorrect = false
                    Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show()
                }

                if (isUsernameCorrect && isPasswordCorrect) {
                    val userCredentials = hashMapOf(
                        "username" to username,
                        "password" to password
                    )

                    usersCollection.document(username).set(userCredentials)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Account created successfully", Toast.LENGTH_LONG).show()
                        }
                        .addOnFailureListener { error ->
                            Log.w("SignUp2", "Account creation error", error)
                            Toast.makeText(this, "Account creation error", Toast.LENGTH_LONG).show()
                        }
                }
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