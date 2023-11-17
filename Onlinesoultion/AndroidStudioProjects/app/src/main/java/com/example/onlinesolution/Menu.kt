package com.example.onlinesolution

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinesolution.databinding.ActivityMenuBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class Menu : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMenuBinding

    // cart
    private val cartItems = mutableListOf<CartItem>()
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView and Adapter
        val cartRecyclerView: RecyclerView = findViewById(R.id.cartRecyclerView)
        cartAdapter = CartAdapter(cartItems)
        cartRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        cartRecyclerView.adapter = cartAdapter

        cartAdapter.setOnItemClickListener(object : CartAdapter.OnItemClickListener {
            override fun onRemoveItemClick(position: Int) {
                // Call the remove item click handler
                cartAdapter.removeItem(position)
                updateCart()
            }
        })

        // Initialize Firebase
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)

        val database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        val storageRef = FirebaseStorage.getInstance().getReference()

        val home = findViewById<ImageButton>(R.id.homeButton)
        val contact = findViewById<ImageButton>(R.id.contacts)
        val cart = findViewById<ImageButton>(R.id.cart)
        val faq = findViewById<ImageButton>(R.id.contacts)

        // Set click listeners for the images to open the edit dialog
        setClickListenerForMenuItem(R.id.image1, MenuItem("Peach Juice", 10.00, R.drawable.drink))
        setClickListenerForMenuItem(R.id.aloejpg, MenuItem("Aloe Vera Drink", 12.00, R.drawable.aloejpg))
        setClickListenerForMenuItem(R.id.caffeineCoffee, MenuItem("Coffee", 15.00, R.drawable.caffeine))

        // Set click listeners for snack buttons
        setAddToCartClickListener(R.id.button1, R.id.snackName1, R.id.snackPrice1, 11.00)
        setAddToCartClickListener(R.id.button2, R.id.snackName2, R.id.snackPrice2, 12.00)
        setAddToCartClickListener(R.id.button3, R.id.snackName3, R.id.snackPrice3, 10.00)

        // Set click listeners for lunch buttons
        setAddToCartClickListener(R.id.pizza, R.id.lunchName1, R.id.lunchPrice1, 40.00)
        setAddToCartClickListener(R.id.hot, R.id.lunchName2, R.id.lunchPrice2, 30.00)
        setAddToCartClickListener(R.id.wrap, R.id.lunchName3, R.id.lunchPrice3, 25.00)

        // Set click listeners for drink buttons
        setAddToCartClickListener(R.id.juice1, R.id.drinkName1, R.id.drinkPrice1, 10.00)
        setAddToCartClickListener(R.id.juice2, R.id.drinkName2, R.id.drinkPrice2, 12.00)
        setAddToCartClickListener(R.id.coffe, R.id.drinkName3, R.id.drinkPrice3, 15.00)

        home.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
        }

        cart.setOnClickListener {
            // Calculate total price and number of items
            val totalPrice = cartItems.sumByDouble { it.price }
            val totalItems = cartItems.size

            // Pass the total price and number of items to CartActivity
            val intent = Intent(this, CartActivity::class.java)
            intent.putExtra("cartItems", ArrayList(cartItems))
            intent.putExtra("totalPrice", totalPrice)
            intent.putExtra("totalItems", totalItems)
            startActivity(intent)
        }
    }

    private fun setClickListenerForMenuItem(imageId: Int, menuItem: MenuItem) {
        findViewById<ImageView>(imageId).setOnClickListener {
            showEditMenuDialog(menuItem)
        }
    }

    private fun setAddToCartClickListener(buttonId: Int, nameId: Int, priceId: Int, defaultPrice: Double) {
        findViewById<Button>(buttonId).setOnClickListener {
            val snackName: TextView = findViewById(nameId)
            val snackPrice: TextView = findViewById(priceId)

            // Get name and price
            val name = snackName.text.toString()
            val price = snackPrice.text.toString().toDoubleOrNull() ?: defaultPrice

            // Add to cartItems list
            cartItems.add(CartItem(name, price))

            // Display a toast indicating that the item has been added to the cart
            Toast.makeText(this, "Item added to cart: $name", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showEditMenuDialog(menuItem: MenuItem) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_edit_menu, null)

        val editTextName = dialogView.findViewById<EditText>(R.id.editTextName)
        val editTextPrice = dialogView.findViewById<EditText>(R.id.editTextPrice)
        val imageView = dialogView.findViewById<ImageView>(R.id.imageView)

        editTextName.setText(menuItem.name)
        editTextPrice.setText(menuItem.price.toString())
        imageView.setImageResource(menuItem.imageResource)

        AlertDialog.Builder(this)
            .setTitle("Edit Menu Item")
            .setView(dialogView)
            .setPositiveButton("Save") { _, _ ->
                // Update the menu item with new values
                menuItem.name = editTextName.text.toString()
                menuItem.price = editTextPrice.text.toString().toDouble()

                // Update the UI with the new values
                updateMenuItemUI(menuItem)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun updateMenuItemUI(menuItem: MenuItem) {
        // Update the TextViews and other UI elements with the new values
        // For example:
        val textViewName = findViewById<TextView>(R.id.drinkName1)
        val textViewPrice = findViewById<TextView>(R.id.drinkPrice1)
        val imageView = findViewById<ImageView>(R.id.image1)

        textViewName.text = menuItem.name
        textViewPrice.text = "Price: R${menuItem.price}"
        imageView.setImageResource(menuItem.imageResource)
    }

    private fun updateCart() {
        // Calculate total price and number of items
        val totalPrice = cartItems.sumByDouble { it.price }
        val totalItems = cartItems.size

        // Notify the adapter about the data change
        cartAdapter.notifyDataSetChanged()
    }
}