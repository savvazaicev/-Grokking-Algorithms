package chapter9

import kotlin.math.max

object Task2 {
    @JvmStatic
    fun main(args: Array<String>) {
        val keys = arrayOf("Вода", "Книга", "Еда", "Куртка", "Камера")
        val items = hashMapOf(
            "Вода" to arrayOf(10, 3),
            "Книга" to arrayOf(3, 1),
            "Еда" to arrayOf(9, 2),
            "Куртка" to arrayOf(5, 2),
            "Камера" to arrayOf(6, 1)
        )
        val maxWeight = 6
        print(findMaxSum(items, maxWeight, keys))
    }

    private fun findMaxSum(
        items: HashMap<String, Array<Int>>,
        maxWeight: Int,
        keys: Array<String>
    ): Int {
        val table = Array(items.keys.size) { IntArray(maxWeight) { 0 } }
        table.forEachIndexed { i, item ->
            item.forEachIndexed { j, weight ->
                val itemCost = items[keys[i]]?.get(0)
                val itemWeight = items[keys[i]]?.get(1)
                if (itemCost != null && itemWeight != null) {
                    if (i == 0) {
                        if (itemWeight <= j + 1) {
                            table[i][j] = itemCost
                        }
                    } else {
                        val remainingSpaceCost = when {
                            j - itemWeight >= 0 -> table[i - 1][j - itemWeight]
                            else -> 0
                        }
                        table[i][j] =
                            max(table[i - 1][j], if (itemWeight <= j + 1) itemCost + remainingSpaceCost else 0)
                    }
                }
            }
        }
        table.forEach { item ->
            item.forEach {
                print("$it ")
            }
            println()
        }
        return table[keys.size - 1].max()!!
    }
}