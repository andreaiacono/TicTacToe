package me.andreaiacono.tictactoe

/**
 * A state represent the board in a specific moment of the game. A move applied to a state
 * generates a new state.
 */
class State(val size: Int, val grid: Array<CharArray> = Array(size, { CharArray(size, { ' ' }) })) {

    init {
        if (size > 9) {
            throw IllegalArgumentException("Max grid size is 9. You tried [$size]")
        }
    }

    fun applyMove(move: Move, player: Player): State {
        if (grid[move.row][move.col] != EMPTY) {
            throw IllegalArgumentException("The actual state already contains the move [$move]")
        }
        val newGrid = clone(grid)
        newGrid[move.row][move.col] = player.toString()[0]
        return State(size, newGrid)
    }

    private fun clone(grid: Array<CharArray>): Array<CharArray> {
        val newGrid: Array<CharArray> = Array(size, { CharArray(size, { ' ' }) })
        grid.forEachIndexed {
            rowIdx, row ->
            row.forEachIndexed { colIdx, _ ->
                newGrid[rowIdx][colIdx] = grid[rowIdx][colIdx]
            }
        }
        return newGrid
    }

    fun getPossibleMoves(): List<Move> {
        val moves: MutableList<Move> = mutableListOf()
        grid.forEachIndexed {
            rowIdx, row ->
            row.forEachIndexed { colIdx, c ->
                if (c == EMPTY) {
                    moves.add(Move(rowIdx, colIdx))
                }
            }
        }
        return moves
    }

    fun getScore(player: Player): Int {
        when {
            getWinner() == null -> return TIE
            getWinner() == player -> return WIN
            else -> return LOSE
        }
    }

    fun getRemainingMovesNumber(): Int = grid.flatMap { it.asIterable() }.filter { it == EMPTY }.size
    fun getPlayedMovesNumber(): Int = size - getRemainingMovesNumber()
    fun hasWinner(): Boolean = getWinner() != null
    fun isFinished() = getRemainingMovesNumber() == 0
    fun isGameOver() = isFinished() || hasWinner()

    fun getWinner(): Player? {

        // rows check
        for (i in 0..size - 1) {
            if (grid[i][0] != EMPTY && grid[i].all { it -> it == grid[i][0] }) {
                return Player.valueOf(grid[i][0].toString())
            }
        }

        // columns check
        for (i in 0..size - 1) {
            var found = true;
            for (j in 1..size - 1) {
                if (grid[0][i] == EMPTY || grid[j][i] != grid[0][i]) {
                    found = false
                    break
                }
            }
            if (found) {
                return Player.valueOf(grid[0][i].toString())
            }
        }

        // top-left/bottom-right diagonal
        var found = true
        if (grid[0][0] == EMPTY) {
            return null
        }
        for (i in 1..size - 1) {
            if (grid[i][i] != grid[0][0]) {
                found = false
                break
            }
        }
        if (found) {
            return Player.valueOf(grid[0][0].toString())
        }

        // top-right/bottom-left diagonal
        found = true
        if (grid[0][size - 1] == EMPTY) {
            return null
        }
        for (i in 1..size - 1) {
            if (grid[i][size - 1 - i] != grid[0][size - 1]) {
                found = false
                break
            }
        }
        if (found) {
            return Player.valueOf(grid[0][size - 1].toString())
        }

        return null
    }

    override fun toString(): String {

        var result = ""

        grid.forEachIndexed {
            rowIdx, row ->
            row.forEachIndexed { colIdx, c ->
                result += c
                if (colIdx < size - 1) {
                    result += "|"
                }
            }

            if (rowIdx < size - 1) {
                result += "\n"
                for (i in 1..size * 2 - 1) {
                    result += if (i % 2 == 0) '+' else '-'
                }
                result += "\n"
            }
        }

        return result + "\n"
    }
}