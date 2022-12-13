class Day7 : Day(7) {

    override val taskInput: List<String> = readTaskInput(day)
    private lateinit var currentDir: Directory
    private lateinit var rowMap: Map<Int, String>
    private var scanForContent = true
    private var root: Directory = Directory( null, "/")

    override fun part1(input: List<String>): Any {
        buildFileSystem(input)
        return root.findAll().filter { it.size <= 100000 }.sumOf { it.size }
    }

    override fun part2(input: List<String>): Any {
        buildFileSystem(input)
        return root.findAll().filter { ((70000000 - root.size) + it.size) >= 30000000 }.minByOrNull { it.size }!!.size
    }

    override fun test1(): Boolean {
        val testInput = getTestSolution()
        val solution = 95437L
        return (part1(testInput) == solution)
    }

    override fun test2(): Boolean {
        val testInput = getTestSolution()
        val solution = 24933642L
        return part2(testInput) == solution
    }

    override fun solve1() {
        println(part1(taskInput))
    }

    override fun solve2() {
        println(part2(taskInput))
    }

    private fun processDirSwitch(dir: String) {
        when (dir) {
            ".." -> currentDir = currentDir.parentDirectory ?: currentDir
            else -> {
                val d = Directory(currentDir, dir)
                currentDir.dirs += d
                currentDir = d
            }
        }
    }

    private fun buildFileSystem(input: List<String>) {
        currentDir = root
        rowMap = input.drop(2).mapIndexed {index, s -> index to s }.toMap()
        rowMap.forEach {
            if (it.value.contains("$")) {
                if (scanForContent) {
                    scanForContent = false
                }
                val command = it.value.substring(2 until it.value.length)
                if (command == "ls") {
                    scanForContent = true
                    return@forEach
                } else {
                    val cd = command.split(" ")
                    processDirSwitch(cd[1])
                }
            } else if(scanForContent) {
                if (!it.value.startsWith("dir")) {
                    currentDir.files.add(File(it.value.split(" ")[0].toLong()))
                }
            }
        }
    }
}

class Directory(val parentDirectory: Directory?, val name: String) {
    val files = mutableListOf<File>()
    val dirs = mutableListOf<Directory>()

    val size: Long get() = files.sumOf { it.size } + dirs.sumOf { it.size }

    fun findAll(): List<Directory> = dirs.plus(dirs.flatMap { it.findAll() })
}
data class File(val size: Long)