abstract class Day(val day: Int, val year: Int = 0) {

    abstract val taskInput : List<Any>

    abstract fun part1(input:List<String>) : Any
    abstract fun part2(input:List<String>) : Any
    abstract fun test1() : Boolean
    abstract fun test2() : Boolean
    abstract fun solve1()
    abstract fun solve2()
}