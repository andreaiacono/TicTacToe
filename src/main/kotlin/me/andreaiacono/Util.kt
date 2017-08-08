package me.andreaiacono.tictactoe

val EMPTY = ' '
val WIN = 100
val LOSE = -100
val TIE = 0

fun String.isNumeric(): Boolean {
    return this.matches("\\d+".toRegex())
}

fun fromCoordsToMove(move: String, size: Int): Move {
    val trimmedMove = move.trim().toUpperCase()
    if (trimmedMove.length != 2 || !trimmedMove[1].isDigit() || !trimmedMove[0].isLetter()) {
        throw IllegalArgumentException("Illegal move [$trimmedMove]")
    }
    val row = trimmedMove[1].toInt() - 49
    val col = trimmedMove[0].toInt() - 65
    if (row >= size || col >= size) {
        throw IllegalArgumentException("The move [$row, $col] is not inside the grid.")
    }
    return Move(row, col)
}
