package chapter4

object Task3 {
    @JvmStatic
    fun main(args: Array<String>) {
        println(listOf(1, 2, 3, 10, 4).max())
    }

    private fun List<Int>.max(): Int = if (size == 1) get(0) else drop(1).max().let { if (it > get(0)) it else get(0) }
}