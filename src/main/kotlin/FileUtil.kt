fun readTaskInput(day: Int, year: Int = 0): List<String> {
    return object {}.javaClass.getResourceAsStream("Day${day}_$year.txt")?.bufferedReader()?.readLines() ?: emptyList()
}

fun readTaskFile(day: Int): String {
    return object {}.javaClass.getResourceAsStream("Day$day.txt")?.bufferedReader()?.readText() ?: ""
}

fun getTestSolution(): List<String> {
    return object {}.javaClass.getResourceAsStream("TestInput.txt")?.bufferedReader()?.readLines() ?: emptyList()
}