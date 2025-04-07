fun main() {
  fun part1(input: List<String>): Int {
    val triangles =
        input.map { it.trim().split(Regex("\\s+")).map { it.toInt() } }

    return triangles
        .filter { (a, b, c) -> (a + b > c && a + c > b && b + c > a) }
        .size
  }

  fun part2(input: List<String>): Int {
    val triangles =
        input
            .map { it.trim().split(Regex("\\s+")).map { it.toInt() } }
            .chunked(3)
            .map { chunk ->
              val (row1, row2, row3) = chunk
              val (a1, a2, a3) = row1
              val (b1, b2, b3) = row2
              val (c1, c2, c3) = row3

              listOf(listOf(a1, b1, c1), listOf(a2, b2, c2), listOf(a3, b3, c3))
            }
            .flatten()

    return triangles
        .filter { (a, b, c) -> (a + b > c && a + c > b && b + c > a) }
        .size
  }

  val input = readInput("Day03")
  ("Part 1: " + part1(input)).println()
  ("Part 2: " + part2(input)).println()
}
