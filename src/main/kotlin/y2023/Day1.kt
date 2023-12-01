package y2023

import Day
import getTestSolution
import readTaskInput

class Day1 : Day(1, 2023) {

    override val taskInput: List<String> = readTaskInput(day, 2023)
    override fun part1(input: List<String>): Any {
        return input.map { line -> line.mapNotNull { it.digitToIntOrNull() } }
            .map { it.first().toString() + it.last().toString() }.sumOf { it.toInt() }
    }

    override fun part2(input: List<String>): Any {
        var sum = 0
        input.forEach { line ->
            val digits = mutableListOf<String>()
            line.mapIndexed { index, c ->
                if (c.isDigit()) {
                    digits.add(c.toString())
                } else {
                    textNums.forEach {
                        if(line.substring(index).startsWith(it.key)) {
                            digits.add(it.value.toString())
                        }
                    }
                }
            }
            sum += (digits.first() + digits.last()).toInt()
        }

        return sum
    }

    override fun test1(): Boolean {
        val testInput = getTestSolution()
        val solution = 142
        println(part1(testInput))
        return (part1(testInput) == solution)
    }

    override fun test2(): Boolean {
        val testInput = getTestSolution()
        val solution = 281
        return part2(testInput) == solution
    }

    override fun solve1() {
        println(part1(taskInput))
    }

    override fun solve2() {
        println(part2(taskInput))
    }

    companion object {
        val textNums = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
        )
    }
}