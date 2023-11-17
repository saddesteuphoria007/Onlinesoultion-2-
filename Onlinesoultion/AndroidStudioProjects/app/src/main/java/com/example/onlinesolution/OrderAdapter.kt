package com.example.onlinesolution

import Order
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class OrderAdapter(context: Context, resource: Int, objects: List<Order>) :
    ArrayAdapter<Order>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false)
        }

        val order = getItem(position)

        val orderIdTextView: TextView = itemView!!.findViewById(R.id.orderIdTextView)
        val orderDateTextView: TextView = itemView.findViewById(R.id.orderDateTextView)
        val totalPriceTextView: TextView = itemView.findViewById(R.id.totalPriceTextView)
        val totalItemsTextView: TextView = itemView.findViewById(R.id.totalItemsTextView)
        val receivedTextView: TextView = itemView.findViewById(R.id.receivedTextView)

        orderIdTextView.text = "Order ID: ${order?.orderId}"
        orderDateTextView.text = "Order Date: ${order?.orderDate}"
        totalPriceTextView.text = "Total Price: ${order?.totalPrice}"
        totalItemsTextView.text = "Total Items: ${order?.totalItems}"
        receivedTextView.text = "Received: ${order?.isReceived}"

        return itemView
    }
}