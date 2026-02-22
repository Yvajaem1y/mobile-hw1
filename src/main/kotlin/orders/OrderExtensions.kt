package orders

/**
 * Applies a percentage discount to every product in the order.
 *
 * Hint: use [products] to read the current list, [removeProductById] and
 * [addProduct] to replace each product with a discounted copy.
 * Use [Product.copy] to create a new product with a modified price.
 *
 * @param discountPercent discount percentage (e.g. 10 means 10%)
 * @param logger optional callback invoked with a log message for each product
 */
fun Order.applyDiscount(
    discountPercent: Int,
    logger: ((String) -> Unit)? = null
) {
    val multiplier = (100 - discountPercent) / 100.0

    val currentProducts = products.toList()

    currentProducts.forEach { product ->
        removeProductById(product.id)
        val discountedPrice = (product.price * multiplier).toInt()
        val discountedProduct = product.copy(price = discountedPrice)
        addProduct(discountedProduct)
        logger?.invoke("Discounted product ${product.id}")
    }
}
