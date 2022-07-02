package org.flightofstairs.ctci.arraysAndStrings

// Problem 1.6
fun stringCompression(uncompressed: String): String {
    data class CharCount(val char: Char, var count: Int)

    val counts = uncompressed.fold(listOf<CharCount>()) { acc, character ->
        if (acc.isEmpty() || acc.last().char != character) {
            acc + CharCount(character, 1)
        } else {
            acc.last().count += 1
            acc
        }
    }

    val compressed = counts.joinToString("") { "${it.char}${it.count}" }

    return if (compressed.length < uncompressed.length) compressed else uncompressed
}
