import kotlin.math.roundToInt

fun main() {
    fun part1(input: List<String>): Int {
        val matrix = input.map { it.map { it.digitToInt() } }
        var gammaRateBin = ""
        var epsilonRateBin = ""
        for (i in 0 until matrix[0].size) {
            gammaRateBin += matrix.map { it[i] }.average().roundToInt()
            epsilonRateBin += matrix.map { it[i] }.average().roundToInt().xor(1)
        }

        val gammaRate = gammaRateBin.toInt(2)
        val epsilonRate = epsilonRateBin.toInt(2)
        println("gammaRate: ${gammaRate} - ${gammaRateBin} ")
        println("epsilonRate: ${epsilonRate} - ${epsilonRateBin} ")
        return gammaRate * epsilonRate
    }

    fun part2(input: List<String>): Int {
        val matrix = input.map { it.map { it.digitToInt() } }
        var oxygenMatrix = matrix
        for (i in 0 until matrix[0].size) {
            val average = oxygenMatrix.map { it[i] }.average()
            val oxygen = if(average == 0.5) 1 else average.roundToInt()
            oxygenMatrix = oxygenMatrix.filter { it[i] == oxygen }
            if(oxygenMatrix.size == 1) break
        }

        var co2Matrix = matrix
        for (i in 0 until matrix[0].size) {
            val average = co2Matrix.map { it[i] }.average()
            val co2 = if(average == 0.5) 0 else average.roundToInt()xor(1)
            co2Matrix = co2Matrix.filter { it[i] == co2 }
            if(co2Matrix.size == 1) break
        }
        val oxygen = oxygenMatrix[0].joinToString("").toInt(2)
        val co2 = co2Matrix[0].joinToString("").toInt(2)

        return oxygen * co2
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
