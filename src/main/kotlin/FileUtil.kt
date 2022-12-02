fun readTaskInput(day: Int): List<String> {
    return object {}.javaClass.getResourceAsStream("Day$day.txt")?.bufferedReader()?.readLines() ?: emptyList()
}

fun readTaskFile(day: Int): String {
    return object {}.javaClass.getResourceAsStream("Day$day.txt")?.bufferedReader()?.readText() ?: ""
}

fun getTestSolution(): List<String> {
    return object {}.javaClass.getResourceAsStream("TestInput.txt")?.bufferedReader()?.readLines() ?: emptyList()
}