# AI enhacned 9 Board Tic - Tac - Toe

Code written by [Andrew Nyaisonga](https://github.com/AndrewNyaisonga), forked and modified by [Lusine Keshishyan](https://github.com/tado-mi) in order to learn the [Minimax algorithm](https://en.wikipedia.org/wiki/Minimax) for simulating a simple AI; Heuristic algorithms and [Alpha-beta pruning](https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning); as well as coding practices. Please see the commit history for details.

Following is a solution of the assignment for the course [CSC 242](https://www.cs.rochester.edu/u/ferguson/csc/242/Spring2019/syllabus.html) at the University of Rochester. It is made public to demonstrate a code sample by the authors, and for learning. We trust it will not be used to violate academic honesty policies.

note:

* indentation is off on `github`. That is not the case on my pc. :confused:
* the code, as is, was mostly written in 2018 December. Some minor changes have been made since.

# Description

The project has 2 parts:

**Part 1:** the first one is for basic `tic-tac-toe` that never looses. It plays the game using the Minimax algorithm and searches for the best move every time. The user can play the game using basic input of numbers from 1-9, indicating the positions of the squares.

**Part 2:** the second part is more complicated. Although it uses the same idea, we now have 9 boards and whenever you make the move you allow the opponent to only play on that board number: I play on board `j` at position `i`, the opponent can only play at board  `i`, if there are empty positions on it, else they can play anywhere. The game ends when one player wins on one board. Because the search space is too big, we use `H_Minimax`. It is a heuristic function that approximates the value of each state; and `Alpha-beta pruning` to help reducing the amount of states we are searching. For now the game is set to search at `depth = 6`, but you can imagine on changing the level by reducing or increasing the search space.

For playing, a move in 9-Board is two numbers from 1-9. The first number is the position of the board (using the same encoding as for single boards in TTT). The second number is the position on that board.

# Running

There is an included `makefile` for your (and our) convenience.
