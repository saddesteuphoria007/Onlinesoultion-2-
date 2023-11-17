package com.example.onlinesolution

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.onlinesolution.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {


    private lateinit var binding:ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val linklogin = findViewById<TextView>(R.id.navLogin) as TextView


        firebaseAuth = FirebaseAuth.getInstance()

        binding.registerButton.setOnClickListener {

            val email = binding.emailEditText.text.toString()
            val pass = binding.password.text.toString()
            val passConfirm = binding.passwordEditText.text.toString()


            if(email.isNotEmpty() && pass.isNotEmpty() && passConfirm.isNotEmpty()){
                if(pass.equals(passConfirm)){

                firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                    if(it.isSuccessful){
                        val intent = Intent(this, Home::class.java)
                        startActivity(intent)

                    }else{

                        Toast.makeText(this, it.exception.toString(),Toast.LENGTH_SHORT).show()


                    }
                }


                }else{

                    Toast.makeText(this, "Password is not matching",Toast.LENGTH_SHORT).show()
                }
            }else{

                Toast.makeText(this, "Fill all fields",Toast.LENGTH_SHORT).show()

            }
        }





        linklogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent) }


    }
}