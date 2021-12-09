fun main() {
    fun part1(input: List<String>): Int {
        val matrix = input.map { it.map { it.digitToInt() } }
        var riskLevel = 0

        for (upDown in matrix.indices) {
            for (leftRight in 0 until matrix[0].size) {
                val current: Int = matrix[upDown][leftRight]
                if (
                    current < (matrix.getOrNull(upDown - 1)?.getOrNull(leftRight) ?: Int.MAX_VALUE) &&
                    current < (matrix.getOrNull(upDown + 1)?.getOrNull(leftRight) ?: Int.MAX_VALUE) &&
                    current < (matrix.getOrNull(upDown)?.getOrNull(leftRight - 1) ?: Int.MAX_VALUE) &&
                    current < (matrix.getOrNull(upDown)?.getOrNull(leftRight + 1) ?: Int.MAX_VALUE)
                ) {
                    riskLevel += current + 1
                }
            }
        }

        return riskLevel
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputLines("Day09_test")
    val input = readInputLines("Day09")
    check(part1(testInput) == 15)
    println("part 1: ${part1(input)}")

//    check(part2(testInput) == 5)
//    println("part 2: ${part2(input)}")
}
