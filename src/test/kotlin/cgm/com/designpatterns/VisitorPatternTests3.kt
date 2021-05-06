package cgm.com.designpatterns

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class VisitorPatternTests3 {
    @Test
    internal fun name() {
        val items: MutableList<Visitable> = ArrayList<Visitable>()
        val p1 = ItemSoldInPieces("CO1", "Cereali", 1.0, 2)
        val p2 = ItemSoldInPieces("CO2", "Quaderno", 1.0, 1)
        val p3 = ItemSoldInWeight("CO3", "Mele", 1.0, 1.0)
        items.add(p1)
        items.add(p2)
        items.add(p3)
        val totalCost: Double = calculateCost(items)
        totalCost shouldBe 4
    }

    private fun calculateCost(items: List<Visitable>): Double {
        var total = 0.0
        val visitor: Visitor = ShoppingVisitor()
        for (item in items) {
            total += item.accept(visitor)!!
        }
        return total
    }
}

abstract class Item(code: String, description: String)

class ItemSoldInWeight(code: String, description: String, var unitPrice: Double, var weight: Double) :
    Item(code, description), Visitable {

    override fun accept(visitor: Visitor): Double {
        return visitor.visit(this);
    }

}
class ItemSoldInPieces(code: String, description: String, var unitPrice: Double, var numberOfPieces: Int) :
    Item(code, description), Visitable {

    override fun accept(visitor: Visitor): Double {
        return visitor.visit(this);
    }
}

class ShoppingVisitor : Visitor {
    override fun visit(item: ItemSoldInWeight): Double{
        return item.weight * item.unitPrice
    }

    override fun visit(item: ItemSoldInPieces): Double {
        return item.numberOfPieces * item.unitPrice
    }
}

interface Visitable {
    fun accept(visitor: Visitor): Double
}

interface Visitor {
    fun visit(item: ItemSoldInWeight): Double
    fun visit(item: ItemSoldInPieces): Double
}