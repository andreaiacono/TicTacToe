package me.andreaiacono.tictactoe

enum class Player {
    X { override fun next(): Player { return O }},
    O { override fun next(): Player { return X }};
    abstract fun next(): Player
}