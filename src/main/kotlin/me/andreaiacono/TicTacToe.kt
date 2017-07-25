package me.andreaiacono.tictactoe

fun main(args : Array<String>) {

    if (!args[0].isNumeric()) {
        println("The maximum number of rows/cols is 9.")
        return
    }

    val grid = State(args[0].toInt())
    grid.setMove(fromCoordsToMove("A1", Player.X))
    grid.setMove(fromCoordsToMove("B1", Player.X))
//    grid.setMove(fromCoordsToMove("C1", Player.X))
    grid.setMove(fromCoordsToMove("B2", Player.X))
    grid.setMove(fromCoordsToMove("C3", Player.X))
    grid.setMove(fromCoordsToMove("B3", Player.O))

    println(grid.toString())
    print(grid.getWinner())
}