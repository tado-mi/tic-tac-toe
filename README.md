# AI-Projects


1. 9Board_TicTacToe     
    This project has 2 parts. The first one is for basic TTT that never looses. It play the game using MiniMax algorithm and search for the best move everytime. You can play the game using basic input from 1-9 which tells the positions of the squares.
    The second part is more complicated. Although you it uses the same idea, we now have 9 boards and whenever you make the move you allow the opponent to only play on that board number. For example if I play on board 5 at position 2, the other person can only play at board 2 on empty position. If no empty position left on that board they can play anywhere. The game ends when one complete one board with basic TTT rules. Because the search space is too big we use H_Minimax which is heuristic function that approximate the value of each state and Alpha-Beta pruning to help reducing the amount of states we are searching. For now the game is set to search at depth 6 but you can imagine on changing the level by reducing or increasing the search space. 
    
    Compiling:
    
        You can also run the program via command line (terminal) 
            -cd to the src directory of the program
            -Compile using normal java compilation Example:
            javac TTT9Board_main.java
            -Then run the program with:
            java TTT9Board_main
            
    Playing the game:
   
        1. For basic TTT, a move is simply a number from 1-9 indicating which position the player
            is marking, according to the following chart:
                                1 2 3
                                4 5 6
                                7 8 9
        2.A move in 9-Board is two numbers from 1-9. The first number is the position of the
          board (using the same encoding as for single boards in TTT). The second number is the
          position on that board.
          However there is one crucial constraint: If a player has just played at some position
          on some board, then the next player must play on the board in the corresponding
          position in the grid. For example, if a player marks the bottom right position, position  
          9, of any board, then the next player must mark any open space on the bottom
          right board (the board at position 9). The first player to play can play anywhere. If
          the board required by the preceding rule is full, the player can play on any board.
        
