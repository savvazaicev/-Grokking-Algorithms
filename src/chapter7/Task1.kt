package chapter7

object Task1 {
    @JvmStatic
    fun main(args: Array<String>) {
        testMain()
        testA()
    }

    private fun shortestWayWeight(
        graph: HashMap<String, HashMap<String, Int>>,
        costs: HashMap<String, Int>,
        parents: HashMap<String, String?>
    ): Int {
        val processed = mutableListOf<String>()
        var node = findLowestCostNode(costs, processed)
        while (node != null) {
            val cost = costs[node] ?: return 0
            val neighbors = graph[node]
            for (n in neighbors?.keys!!) {
                val neighbor = neighbors[n]
                if (neighbor != null) {
                    val newCost = cost + neighbor
                    val tempCost = costs[n] ?: -1
                    if (tempCost > newCost) {
                        costs[n] = newCost
                        parents[n] = node
                    }
                }
            }
            processed.add(node)
            node = findLowestCostNode(costs, processed)
        }
        return costs["end"]!!
    }

    private fun findLowestCostNode(costs: HashMap<String, Int>, processed: MutableList<String>): String? {
        var lowestCost = Int.MAX_VALUE
        var lowestCostNode: String? = null
        costs.keys.forEach { node ->
            val cost = costs[node]
            if (cost != null && cost < lowestCost && node !in processed) {
                lowestCost = cost
                lowestCostNode = node
            }
        }
        return lowestCostNode
    }

    private fun testMain() {
        val graph = hashMapOf(
            "start" to hashMapOf("a" to 6, "b" to 2),
            "a" to hashMapOf("end" to 1),
            "b" to hashMapOf("a" to 3, "end" to 5),
            "end" to hashMapOf()
        )
        val costs = hashMapOf("a" to 6, "b" to 2, "end" to Int.MAX_VALUE)
        val parents = hashMapOf("a" to "start", "b" to "start", "end" to null)

        println(shortestWayWeight(graph, costs, parents))
    }

    private fun testA() {
        val graph = hashMapOf(
            "start" to hashMapOf("a" to 5, "b" to 2),
            "a" to hashMapOf("c" to 4, "d" to 2),
            "b" to hashMapOf("a" to 8, "d" to 7),
            "c" to hashMapOf("end" to 3, "d" to 6),
            "d" to hashMapOf("end" to 1),
            "end" to hashMapOf()
        )
        val costs = hashMapOf(
            "a" to 5,
            "b" to 2,
            "c" to 9,
            "d" to 9,
            "end" to Int.MAX_VALUE
        )
        val parents = hashMapOf(
            "a" to "start",
            "b" to "start",
            "c" to "a",
            "d" to "b",
            "end" to null
        )

        println(shortestWayWeight(graph, costs, parents))
    }
}