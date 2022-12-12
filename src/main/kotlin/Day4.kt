class Day4 : Day(4) {

    override val taskInput: List<String> = readTaskInput(day)
    var score = 0

    override fun part1(input: List<String>): Any {
        input.forEach { pairs ->
            val sections = pairs.split(",")
            val ranges = sections.map {
                (it.split("-")[0].toInt()..it.split("-")[1].toInt()).toList()
            }.chunked(2)

            if (ranges.any { range -> range[0].containsAll(range[1]) || range[1].containsAll(range[0]) }) {
                score += 1
            }
        }
        return score
    }

    override fun part2(input: List<String>): Any {
        input.forEach { pairs ->
            val sections = pairs.split(",")
            val ranges = sections.map {
                (it.split("-")[0].toInt()..it.split("-")[1].toInt()).toList()
            }

            if ( ranges.first().any { ranges[1].contains(it) } ) {
                score += 1
            }
        }
        return score
    }


    override fun test1(): Boolean {
        val testInput = getTestSolution()
        val solution = 2
        return (part1(testInput) == solution)
    }

    override fun test2(): Boolean {
        val testInput = getTestSolution()
        val solution = 4
        return part2(testInput) == solution
    }

    override fun solve1() {
        println(part1(taskInput))
    }

    override fun solve2() {
        println(part2(taskInput))
    }
}