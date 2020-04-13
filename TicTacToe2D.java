import java.util.*;

public class TicTacToe2D {

	Scanner input = new Scanner(System.in);

	ArrayList<Integer> 	emptCells; // empty cells on the board
	ArrayList<State> 	Score;

	int[] board;

	public TicTacToe2D() {

		board = new int[9];

	}

	// util methods
	public ArrayList<Integer> getEmptCells() {

		emptCells = new ArrayList<Integer>();

		for (int i = 0; i < 9; i = i + 1) {

			if (board[i] == 0) emptCells.add(i);

		}

		return emptCells;

	}

	public int min(ArrayList<Integer> arr) {

		int min = 1;
        
        for (int i = 0; i < arr.size(); i = i + 1) {

            if (arr.get(i) < min) min = arr.get(i);

        }
        
        return min;

	}

	public int max(ArrayList<Integer> arr) {

		int max = 1;
        
        for (int i = 0; i < arr.size(); i = i + 1) {

            if (arr.get(i) > max) max = arr.get(i);

        }
        
        return max;
		
	}

	public void changeState(int cell, int turn) {

		board[cell] = turn;

	}

	// get minimax
	public void get(int depth, int turn) {

		Score = new ArrayList<State>();
		minimax(depth, turn);

	}

	// minimax algorithm
	public int minimax(int depth, int turn) {

		ArrayList<Integer> emptCells	= getEmptCells();
		ArrayList<Integer> currScores 		= new ArrayList<Integer>();

		if (loose())	return  1;
		if (win())		return -1;
		// no more available cells
		if (emptCells.isEmpty()) return 0;

		// recursive call for each candidate cell
		for (int i = 0; i < emptCells.size(); i = i + 1) {

			int focus = emptCells.get(i);

			
			changeState(focus, turn);
			int currScore = minimax(depth + turn, turn);
			currScores.add(currScore);

			if (turn == 1) { // computer's turn

				// if it reaches to the top
				if (depth == 0) Score.add(new State(currScore, focus));

			}

			// reset the board
			board[focus] = 0;

		}

		// computer's turn, get maximum point
		if (turn == 1)	return max(currScores);
		// user's turn, get maximum point
		else 			return min(currScores);

	}

	// the action with the highest score
	public int bestAction() {

		int max  = -10; // value
		int best = -1;  // index

		for (int i = 0; i < Score.size(); i = i + 1) {

			if (Score.get(i).score > max) {

				max  = Score.get(i).score;
				best = i;

			}

		}

		return Score.get(best).action;

	}

	// helping methods
	// whether game is over or not
	public boolean over() {

		return loose() || win() || getEmptCells().isEmpty();

	}

	public boolean culmination(int turn) {

		if (board[0] == turn && board[1] == turn && board[2] == turn)
            return true;
        
        if (board[3] == turn && board[4] == turn && board[5] == turn)
            return true;
        
        if (board[6] == turn && board[7] == turn && board[8] == turn)
            return true;
        
        if (board[0] == turn && board[3] == turn && board[6] == turn)
            return true;
        
        if (board[1] == turn && board[4] == turn && board[7] == turn)
            return true;
        
        if (board[2] == turn && board[5] == turn && board[8] == turn)
            return true;
        
        if (board[0] == turn && board[4] == turn && board[8] == turn)
            return true;
        
        if (board[2] == turn && board[4] == turn && board[6] == turn)
            return true;
        
        return false;


	}

	public boolean loose() {

		// the user won
		return culmination(1);

	}

	public boolean win() {

		// the computer won
		return culmination(2);

	}

	public void printBoard(boolean b) {

		// b: userFirst

		char[] str = new char[9];
		String ln  = "_________";

		for (int i = 0; i < 9; i = i + 1) {

			if (board[i] == 0) { // empty cell

				str[i] = ' ';

			} else {

				if ((board[i] == 2 && !b) || (board[i] == 1 && b)) {

					str[i] = 'O';

				} else {

					str[i] = 'X';

				}

			}

		}

		System.err.println(ln);
		System.err.println(str[0] + " | " + str[1] + " | " + str[2]);
		System.err.println(ln);
		System.err.println(str[3] + " | " + str[4] + " | " + str[5]);
		System.err.println(ln);
		System.err.println(str[6] + " | " + str[7] + " | " + str[8]);
		System.err.println(ln);

	}

}