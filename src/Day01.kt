import kotlin.math.abs

enum class Direction(val vector: Pair<Int, Int>) {
  NORTH(0 to 1),
  EAST(1 to 0),
  SOUTH(0 to -1),
  WEST(-1 to 0);

  fun turnLeft(): Direction =
      when (this) {
        NORTH -> WEST
        WEST -> SOUTH
        SOUTH -> EAST
        EAST -> NORTH
      }

  fun turnRight(): Direction =
      when (this) {
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
      }
}

fun main() {
  fun parseInstructions(input: List<String>): List<List<String>> {
    return input.first().split(", ").map { it.split(Regex("(?<=\\D)(?=\\d)")) }
  }

  fun calcDistance(position: Pair<Int, Int>): Int {
    return abs(position.first) + abs(position.second)
  }

  fun part1(input: List<String>): Int {
    val instructions = parseInstructions(input)
    var position = 0 to 0
    var direction = Direction.NORTH

    for ((turn, distance) in instructions) {
      direction =
          when (turn) {
            "L" -> direction.turnLeft()
            "R" -> direction.turnRight()
            else -> direction
          }
      position =
          position.first + distance.toInt() * direction.vector.first to
              position.second + distance.toInt() * direction.vector.second
    }

    return calcDistance(position)
  }

  fun part2(input: List<String>): Int {
    val instructions = parseInstructions(input)
    var position = 0 to 0
    var direction = Direction.NORTH
    val visited = mutableSetOf<Pair<Int, Int>>(position)

    for ((turn, distance) in instructions) {
      direction =
          when (turn) {
            "L" -> direction.turnLeft()
            "R" -> direction.turnRight()
            else -> direction
          }
      // TODO: come up with a better solution
      repeat(distance.toInt()) {
        position =
            position.first + direction.vector.first to
                position.second + direction.vector.second
        if (position in visited) {
          return calcDistance(position)
        }
        visited.add(position)
      }
    }

    return calcDistance(position)
  }

  // Read the input from the `src/Day01.txt` file.
  val input = readInput("Day01")
  ("Part 1: " + part1(input)).println()
  ("Part 2: " + part2(input)).println()
}
