package com.example.onlinesolution

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ViewUsers : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var userList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_users)

        // Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Initialize UI components
        userList = findViewById(R.id.userList)

        // Fetch and display users
        fetchUsers()
    }

    // Function to fetch users from Firebase Authentication
    private fun fetchUsers() {
        auth.currentUser?.let { currentUser ->
            val users = listOf(currentUser)
            displayUsers(users)
        }
    }

    // Display user names in the ListView
    private fun displayUsers(users: List<FirebaseUser>) {
        // Extract user names
        val userNames = users.map { it.displayName ?: "No Name" }

        // Create ArrayAdapter and set it to the ListView
        val adapter = ArrayAdapter(this@ViewUsers, android.R.layout.simple_list_item_1, userNames)
        userList.adapter = adapter
    }
}