# AI-Projects


1. 9Board_TicTacToe     
    This project has 2 parts. The first one is for basic TTT that never looses. It play the game using MiniMax algorithm and search for the best move everytime. You can play the game using basic input from 1-9 which tells the positions of the squares.
    The second part is more complicated. Although you it uses the same idea, we now have 9 boards and whenever you make the move you allow the opponent to only play on that board number. For example if I play on board 5 at position 2, the other person can only play at board 2 on empty position. If no empty position left on that board they can play anywhere. The game ends when one complete one board with basic TTT rules. Because the search space is too big we use H_Minimax which is heuristic function that approximate the value of each state and Alpha-Beta pruning to help reducing the amount of states we are searching. For now the game is set to search at depth 6 but you can imagine on changing the level by reducing or increasing the search space. 
