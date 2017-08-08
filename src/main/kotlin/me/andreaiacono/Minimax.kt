package me.andreaiacono

import me.andreaiacono.tictactoe.*

data class ScoredMove(val score: Int, val move: Move)

// for every possible move of this player, computes the best score and selects that move
fun minimax(state: State, player: Player): Move {

    return state.getPossibleMoves()
            .map {
                ScoredMove(
                    minimax(state.applyMove(it, player), player.next(), false),
                    it)
            }
            .maxBy { it.score }
            ?.move!! // we can safely assume that a move will always be available
}


/**
 * Computes the best move for the AI given the actual state.

 * @param actualState the state of the game at this round
 * @param actualPlayer the player I'm moving for this round
 * @param isAiPlaying if this is round is played by the AI or by the user
 */
fun minimax(actualState: State, actualPlayer: Player, isAiPlaying: Boolean): Int {

    if (actualState.isGameOver()) {
        // even if the game is going to be tied or lost, we want the AI to last as much as possible
        // thus adding the number of played moves
        return actualState.getScore(actualPlayer) + actualState.getPlayedMovesNumber()
    }
    var bestScore: Int = if (isAiPlaying) Int.MIN_VALUE else Int.MAX_VALUE
    val moves = actualState.getPossibleMoves()
    for (move in moves) {
        val moveScore = minimax(actualState.applyMove(move, actualPlayer), actualPlayer.next(), !(isAiPlaying))
        bestScore = if (isAiPlaying)
            Math.max(moveScore, bestScore)
        else
            Math.min(moveScore, bestScore)
    }
    return bestScore
}