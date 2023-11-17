package com.example.onlinesolution

import Order
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class CartActivity : AppCompatActivity() {
    // Declare a reference to the Firebase Realtime Database
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        // Initialize Firebase
        database = FirebaseDatabase.getInstance().reference

        val cartItemsLayout: LinearLayout = findViewById(R.id.cartItemsLayout)
        val totalPriceTextView: TextView = findViewById(R.id.totalPriceTextView)
        val totalItemsTextView: TextView = findViewById(R.id.totalItemsTextView)

        val checkout: Button = findViewById(R.id.checkoutButton)

        // Retrieve the list of added items from the intent
        val cartItems: List<CartItem> = intent.getSerializableExtra("cartItems") as List<CartItem>

        // Display the added items in the cart layout
        displayCartItems(cartItems)

        // Retrieve total price and total items from the intent
        val totalPrice: Double = intent.getDoubleExtra("totalPrice", 0.0)
        val totalItems: Int = intent.getIntExtra("totalItems", 0)

        // Update your UI with total price and total items
        totalPriceTextView.text = "Total Price: $totalPrice"
        totalItemsTextView.text = "Total Items: $totalItems"

        checkout.setOnClickListener {
            // Call a function to save the order to the database
            saveOrderToDatabase(cartItems, totalPrice, totalItems)
        }
    }

    private fun displayCartItems(cartItems: List<CartItem>) {
        val cartItemsLayout: LinearLayout = findViewById(R.id.cartItemsLayout)

        for (item in cartItems) {
            val cartItemView = layoutInflater.inflate(R.layout.activity_cart_item_layout, null)

            val itemName: TextView = cartItemView.findViewById(R.id.cartItemName)
            val itemPrice: TextView = cartItemView.findViewById(R.id.cartItemPrice)

            itemName.text = item.name
            itemPrice.text = "R${"%.2f".format(item.price)}"

            cartItemsLayout.addView(cartItemView)
        }
    }

    private fun saveOrderToDatabase(cartItems: List<CartItem>, totalPrice: Double, totalItems: Int) {
        val orderId = System.currentTimeMillis().toString()
        val orderDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val order = Order(orderId, orderDate, cartItems, totalPrice, totalItems, true)

        database.child("orders").child(orderId).setValue(order)
            .addOnSuccessListener {
                Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_SHORT).show()

                Toast.makeText(this, "Please fetch your order at till and pay there!, sorry for the inconvinience", Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to place order. Please try again.", Toast.LENGTH_SHORT).show()
            }
    }
}