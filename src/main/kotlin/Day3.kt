class Day3 : Day(3) {

    override val taskInput: List<String> = readTaskInput(day)
    var itemScore = 0
    var commonChar: Char = '\u0000'

    override fun part1(input: List<String>): Any {
       input.forEach { items ->
           val splitA = items.chunked(items.length / 2)
           items.forEach {
               if (splitA.all { compartment -> compartment.contains(it) }) {
                   commonChar = it
               }
           }
           itemScore += prioScore(commonChar)
       }
       return itemScore
    }

    override fun part2(input: List<String>): Any {
        val groups = input.chunked(3)
        groups.forEach { group ->
            group.first().forEach { char ->
                if (group.all { items -> items.contains(char) }) {
                    commonChar = char
                }
            }
            itemScore += prioScore(commonChar)
        }
        return itemScore
    }

    private fun prioScore(char: Char) : Int {
        return if (char.isLowerCase()) {
            char  -  'a' + 1
        } else {
            (char.lowercaseChar()  -  'a' + 1) + 26
        }
    }


    override fun test1(): Boolean {
        val testInput = getTestSolution()
        val solution = 157
        return (part1(testInput) == solution)
    }

    override fun test2(): Boolean {
        val testInput = getTestSolution()
        val solution = 70
        return part2(testInput) == solution
    }

    override fun solve1() {
        println(part1(taskInput))
    }

    override fun solve2() {
        println(part2(taskInput))
    }
}