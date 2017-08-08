package me.andreaiacono.tictactoe

data class Move(val row: Int, val col: Int, var score: Int = LOSE)