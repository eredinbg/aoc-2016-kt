import kotlin.math.max
import kotlin.math.min

fun main() {
  fun solve(keypad: List<List<String>>, input: List<String>): String {
    val instructions = input.map { it.toList() }
    var y = 0
    var x = 0
    for ((rowIdx, row) in keypad.withIndex()) {
      for ((colIdx, cell) in row.withIndex()) {
        if (cell === "5") {
          y = rowIdx
          x = colIdx
        }
      }
    }
    var position = y to x
    var code = ""
    for (directions in instructions) {
      for (direction in directions) {
        val newPosition =
            when (direction) {
              'U' -> max(position.first - 1, 0) to position.second
              'R' ->
                  position.first to
                      min(position.second + 1, keypad[position.first].size - 1)
              'D' -> min(position.first + 1, keypad.size - 1) to position.second
              'L' -> position.first to max(position.second - 1, 0)
              else -> position
            }
        if (keypad[newPosition.first][newPosition.second] != "")
            position = newPosition
      }
      code += keypad[position.first][position.second]
    }

    return code
  }

  val input = readInput("Day02")
  ("Part 1: " +
          solve(
              listOf(
                  listOf("1", "2", "3"),
                  listOf("4", "5", "6"),
                  listOf("7", "8", "9"),
              ),
              input))
      .println()
  ("Part 2: " +
          solve(
              listOf(
                  listOf("", "", "1", "", ""),
                  listOf("", "2", "3", "4", ""),
                  listOf("5", "6", "7", "8", "9"),
                  listOf("", "A", "B", "C", ""),
                  listOf("", "", "D", "", ""),
              ),
              input))
      .println()
}
