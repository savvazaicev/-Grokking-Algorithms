package chapter4

object Task1 {
    @JvmStatic
    fun main(args: Array<String>) {
        println(listOf(1, 2, 3, 4, 10).sum())
    }

    private fun List<Int>.sum(): Int = if (isEmpty()) 0 else get(0) + drop(1).sum()
}