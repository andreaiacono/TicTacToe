package me.andreaiacono.tictactoe

val EMPTY = ' '

fun String.isNumeric(): Boolean {
    return this.matches("\\d+".toRegex())
}

fun minimax(actual: State, depth: Int, player: Player, firstMovePlayer: Player): Move {

    val moves = actual.getPossibleMoves(firstMovePlayer)

    // if it's the last move
    if (depth == actual.size * actual.size - 1) {
        val lastMove = moves.first()
        lastMove.score = actual.applyMove(lastMove).getScore(player)
        return lastMove
    }

    val opponent = player.next()
    var bestMove: Move = moves.first()
    for (move in moves) {

        val nextState = actual.applyMove(move)
        if (nextState.hasWinner()) {
            move.score = if (nextState.getWinner() == player) -10 else 10
            return move
        }

        val nextMove = minimax(nextState, depth + 1, opponent, firstMovePlayer)
        if (nextMove.score > bestMove.score) {
            bestMove = nextMove
        }
    }

    return bestMove
}

fun fromCoordsToMove(move: String, player: Player, size: Int): Move {
    val trimmedMove = move.trim().toUpperCase()
    if (trimmedMove.length != 2 || !trimmedMove[1].isDigit() || !trimmedMove[0].isLetter()) {
        throw IllegalArgumentException("Illegal move [$trimmedMove]")
    }
    val row = trimmedMove[1].toInt() - 49
    val col = trimmedMove[0].toInt() - 65
    if (row >= size || col >= size) {
        throw IllegalArgumentException("The move [$row, $col] is not inside the grid.")
    }
    return Move(row, col, player)
}