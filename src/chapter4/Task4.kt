package chapter4

object Task4 {
    @JvmStatic
    fun main(args: Array<String>) {
        println(listOf(1, 3, 5, 7, 10, 15, 19).searchIndexOfNumber(10))
    }

    private fun List<Int>.searchIndexOfNumber(number: Int): Int = when (get(size / 2)) {
        number -> size / 2
        else -> get(size / 2).let {
            when {
                it > number -> take(size / 2).searchIndexOfNumber(number)
                it < number -> drop(size / 2).searchIndexOfNumber(number) + size / 2
                else -> it
            }
        }
    }
}