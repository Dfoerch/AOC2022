class Day1 : Day(1) {

    override val taskInput: List<String> = readTaskInput(day)
    override fun part1(input: List<String>): Any {

        var maxVal = 0
        var currentGroupMax = 0

        input.forEach {
            if(it.isNotEmpty()) {
                currentGroupMax += it.toInt()
            } else {
                if(currentGroupMax > maxVal){
                    maxVal = currentGroupMax
                    currentGroupMax = 0
                } else {
                    currentGroupMax = 0
                }
            }
        }
        return maxVal
    }

    override fun part2(input: List<String>): Any {
        var currentGroupMax = 0
        val nums = mutableListOf<Int>()

        input.forEach {
            if (it.isNotEmpty()) {
                currentGroupMax += it.toInt()
            } else {
                nums.add(currentGroupMax)
                currentGroupMax = 0
            }
        }
        if (currentGroupMax > 0 ) {
            nums.add(currentGroupMax)
            currentGroupMax = 0
        }
        nums.sortDescending()
        return nums.take(3).sum()
    }

    override fun test1(): Boolean {
        val testInput = getTestSolution()
        val solution = 24000
        return (part1(testInput) == solution)
    }

    override fun test2(): Boolean {
        val testInput = getTestSolution()
        val solution = 45000
        return part2(testInput) == solution
    }

    override fun solve1() {
        println(part1(taskInput))
    }

    override fun solve2() {
        println(part2(taskInput))
    }
}