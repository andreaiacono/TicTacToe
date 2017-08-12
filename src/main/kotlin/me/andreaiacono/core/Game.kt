package me.andreaiacono

import me.andreaiacono.tictactoe.*
import java.util.*

/**
 * This class represent a game. The first player is X, and is randomly
 * chosen between the computer and the human opponent.
 */
class Game(val size: Int) {

    val HUMAN_WIN_MESSAGE = "Congratulations, you won!"
    val AI_WIN_MESSAGE = "Wooohh, I won!"
    val TIE_MESSAGE = "This match was a tie."

    var state = State(size)
    val maxMinimaxDepth = getMaxDepth(size)

    fun start() {
        // is the user (and not the AI) is playing with the X?
        val isUserPlayingX = Random().nextBoolean()

        println("Welcome to TicTacToe\nThe X will start the game, now I'll randomly choose who of us will be the X player.")
        Thread.sleep(1000)
        if (isUserPlayingX)
            println("You'll be the X player, so it's your turn.")
        else
            println("I'll be the X player, so it's my turn.")

        // the starting player is always X
        var actualPlayer = Player.X

        // the main game loop
        while (!state.isGameOver()) {
            try {
                state = state.applyMove(getMove(isUserPlayingX, actualPlayer), actualPlayer)
                println(state)
                actualPlayer = actualPlayer.next()
            }
            catch (ex: Exception) {
                println(ex.message)
            }
        }

        // the game is finished
        val winner = state.getWinner()
        when {
            winner == null -> println(TIE_MESSAGE)
            (winner == Player.X && isUserPlayingX) || (winner == Player.O && !isUserPlayingX) -> println(HUMAN_WIN_MESSAGE)
            else -> println(AI_WIN_MESSAGE)
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
                return fromCoordsToMove(readLine().orEmpty(), size)
            } catch (ex: Exception) {
                println(ex.message + "Valid moves have a letter and a number (like A1 or B3) where the letter is the column and the number is the row.")
            }
        }
    }
}