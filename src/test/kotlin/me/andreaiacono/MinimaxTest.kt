package me.andreaiacono

import me.andreaiacono.tictactoe.Player
import me.andreaiacono.tictactoe.State
import me.andreaiacono.tictactoe.fromCoordsToMove
import org.junit.Test
import kotlin.test.*


internal class MinimaxTest {

    @Test fun `minimax check`() {

        var grid = arrayOf(charArrayOf('X', 'O', 'O'),
                                           charArrayOf('O', 'X', 'X'),
                                           charArrayOf(' ', 'X', ' '))
        var state = State(3, grid)
        var move = minimax(state, Player.O)
        assertEquals(2, move.row)
        assertEquals(2, move.col)

        println("\n\n\n")
        grid = arrayOf(charArrayOf('X', ' ', 'O'),
                       charArrayOf(' ', 'X', ' '),
                       charArrayOf(' ', ' ', ' '))
        state = State(3, grid)
        move = minimax(state, Player.O)
        assertEquals(2, move.row)
        assertEquals(2, move.col)

        println("test\n\n\n")
        grid = arrayOf(charArrayOf(' ', 'X', 'X'),
                       charArrayOf(' ', 'O', ' '),
                       charArrayOf(' ', ' ', ' '))
        state = State(3, grid)
        move = minimax(state, Player.O)
        assertEquals(0, move.row)
        assertEquals(0, move.col)

        grid = arrayOf(charArrayOf('O', 'X', 'X'),
                       charArrayOf(' ', 'O', 'O'),
                       charArrayOf(' ', ' ', 'X'))
        state = State(3, grid)
        move = minimax(state, Player.X)
        assertEquals(1, move.row)
        assertEquals(0, move.col)

        grid = arrayOf(charArrayOf('O', ' ', 'X'),
                       charArrayOf(' ', 'X', ' '),
                       charArrayOf('O', ' ', 'X'))
        state = State(3, grid)
        move = minimax(state, Player.O)
        assertEquals(1, move.row)
        assertEquals(0, move.col)

//        grid = arrayOf(charArrayOf('X', ' ', 'X'),
//                       charArrayOf('O', 'O', 'X'),
//                       charArrayOf('O', ' ', ' '))
//        state = State(3, grid)
//        move = minimax(state, Player.X)
//        assertTrue((move.row == 0 && move.col == 1) || (move.row == 2 && move.col == 2))
    }
}
