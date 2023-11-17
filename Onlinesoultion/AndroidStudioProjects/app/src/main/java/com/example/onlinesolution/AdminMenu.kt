package com.example.onlinesolution

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AdminMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_menu)

        val logout = findViewById<Button>(R.id.logOutButton) as Button
        val  orders = findViewById<Button>(R.id.VoiceButton) as Button
        val users = findViewById<Button>(R.id.addItemButton) as Button
        val newItem = findViewById<Button>(R.id.createCategoryButton) as Button
        val email = findViewById<Button>(R.id.takePhotoButton) as Button
        val hist = findViewById<Button>(R.id.viewListButton) as Button

        users.setOnClickListener { val intent = Intent(this, ViewUsers::class.java)
            startActivity(intent) }

        orders.setOnClickListener { val intent = Intent(this, ViewUsers::class.java)
            startActivity(intent) }

        hist.setOnClickListener {
            val intent = Intent(this, ActivityHistory::class.java)
            startActivity(intent)
        }

        logout.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)

        }

        newItem.setOnClickListener {

            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }



    }
}