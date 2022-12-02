class Day2 : Day(2) {

    override val taskInput: List<String> = readTaskInput(day)

    private val strat = mapOf(
        "ROCK" to "PAPER",
        "PAPER" to "SCISSORS",
        "SCISSORS" to "ROCK"
    )
    private val scores = mapOf(
        "ROCK" to 1,
        "PAPER" to 2,
        "SCISSORS" to 3
    )
    private var totalScore = 0


    override fun part1(input: List<String>): Any {
        input.forEach {
            val opp = translateMove(it.split(" ").first())
            val me = translateMove(it.split(" ")[1])
            totalScore += getScoring(opp, me)
            totalScore += scores[me]!!
        }
        return totalScore
    }

    override fun part2(input: List<String>): Any {
        input.forEach {
            val opp = translateMove(it.split(" ").first())
            val me = determineMove(it.split(" ")[1], opp)
            totalScore += getScoring(translateMove(it.split(" ").first()), me)
            totalScore += scores[me]!!
        }
        return totalScore
    }

    override fun test1(): Boolean {
        val testInput = getTestSolution()
        val solution = 15
        return (part1(testInput) == solution)
    }

    override fun test2(): Boolean {
        val testInput = getTestSolution()
        val solution = 12
        return part2(testInput) == solution
    }

    override fun solve1() {
        println(part1(taskInput))
    }

    override fun solve2() {
        println(part2(taskInput))
    }

    private fun translateMove(move: String) : String {
        return when(move) {
            "A","X" -> "ROCK"
            "B","Y" -> "PAPER"
            "C","Z" -> "SCISSORS"
            else -> ""
        }
    }

    private fun determineMove(outcome: String, oppMove: String) : String {
        return when(outcome) {
            "Y" -> oppMove
            "X" -> strat.entries.find { it.value == oppMove }!!.key
            "Z" -> strat[oppMove]!!
            else -> ""
        }
    }

    private fun getScoring(oppMove: String, myMove: String) : Int {
        return if(oppMove == myMove) {
            3
        } else if (strat[oppMove] == myMove) {
            6
        } else {
            0
        }
    }

}