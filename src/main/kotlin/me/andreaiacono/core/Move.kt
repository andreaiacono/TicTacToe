package me.andreaiacono.tictactoe

data class Move(val row: Int, val col: Int, val player: Player, var score: Int = 0)