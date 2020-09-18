package chapter4

object Task2 {
    @JvmStatic
    fun main(args: Array<String>) {
        println(listOf(1, 2, 3, 4, 10).length())
    }

    private fun List<Int>.length(): Int = if (isEmpty()) 0 else 1 + drop(1).length()
}