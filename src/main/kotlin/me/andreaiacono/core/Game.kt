package me.andreaiacono

import me.andreaiacono.tictactoe.*
import java.util.*

/**
 * This class represent a game. The first player is X, and is randomly
 * chosen between the computer and the human opponent.
 */
class Game(val size: Int) {

    var state = State(size)
    val maxMinimaxDepth = getMaxDepth(size)

    fun start() {
        // the user (and not the AI) is playing with the X?
        val isUserPlayingX = Random().nextBoolean()

        println("Welcome to TicTacToe\nThe X will start the game, now I'll randomly choose who of us will be the X player.")
        Thread.sleep(1000)
        if (isUserPlayingX) println("You'll be the X player, so it's your turn.") else println("I'll be the X player, so it's my turn.")

        // the main game loop
        var actualPlayer = Player.X
        while (!state.isGameOver()) {
            // TODO fix inserting twice the same move
            state = state.applyMove(getMove(isUserPlayingX, actualPlayer), actualPlayer)
            println(state)
            actualPlayer = actualPlayer.next()
        }

        // the game is finished
        val winner = state.getWinner()
        when {
            winner == null -> println("This match was a tie.")
            (winner == Player.X && isUserPlayingX) || (winner == Player.O && !isUserPlayingX) -> println("Congratulations, you won!")
            else -> println("Wooohh, I won!")
        }
    }

    fun getMove(isUserX: Boolean, player: Player): Move =
            if ((isUserX && player == Player.X) || (!isUserX && player == Player.O))
                getUserMove()
            else
                getComputerMove(player)

    fun getComputerMove(player: Player): Move {
        println("I'm now thinking...")
        Thread.sleep(500)
        val move: Move = minimax(state, player, maxMinimaxDepth)
        return move
    }

    fun getUserMove(): Move {
        while (true) {
            try {
                print("Insert your move > ")
                val userMove = readLine().orEmpty()
                return fromCoordsToMove(userMove, size)
            } catch (ex: Exception) {
                println(ex.message + " Valid moves are A1, B3, ...")
            }
        }
    }
}