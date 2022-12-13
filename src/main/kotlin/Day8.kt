class Day8 : Day(8) {

    override val taskInput: List<String> = readTaskInput(day)
    private lateinit var ints: Array<IntArray>
    private var pointList = mutableListOf<Point>()
    private var maxLen = 0
    private var halfX = 0
    private var halfy = 0

    override fun part1(input: List<String>): Any {
        getPoints(input)
        return pointList.filter { it.isVisible }.size
    }

    override fun part2(input: List<String>): Any {
        getPoints(input)
        return pointList.maxOf { it.score }
    }

    private fun left(x: Int, line: IntArray) : Boolean {
        val height = line[x]
        var xpos = x - 1

        while (xpos >= 0) {
            if(height <= line[xpos]) {
                return false
            }
            xpos -= 1
        }
        return true
    }
    private fun leftScore(x: Int, line: IntArray) : Int {
        val height = line[x]
        var xpos = x - 1
        var score = 0

        while (xpos >= 0) {
            if(height <= line[xpos]) {
                score += 1
                break
            }
            score += 1
            xpos -= 1
        }
        return score
    }

    private fun right(x: Int, line: IntArray) : Boolean {
        val height = line[x]
        var xpos = x + 1

        while (xpos < maxLen) {
            if(height <= line[xpos]) {
                return false
            }
            xpos += 1
        }
        return true
    }

    private fun rightScore(x: Int, line: IntArray) : Int {
        val height = line[x]
        var xpos = x + 1
        var score = 0

        while (xpos < maxLen) {
            if(height <= line[xpos]) {
                score += 1
                break
            }
            xpos += 1
            score += 1
        }
        return score
    }

    private fun top(x: Int, y: Int) : Boolean {
        val height = ints[y][x]
        var ypos = y - 1

        while (ypos >= 0) {
            if(height <= ints[ypos][x]) {
                return false
            }
            ypos -= 1
        }
        return true
    }
    private fun topScore(x: Int, y: Int) : Int {
        val height = ints[y][x]
        var ypos = y - 1
        var score = 0

        while (ypos >= 0) {
            if(height <= ints[ypos][x]) {
                score += 1
                break
            }
            ypos -= 1
            score += 1
        }
        return score
    }

    private fun bottom(x: Int, y: Int) : Boolean {
        val height = ints[y][x]
        var ypos = y + 1

        while (ypos < ints.size) {
            val a = ints[ypos][x]
            if(height <= a) {
                return false
            }
            ypos += 1
        }
        return true
    }

    private fun bottomScore(x: Int, y: Int) : Int {
        val height = ints[y][x]
        var ypos = y + 1
        var score = 0

        while (ypos < ints.size) {
            val a = ints[ypos][x]
            if(height <= a) {
                score += 1
                break
            }
            ypos += 1
            score += 1
        }
        return score
    }

    private fun Point.isVisible() : Boolean {
        return if(y == 0 || x == 0 || y == ints.lastIndex || x == ints[0].lastIndex) true else
                 (this.left || this.right || this.top || this.bottom)
    }

    private fun getPoints(input: List<String>) {
        ints = input.map { it.map { num -> num.digitToInt() }.toIntArray() }.toTypedArray()
        maxLen = ints[0].size
        halfX = maxLen / 2
        halfy = ints.size / 2
        ints.indices.forEach { y ->
            ints[y].indices.forEach { x ->
                val line = ints[y]
                val currentPoint = Point(x, y)
                currentPoint.left = left(x, line)
                currentPoint.right = right(x, line)
                currentPoint.top = top(x, y)
                currentPoint.bottom = bottom(x, y)
                currentPoint.score = (
                    leftScore(x, line) *
                    rightScore(x, line) *
                    topScore(x, y) *
                    bottomScore(x, y)
                )
                currentPoint.isVisible = currentPoint.isVisible()
                pointList.add(currentPoint)
            }
        }
    }

    override fun test1(): Boolean {
        val testInput = getTestSolution()
        val solution = 21
        return (part1(testInput) == solution)
    }

    override fun test2(): Boolean {
        val testInput = getTestSolution()
        val solution = 8
        return part2(testInput) == solution
    }

    override fun solve1() {
        println(part1(taskInput))
    }

    override fun solve2() {
        println(part2(taskInput))
    }
}

class Point(val x: Int, val y: Int) {
    var score = 0
    var isVisible: Boolean = false
    var left: Boolean = false
    var right: Boolean = false
    var top: Boolean = false
    var bottom: Boolean = false
}