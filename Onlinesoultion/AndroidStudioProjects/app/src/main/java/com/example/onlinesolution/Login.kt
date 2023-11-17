package com.example.onlinesolution

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.onlinesolution.databinding.ActivityLoginBinding
import com.example.onlinesolution.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding
     lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val forget = findViewById<TextView>(R.id.forgotPassword)



        firebaseAuth = FirebaseAuth.getInstance()

        binding.login.setOnClickListener {
            val email = binding.username.text.toString()
            val pass = binding.password.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                // Sign in with email and password
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        // Check if the user is the admin
                        val currentUser: FirebaseUser? = firebaseAuth.currentUser

                        if (currentUser?.email == "tshepo@gmail.com" && pass == "Hello12") {

                            // If the user is the admin, navigate to the HistoryActivity
                            val intent = Intent(this, AdminMenu::class.java)
                            startActivity(intent)

                        } else {
                            // If not the admin, navigate to the HomeActivity
                            val intent = Intent(this, Home::class.java)
                            startActivity(intent)

                            Toast.makeText(this, "Welcome to 3M online Tuckshop" + currentUser, Toast.LENGTH_SHORT).show()

                        }
                    } else {
                        // Handle unsuccessful login
                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, Login::class.java)
                        startActivity(intent)
                    }
                }
            } else {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
            }

            forget.setOnClickListener {

            }
        }

        binding.register.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)}

        fun onStart() {
            super.onStart()
            if(firebaseAuth.currentUser != null){
                val intent = Intent(this, Home::class.java)
                startActivity(intent)

            }
        }

    }
}