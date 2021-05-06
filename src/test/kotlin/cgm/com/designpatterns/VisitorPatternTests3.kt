package cgm.com.designpatterns

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class VisitorPatternTests3 {
    @Test
    internal fun name() {
        val items: MutableList<Item> = ArrayList()
        val p1 = Item.ItemSoldInPieces("CO1", "Cereali", 1.0, 2)
        val p2 = Item.ItemSoldInPieces("CO2", "Quaderno", 1.0, 1)
        val p3 = Item.ItemSoldInWeight("CO3", "Mele", 1.0, 1.0)
        items.add(p1)
        items.add(p2)
        items.add(p3)
        val totalCost: Double = calculateCost(items)
        totalCost shouldBe 4
    }

    private fun calculateCost(items: List<Item>): Double {
        var total = 0.0
        var shoppingItem = ShoppingItem()
        for (item in items) {
            total += shoppingItem.getPrice(item)
        }
        return total
    }
}

sealed class Item {
    data class ItemSoldInWeight(
        private val code: String,
        private val description: String,
        var unitPrice: Double,
        var weight: Double
    ) : Item()

    data class ItemSoldInPieces(
        private val code: String,
        private val description: String,
        var unitPrice: Double,
        var numberOfPieces: Int
    ) : Item()
}

class ShoppingItem  {
     fun getPrice(item: Item): Double{
         return when(item) {
             is Item.ItemSoldInWeight -> item.weight * item.unitPrice
             is Item.ItemSoldInPieces -> item.numberOfPieces * item.unitPrice
         }
    }
}