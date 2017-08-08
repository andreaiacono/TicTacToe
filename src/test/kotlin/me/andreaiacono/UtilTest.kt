package me.andreaiacono

import me.andreaiacono.tictactoe.fromCoordsToMove
import org.junit.Test
import kotlin.test.*


internal class UtilTest {

    @Test fun `coordinates transform`() {

        var input = "A1"
        var move = fromCoordsToMove(input, 3)
        assertEquals(0, move.row)
        assertEquals(0, move.col)

        input = "A3"
        move = fromCoordsToMove(input, 3)
        assertEquals(2, move.row)
        assertEquals(0, move.col)

        input = "F6"
        move = fromCoordsToMove(input, 6)
        assertEquals(5, move.row)
        assertEquals(5, move.col)

        input = "C8"
        move = fromCoordsToMove(input, 8)
        assertEquals(7, move.row)
        assertEquals(2, move.col)

        try {
            input = "foo"
            fromCoordsToMove(input, 3)
            fail()
        } catch (e: IllegalArgumentException) {
        }

        try {
            input = "A4"
            fromCoordsToMove(input, 3)
            fail()
        } catch (e: IllegalArgumentException) {
        }

        try {
            input = "4A"
            fromCoordsToMove(input, 3)
            fail()
        } catch (e: IllegalArgumentException) {
        }
    }
}
