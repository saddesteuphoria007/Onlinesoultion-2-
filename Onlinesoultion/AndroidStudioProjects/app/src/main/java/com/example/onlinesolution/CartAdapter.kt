package com.example.onlinesolution

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(private val cartItems: MutableList<CartItem>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    interface OnItemClickListener {
        fun onRemoveItemClick(position: Int)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.cartItemName)
        val itemPrice: TextView = itemView.findViewById(R.id.cartItemPrice)
        val removeItemButton: Button = itemView.findViewById(R.id.removeCartItemButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_cart_item_layout, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]

        holder.itemName.text = cartItem.name
        holder.itemPrice.text = "Price: ${cartItem.price}"

        holder.removeItemButton.setOnClickListener {
            // Call the remove item click handler
            onItemClickListener?.onRemoveItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    fun removeItem(position: Int) {
        cartItems.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, cartItems.size)
    }
}