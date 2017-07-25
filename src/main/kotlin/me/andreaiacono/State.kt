package me.andreaiacono.tictactoe

/**
 * A state represent the tic-tac-toe grid in a certain moment of the game.
 */
class State(val size: Int, val grid: Array<CharArray> = Array(size, { CharArray(size, { ' ' }) })) {

    init {
        if (size > 9) {
            throw IllegalArgumentException("Max grid size is 9. You tried [$size]")
        }
    }

    fun setMove(move: Move): State {
        var newGrid = this.grid.clone()
        // TODO horrible!
        newGrid[move.row][move.col] = move.player.toString().toCharArray()[0]
        return State(size, newGrid)
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

    // FIXME
    fun getWinner(): Player? {

        for (i in 0..size - 1) {
            if (grid[i].all { v -> v == grid[i][0] }) {
                return Player.valueOf(grid[i][0].toString())
            }
            var found = true
            for (j in 0..size - 1) {
                if (grid[j][i] != grid[0][i]) {
                    found = false
                }
            }
            if (found) {
                return Player.valueOf(grid[0][i].toString())
            }
        }

        var found = true
        for (i in 0..size - 1) {
            for (j in 0..size - 1) {
                if (grid[0][0] != grid[i][j]) {
                    found = false
                }
            }
        }
        if (found) {
            return Player.valueOf(grid[0][0].toString())
        }

        for (i in size - 1 downTo 0) {
            for (j in 0..size - 1) {
                if (grid[size-1][0] != grid[i][j]) {
                    found = false
                }
            }
        }
        if (found) {
            return Player.valueOf(grid[size-1][0].toString())
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