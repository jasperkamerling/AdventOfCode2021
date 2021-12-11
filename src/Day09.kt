fun main() {
    data class Point(
        val upDown: Int,
        val leftRight: Int
    ) {
        fun getAdjacent(): List<Point> {
            return listOf(
                Point(upDown - 1, leftRight),
                Point(upDown + 1, leftRight),
                Point(upDown, leftRight - 1),
                Point(upDown, leftRight + 1)
            ).filter { it.upDown > -1 }.filter { it.leftRight > -1 }
        }
    }


    fun getLowPoints(matrix: List<List<Int>>): MutableList<Point> {
        val lowPoints = mutableListOf<Point>()
        matrix.indices.forEach { upDown ->
            (0 until matrix[0].size).forEach { leftRight ->
                val current: Int = matrix[upDown][leftRight]
                if (
                    current < (matrix.getOrNull(upDown - 1)?.getOrNull(leftRight) ?: Int.MAX_VALUE) &&
                    current < (matrix.getOrNull(upDown + 1)?.getOrNull(leftRight) ?: Int.MAX_VALUE) &&
                    current < (matrix.getOrNull(upDown)?.getOrNull(leftRight - 1) ?: Int.MAX_VALUE) &&
                    current < (matrix.getOrNull(upDown)?.getOrNull(leftRight + 1) ?: Int.MAX_VALUE)
                ) {
                    lowPoints.add(Point(upDown, leftRight))

                }
            }
        }
        return lowPoints
    }

    fun checkSurrounding(currentPoint: Point, lastPoint: Point?, matrix: List<List<Int>>): List<Point> {
        val surrounding: List<Point> = mutableListOf(
            Point(currentPoint.upDown - 1, currentPoint.leftRight),
            Point(currentPoint.upDown + 1, currentPoint.leftRight),
            Point(currentPoint.upDown, currentPoint.leftRight - 1),
            Point(currentPoint.upDown, currentPoint.leftRight + 1)
        )
        val realList = if (lastPoint != null) surrounding.minus(lastPoint)
        else surrounding

        return realList.filter { point ->
            (matrix.getOrNull(point.upDown - 1)?.getOrNull(point.leftRight)?.equals(9) == true) &&
                    (matrix.getOrNull(point.upDown + 1)?.getOrNull(point.leftRight)?.equals(9) == true) &&
                    (matrix.getOrNull(point.upDown)?.getOrNull(point.leftRight - 1)?.equals(9) == true) &&
                    (matrix.getOrNull(point.upDown)?.getOrNull(point.leftRight + 1)?.equals(9) == true)

        }
    }

    fun part1(input: List<String>): Int {
        val matrix = input.map { it.map { it.digitToInt() } }
        return getLowPoints(matrix)
            .sumOf { matrix[it.upDown][it.leftRight] + 1 }
    }

    fun part2(input: List<String>): Int {
        val matrix = input.map { it.map { it.digitToInt() } }
        val basins =
            getLowPoints(matrix).map { lowPoint ->
                val visited = mutableListOf(lowPoint)
                val basin = mutableListOf(lowPoint)
                val toVisit = mutableListOf<Point>()
                toVisit.addAll(lowPoint.getAdjacent())
                while (toVisit.isNotEmpty()) {
                    val point = toVisit[0]
                    if (point.upDown < matrix.size &&
                        point.leftRight < matrix[0].size &&
                        matrix[point.upDown][point.leftRight] != 9
                    ) {
                        toVisit.addAll(point.getAdjacent().filter { it !in visited })
                        basin.add(point)
                    }
                    visited.add(point)
                    toVisit.remove(point)

                }
                return@map basin.distinct().count()
            }
        return basins.sorted()
            .takeLast(3)
            .foldRight(1) { a, b -> a * b }


    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputLines("Day09_test")
    val input = readInputLines("Day09")
    check(part1(testInput) == 15)
    println("part 1: ${part1(input)}")

    check(part2(testInput) == 1134)
    println("part 2: ${part2(input)}")
}
