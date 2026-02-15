package orders

/**
 * Returns a human-readable description of the order status.
 * Use a `when` expression on [order].status:
 *   - Created  -> "Order {id} is new"
 *   - Paid     -> "Order {id} is paid"
 *   - Cancelled -> "Order {id} is cancelled: {reason}"
 */
fun processOrder(order: Order): String {
    return when(order.status){
        is OrderStatus.Cancelled -> "Order ${order.id} is cancelled: ${(order.status as OrderStatus.Cancelled).reason}"
        is OrderStatus.Created -> "Order ${order.id} is cancelled: {reason}"
        is OrderStatus.Paid -> "Order ${order.id} is paid"
    }
}
