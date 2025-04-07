fun main() {
  fun parseRooms(input: List<String>): List<Triple<String, Int, String>> {
    return input.mapNotNull { line ->
      Regex("(\\D+)-(\\d+)\\[([a-z]+)]").find(line)?.let {
        val (name, id, checksum) = it.destructured
        Triple(name, id.toInt(), checksum)
      }
    }
  }

  fun calcChecksum(name: String): String {
    return name
        .replace("-", "")
        .groupingBy { it }
        .eachCount()
        .entries
        .sortedWith(
            compareByDescending<Map.Entry<Char, Int>> { it.value }
                .thenBy { it.key })
        .take(5)
        .map { it.key }
        .joinToString("")
  }

  fun decryptName(name: String, shift: Int): String {
    val alphabetLength = 26
    return name.split("-").joinToString("-") {
      it.map {
            ('a'.code + (it.code - 'a'.code + shift).rem(alphabetLength))
                .toChar()
          }
          .joinToString("")
    }
  }

  fun part1(input: List<String>): Int {
    return parseRooms(input)
        .filter { (name, _, checksum) -> calcChecksum(name) == checksum }
        .sumOf { it.second }
  }

  fun part2(input: List<String>): Int? {
    return parseRooms(input)
        .filter { (name, _, checksum) -> calcChecksum(name) == checksum }
        .find { (name, id) -> "north" in decryptName(name, id) }
        ?.second
  }

  val input = readInput("Day04")
  ("Part 1: " + part1(input)).println()
  ("Part 2: " + part2(input)).println()
}
