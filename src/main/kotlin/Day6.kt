class Day6 : Day(6) {

    override val taskInput: List<String> = readTaskInput(day)

    override fun part1(input: List<String>): Any {
        val startfOfPacket = input.first().windowed(4).find { window ->
            window.groupBy { it }.size == 4
        } ?: ""
        return input.first().indexOf(startfOfPacket) + 4
    }

    override fun part2(input: List<String>): Any {
        val startfOfPacket = input.first().windowed(14).find { window ->
            window.groupBy { it }.size == 14
        } ?: ""
        return input.first().indexOf(startfOfPacket) + 14
    }

    override fun test1(): Boolean {
        val testInput = getTestSolution()
        val solution = 7
        return (part1(testInput) == solution)
    }

    override fun test2(): Boolean {
        val testInput = getTestSolution()
        val solution = 19
        return part2(testInput) == solution
    }

    override fun solve1() {
        println(part1(taskInput))
    }

    override fun solve2() {
        println(part2(taskInput))
    }
}