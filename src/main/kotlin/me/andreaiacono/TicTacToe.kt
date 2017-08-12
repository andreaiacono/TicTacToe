package me.andreaiacono.tictactoe

import me.andreaiacono.Game

fun main(args: Array<String>) {

    val sizeArgument = if (args.isEmpty()) "3" else args[0]

    if (!sizeArgument.isNumeric() || sizeArgument.toInt() < 3 || sizeArgument.toInt() > 9) {
        println("Syntax: TicTacToe [SIZE]\nwhere 3 <= SIZE <= 9")
        return
    }

    Game(sizeArgument.toInt()).start()
}
