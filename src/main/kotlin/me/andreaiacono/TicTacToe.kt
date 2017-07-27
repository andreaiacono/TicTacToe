package me.andreaiacono.tictactoe

import me.andreaiacono.Game

fun main(args : Array<String>) {

    val sizeParam = if (args.isEmpty()) "3" else args[0]

    if (!sizeParam.isNumeric()) {
        println("Syntax: TicTacToe [SIZE]\n where SIZE is a number <= 9")
        return
    }

    val size = sizeParam.toInt()
    if (size > 9) {
        println("The maximum number of rows/cols is 9.")
        return
    }

    val game = Game(size)
    game.start()
}

