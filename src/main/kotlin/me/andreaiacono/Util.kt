package me.andreaiacono.tictactoe

val EMPTY = ' '
val WIN = 100
val LOSE = -100
val TIE = 0

fun String.isNumeric() = this.matches("\\d+".toRegex())

/**
 * transforms the user input into a move, checking if is legal
 */
fun fromCoordsToMove(move: String, size: Int): Move {
    val trimmedMove = move.trim().toUpperCase()
    if (trimmedMove.length != 2 || !trimmedMove[1].isDigit() || !trimmedMove[0].isLetter()) {
        throw IllegalArgumentException("Illegal move [$trimmedMove]. ")
    }
    val row = trimmedMove[1].toInt() - 49
    val col = trimmedMove[0].toInt() - 65
    if (row >= size || col >= size) {
        throw IllegalArgumentException("The move [$row, $col] is not inside the board. ")
    }
    return Move(row, col)
}

/**
 * this function uses a simple heuristic given by the execution time on my
 * box to be below 10 seconds
 */
fun getMaxDepth(size: Int) = when (size) {
    3 -> 9
    4 -> 5
    5 -> 4
    6 -> 3
    7 -> 3
    8 -> 2
    else -> 2
}