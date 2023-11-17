import com.example.onlinesolution.CartItem
import java.util.Date
data class Order(
    val orderId: String?,
    val orderDate: String,
    val items: List<CartItem>,
    val totalPrice: Double,
    val totalItems: Int,
    val isFetchAtTill: Boolean,
    val isReceived: Boolean = false // New property to track if the order is received
) {
    // Override toString() to display details in a readable format
    override fun toString(): String {
        return "Order ID: $orderId\nOrder Date: $orderDate\nTotal Price: $totalPrice\nTotal Items: $totalItems\nReceived: $isReceived"
    }

}