package orders

class Order(
    val id: Int
) : PriceCalculator {

    private val _products: MutableList<Product> = mutableListOf()

    /** Read-only view of products in the order. */
    val products: List<Product> get() = _products.toList()

    var status: OrderStatus = OrderStatus.Created
        private set

    /**
     * Adds a product to the order.
     * If the product is null, it should be ignored.
     */
    fun addProduct(product: Product?) {
        if (product!=null) _products.add(product)
    }

    /**
     * Removes the first product matching [productId].
     */
    fun removeProductById(productId: Int) {
        for (i in 0.._products.size){
            if (_products[i].id == productId){
                _products.removeAt(i)
                return
            }
        }

    }

    /**
     * Returns the total price of all products in the order.
     */
    override fun calculateTotal(): Int {
        var sum : Int = 0

        products.forEach { product -> sum+=product.price }

        return sum
    }

    /**
     * Marks the order as paid.
     * Throws [IllegalStateException] if the order has no products.
     */
    fun pay() {
        if (_products.isNotEmpty()) status= OrderStatus.Paid
        else throw IllegalStateException("No products to pay for")
    }

    /**
     * Cancels the order with the given reason.
     * If [reason] is null, use "Unknown reason".
     */
    fun cancel(reason: String?) {
        status = OrderStatus.Cancelled(reason = reason ?: "Unknown reason")
    }
}
