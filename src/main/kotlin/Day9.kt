import kotlin.math.abs

class Day9 : Day(9) {

    override val taskInput: List<String> = readTaskInput(day)

    override fun part1(input: List<String>): Any {
        val head = Point(0,0)
        val tail = Point(0,0)
        val tails = mutableListOf<Point>()
        tails.add(tail.copy())

        val movements = input.map { line -> line.split(" ") }.map { it[0] to it[1].toInt() }
        movements.forEach { mov ->
            repeat(mov.second) {
                head.movement(mov.first)
                val newTail = tailMove(head, tail)
                if (newTail != null) {
                    tails.add(newTail.copy())
                }
            }

        }
        return tails.distinct().size
    }

    override fun part2(input: List<String>): Any {
        val tail = Point(0,0)
        val tails = mutableListOf<Point>()
        tails.add(tail.copy())

        val pointArr = Array(10) { Point(0,0) }

        val movements = input.map { line -> line.split(" ") }.map { it[0] to it[1].toInt() }
        movements.forEach { mov ->
            repeat(mov.second) {
                pointArr.first().movement(mov.first)
                pointArr[0] = pointArr.first().copy()
                for (i in 1 until pointArr.size) {
                    val newTail = tailMove(pointArr[i-1], pointArr[i])
                    if (newTail != null) {
                        pointArr[i] = newTail.copy()
                    }
                }
                tails.add(pointArr.last().copy())
            }

        }
        return tails.distinct().size
    }

    override fun test1(): Boolean {
        val testInput = getTestSolution()
        val solution = 13
        return (part1(testInput) == solution)
    }

    override fun test2(): Boolean {
        val testInput = getTestSolution()
        val solution = 36
        return part2(testInput) == solution
    }

    override fun solve1() {
        println(part1(taskInput))
    }

    override fun solve2() {
        println(part2(taskInput))
    }

    private fun Point.movement(direction: String) {
        when(direction) {
            "U" -> this.y = this.y + 1
            "D" -> this.y = this.y - 1
            "R" -> this.x = this.x + 1
            "L" -> this.x = this.x - 1
            else -> {}
        }
    }

    private fun tailMove(head: Point, tail: Point) : Point? {

        val isAdjacent = -1..1
        val deltaX = head.x - tail.x
        val deltaY = head.y - tail.y

        if (deltaX != 0 && deltaY != 0 && (abs(deltaY) > 1 || abs(deltaX) > 1)) {
            tail.x = tail.x + deltaX.coerceIn(isAdjacent)
            tail.y = tail.y + deltaY.coerceIn(isAdjacent)
        } else if (abs(deltaX) > 1 && deltaY == 0) {
            tail.x = tail.x +  deltaX.coerceIn(isAdjacent)
        } else if (abs(deltaY) > 1 && deltaX == 0) {
            tail.y = tail.y +  deltaY.coerceIn(isAdjacent)
        } else {
            return null
        }

        return tail
    }

    private data class Point(var x: Int, var y: Int)
}

