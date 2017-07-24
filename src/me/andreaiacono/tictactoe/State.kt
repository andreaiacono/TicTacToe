package me.andreaiacono.tictactoe

class State(val size: Int, val grid : Array<CharArray> = Array(size, { CharArray(size, { ' ' }) })) {

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
        val moves : MutableList<Move> = mutableListOf()
        for (i in 0..size-1) {
            for (j in 0..size-1) {
                if (grid[i][j] == EMPTY) {
                    moves.add(Move(i, j, player))
                }
            }
        }
        return moves
    }

    fun getScore(player: Player) : Int {
        if (getWinner() == null) {
            return 0
        }
        return if (getWinner() == player) 10 else -10
    }

    fun hasWinner() : Boolean = getWinner() == null

    // FIXME
    fun getWinner(): Player? {

        // TODO to be implemented
        return Player.X

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