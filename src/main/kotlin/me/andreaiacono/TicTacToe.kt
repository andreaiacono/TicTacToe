package me.andreaiacono.tictactoe

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

    var state = State(4)

    state = state.applyMove(fromCoordsToMove("A1", Player.X))
    state = state.applyMove(fromCoordsToMove("B1", Player.X))
//    grid.applyMove(fromCoordsToMove("C1", Player.X))
    state = state.applyMove(fromCoordsToMove("B2", Player.X))
    state = state.applyMove(fromCoordsToMove("C3", Player.X))
    state = state.applyMove(fromCoordsToMove("B3", Player.O))

//    minimax(state, 5, Player.X)

    println(state.toString())
    print(state.getWinner())
}