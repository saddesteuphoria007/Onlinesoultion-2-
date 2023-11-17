package com.example.onlinesolution

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ContactUsActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextMessage: EditText
    private lateinit var buttonSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)

        // Initialize UI elements
        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextMessage = findViewById(R.id.editTextMessage)
        buttonSubmit = findViewById(R.id.buttonSubmit)

        // Set click listener for the submit button
        buttonSubmit.setOnClickListener {
            // Retrieve user input
            val name = editTextName.text.toString().trim()
            val email = editTextEmail.text.toString().trim()
            val message = editTextMessage.text.toString().trim()

            // Validate input
            if (name.isEmpty() || email.isEmpty() || message.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Perform your contact form submission logic here
                // For now, just display a toast indicating the form is submitted
                Toast.makeText(this, "Form submitted!\nName: $name\nEmail: $email\nMessage: $message", Toast.LENGTH_LONG).show()
            }
        }
    }
}