class Day5 : Day(5) {

    override val taskInput: List<String> = readTaskInput(day)

    override fun part1(input: List<String>): Any {
        val maxStacks = numOfStacks(input)
        val stacks = List(maxStacks) { ArrayDeque<Char>() }
        getStacks(input) { index, char ->
            stacks[index].addLast(char)
        }

        val moves = getMoves(input)
        val newStacks = applyMoves(stacks, moves)

        return newStacks.map {
            it.first()
        }.joinToString("")
    }

    override fun part2(input: List<String>): Any {
        val maxStacks = numOfStacks(input)
        val stacks = List(maxStacks) { ArrayDeque<Char>() }
        getStacks(input) { index, char ->
            stacks[index].addLast(char)
        }

        val moves = getMoves(input)
        val newStacks = applySteps(stacks, moves)

        return newStacks.map {
            it.first()
        }.joinToString("")
    }

    private fun getStacks(input: List<String>, addCharToStack: (Int, Char) -> Unit) {
        input.filter { it.contains("[") }
            .map { row ->
                row.mapIndexed { index, char ->
                    if (char.isLetter()) {
                        addCharToStack(index / 4, row[index])
                    }
                }
            }
    }

    private fun numOfStacks(input: List<String>): Int {
        return input.dropWhile { it.contains("[") }
            .first()
            .split(" ")
            .filter { it.isNotBlank() }
            .maxOf { it.toInt() }
    }

    private fun getMoves(input: List<String>): List<Move> {
        return input.filter { it.contains("move") }
            .map { tokens ->
                val nums = tokens.split(" ")
                    .filter { it.toIntOrNull() != null }
                    .map { it.toInt() }
                Move(nums[0], nums[1], nums[2])
            }
    }

    private fun applyMoves(stacks: List<ArrayDeque<Char>>, moves: List<Move>): List<ArrayDeque<Char>> {
        moves.forEach { move ->
            repeat(move.amount) {
                stacks[move.target - 1].addFirst(stacks[move.origin - 1].first())
                stacks[move.origin - 1].removeFirst()
            }
        }
        return stacks
    }

    private fun applySteps(stacks: List<ArrayDeque<Char>>, moves: List<Move>): List<ArrayDeque<Char>> {
        moves.forEach { move ->
            if(move.amount > 1) {
                val crates = mutableListOf<Char>()
                repeat(move.amount) {
                    crates.add(stacks[move.origin - 1].first())
                    stacks[move.origin - 1].removeFirst()
                }
                crates.reversed().forEach {
                    stacks[move.target - 1].addFirst(it)
                }
            } else {
                stacks[move.target - 1].addFirst(stacks[move.origin - 1].first())
                stacks[move.origin - 1].removeFirst()
            }
        }
        return stacks
    }

    override fun test1(): Boolean {
        val testInput = getTestSolution()
        val solution = "CMZ"
        return (part1(testInput) == solution)
    }

    override fun test2(): Boolean {
        val testInput = getTestSolution()
        val solution = "MCD"
        return part2(testInput) == solution
    }

    override fun solve1() {
        println(part1(taskInput))
    }

    override fun solve2() {
        println(part2(taskInput))
    }
}

data class Move(val amount: Int, val origin: Int, val target: Int)