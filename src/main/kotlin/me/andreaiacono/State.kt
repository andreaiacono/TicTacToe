package me.andreaiacono.tictactoe

/**
 * A state represent a specific moment during the game. A move applied to a state
 * generates a new state.
 */
class State(val size: Int, val grid: Array<CharArray> = Array(size, { CharArray(size, { ' ' }) })) {

    init {
        if (size > 9) {
            throw IllegalArgumentException("Max grid size is 9. You tried [$size]")
        }
    }

    fun applyMove(move: Move): State {
        if (grid[move.row][move.col] != EMPTY) {
            throw IllegalArgumentException("The actual state already contains the move [$move]")
        }

        var newGrid = clone(grid)
        newGrid[move.row][move.col] = move.player.toString()[0]
        return State(size, newGrid)
    }

    private fun clone(grid: Array<CharArray>): Array<CharArray> {
        var newGrid: Array<CharArray> = Array(size, { CharArray(size, { ' ' }) })
        for (i in 0..size - 1) {
            for (j in 0..size - 1) {
                newGrid[i][j] = grid[i][j]
            }
        }
        return newGrid
    }

    fun getPossibleMoves(player: Player): List<Move> {
        val moves: MutableList<Move> = mutableListOf()
        for (i in 0..size - 1) {
            for (j in 0..size - 1) {
                if (grid[i][j] == EMPTY) {
                    moves.add(Move(i, j, player))
                }
            }
        }
        return moves
    }

    fun getScore(player: Player): Int {
        if (getWinner() == null) {
            return 0
        }
        return if (getWinner() == player) 10 else -10
    }

    fun hasWinner(): Boolean = getWinner() == null

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
            if (grid[i][size - i] != grid[0][size - 1]) {
                found = false
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
            index, row ->
            row.forEachIndexed { index, c ->
                result += c
                if (index < size - 1) {
                    result += "|"
                }
            }

            if (index < size - 1) {
                result += "\n"
                for (i in 1..size * 2 - 1) {
                    result += if (i % 2 == 0) '+' else '-'
                }
                result += "\n"
            }
        }

        return result
    }
}