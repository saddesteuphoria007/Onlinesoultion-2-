package com.example.onlinesolution

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Home : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val logout = findViewById<ImageButton>(R.id.Logout)
        val menu = findViewById<ImageButton>(R.id.menuButton)
        val cart = findViewById<ImageButton>(R.id.cart)
        val con = findViewById<ImageButton>(R.id.contacts)

        logout.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)}

        menu.setOnClickListener{
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)}

        cart.setOnClickListener{
            val intent = Intent(this, cart::class.java)
            startActivity(intent)}

        con.setOnClickListener{
                val intent = Intent(this, ContactUsActivity::class.java)
            startActivity(intent)}


    }
}