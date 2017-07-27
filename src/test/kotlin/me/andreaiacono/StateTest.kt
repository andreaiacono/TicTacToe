package me.andreaiacono

import me.andreaiacono.tictactoe.Player
import me.andreaiacono.tictactoe.State
import me.andreaiacono.tictactoe.fromCoordsToMove
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.fail


internal class StateTest {

    @Test fun `check winner`() {

        var grid = arrayOf(charArrayOf(' ', ' ', ' '), charArrayOf(' ', ' ', ' '), charArrayOf(' ', ' ', ' '))
        var state = State(3, grid)
        assertNull(state.getWinner())

        grid = arrayOf(charArrayOf('X', ' ', ' '), charArrayOf(' ', ' ', ' '), charArrayOf(' ', ' ', ' '))
        state = State(3, grid)
        assertNull(state.getWinner())

        grid = arrayOf(charArrayOf('X', 'X', ' '), charArrayOf(' ', ' ', ' '), charArrayOf(' ', ' ', ' '))
        state = State(3, grid)
        assertNull(state.getWinner())

        grid = arrayOf(charArrayOf('X', ' ', 'X'), charArrayOf(' ', 'X', ' '), charArrayOf(' ', ' ', ' '))
        state = State(3, grid)
        assertNull(state.getWinner())

        grid = arrayOf(charArrayOf('X', 'X', ' '), charArrayOf('X', ' ', ' '), charArrayOf(' ', ' ', ' '))
        state = State(3, grid)
        assertNull(state.getWinner())

        grid = arrayOf(charArrayOf('O', 'X', 'O'), charArrayOf('O', 'X', 'X'), charArrayOf('X', 'O', 'X'))
        state = State(3, grid)
        assertNull(state.getWinner())

        grid = arrayOf(charArrayOf('X', 'X', ' '), charArrayOf('X', 'X', ' '), charArrayOf(' ', ' ', ' '))
        state = State(3, grid)
        assertNull(state.getWinner())

        grid = arrayOf(charArrayOf('X', 'X', ' '), charArrayOf('X', 'X', ' '), charArrayOf(' ', 'X', ' '))
        state = State(3, grid)
        assertEquals(Player.X, state.getWinner())

        grid = arrayOf(charArrayOf('O', 'O', ' '), charArrayOf('O', 'O', ' '), charArrayOf('X', 'X', 'X'))
        state = State(3, grid)
        assertEquals(Player.X, state.getWinner())

        grid = arrayOf(charArrayOf(' ', 'O', 'O'), charArrayOf(' ', 'O', 'O'), charArrayOf('X', 'X', 'X'))
        state = State(3, grid)
        assertEquals(Player.X, state.getWinner())

        grid = arrayOf(charArrayOf('X', 'X', 'X'), charArrayOf(' ', 'O', 'O'), charArrayOf(' ', 'O', 'O'))
        state = State(3, grid)
        assertEquals(Player.X, state.getWinner())

        grid = arrayOf(charArrayOf('X', ' ', 'X'), charArrayOf(' ', ' ', ' '), charArrayOf('X', ' ', 'X'))
        state = State(3, grid)
        assertNull(state.getWinner())

        grid = arrayOf(charArrayOf('X', ' ', ' '), charArrayOf('X', ' ', ' '), charArrayOf('X', ' ', ' '))
        state = State(3, grid)
        assertEquals(Player.X, state.getWinner())

        grid = arrayOf(charArrayOf('O', ' ', ' '), charArrayOf('O', ' ', ' '), charArrayOf('O', ' ', ' '))
        state = State(3, grid)
        assertEquals(Player.O, state.getWinner())

        grid = arrayOf(charArrayOf(' ', ' ', 'X'), charArrayOf(' ', ' ', 'X'), charArrayOf(' ', ' ', 'X'))
        state = State(3, grid)
        assertEquals(Player.X, state.getWinner())

        grid = arrayOf(charArrayOf('X', ' ', ' '), charArrayOf(' ', 'X', ' '), charArrayOf(' ', ' ', 'X'))
        state = State(3, grid)
        assertEquals(Player.X, state.getWinner())

        grid = arrayOf(charArrayOf('X', ' ', 'O'), charArrayOf('O', 'X', 'O'), charArrayOf('O', ' ', 'X'))
        state = State(3, grid)
        assertEquals(Player.X, state.getWinner())

        grid = arrayOf(charArrayOf('X', 'O', 'O'), charArrayOf('X', 'O', ' '), charArrayOf('X', ' ', 'O'))
        state = State(3, grid)
        assertEquals(Player.X, state.getWinner())

    }

    @Test fun `cannot move twice on the same cell`() {

        var state = State(3)
        state = state.applyMove(fromCoordsToMove("A1", Player.X))
        try {
            state.applyMove(fromCoordsToMove("A1", Player.X))
            fail()
        } catch (e: IllegalArgumentException) {
        }

    }

}