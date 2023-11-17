package com.example.onlinesolution

import Order
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class ActivityHistory : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var historyListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        database = FirebaseDatabase.getInstance().reference
        historyListView = findViewById(R.id.historyListView)

        loadOrderHistory()
    }

    private fun loadOrderHistory() {
        val historyRef = database.child("orders")
        val historyListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val orderList = mutableListOf<Order>()

                for (orderSnapshot in dataSnapshot.children) {
                    val orderId = orderSnapshot.key.toString()
                    val order = orderSnapshot.getValue(Order::class.java)
                    order?.let {
                        orderList.add(it)
                    }
                }

                val adapter = OrderAdapter(this@ActivityHistory, R.layout.order_item, orderList)
                historyListView.adapter = adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors here
            }
        }

        historyRef.addValueEventListener(historyListener)
    }
}