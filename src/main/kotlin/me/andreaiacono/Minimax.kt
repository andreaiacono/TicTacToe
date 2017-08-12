package me.andreaiacono

import me.andreaiacono.tictactoe.*

data class ScoredMove(val score: Int, val move: Move)


/**
 * Returns the best move the specified player at this state
 * @param state the state to find the best move for
 * @param player the player to find the best move for
 */
fun minimax(state: State, player: Player, maxDepth: Int): Move {
    return state.getPossibleMoves()
            .map {
                ScoredMove(
                    // we pass to the minimax function:
                    // - a new state obtained by applying this move
                    // - the opponent of the calling player
                    // - to minimize the score
                    minimax(state.applyMove(it, player), player.next(), maxDepth, false),
                    it)
            }
            .maxBy { it.score }
            ?.move!! // we can safely assume that a move will always be available
}


/**
 * Computes the score for this state using the Minimax algorithm.
 * @param actualState the state of the game at this round
 * @param actualPlayer the player for this round
 * @param depth the depth of recursive calls of minimax
 * @param isMaximizing if this is round is to be maximized or minimized
 */
fun minimax(actualState: State, actualPlayer: Player, depth: Int, isMaximizing: Boolean): Int {

    if (actualState.isGameOver() || depth == 0) {
        return actualState.getScore(actualPlayer, isMaximizing)
    }
    var bestScore: Int = if (isMaximizing) Int.MIN_VALUE else Int.MAX_VALUE
    val moves = actualState.getPossibleMoves()
    for (move in moves) {
        val moveScore = minimax(actualState.applyMove(move, actualPlayer), actualPlayer.next(), depth-1, !(isMaximizing))
        bestScore = if (isMaximizing)
                        Math.max(moveScore, bestScore)
                    else
                        Math.min(moveScore, bestScore)
    }
    return bestScore
}
