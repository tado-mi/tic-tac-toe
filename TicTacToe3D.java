import java.util.*;
import util.*;

public class TicTacToe3D {

	Scanner input = new Scanner(System.in);

	ArrayList<Position3D>	emptCells;
	ArrayList<State3D>		Score;

	ArrayList<int[]> board;

	int numBoard;

	public TicTacToe3D() {

		board = new ArrayList<int[]>();

		for (int i = 0; i < 9; i = i + 1) {

			int[] temp = new int[9];
			board.add(temp);

		}

	}

	public ArrayList<Position3D> getEmptCells() {

		emptCells = new ArrayList<Position3D>();
		int[] temp = board.get(numBoard);

		boolean full = true;

		// whether the previous sub-board is full or not
		for (int j = 0; j < 9; j = j + 1) {

			if (temp[j] == 0) {

				full = false;
				emptCells.add(new Position3D(numBoard, j));

			}

		}

		if (!full) { // can play only in this sub-grid

			return emptCells;

		}

		// else can play in any board
		for (int i = 0; i < 9; i = i + 1) {

			int[] tempi = board.get(i);
			for (int j = 0; j < 9; j = j + 1) {

				if (tempi[j] == 0) emptCells.add(new Position3D(i, j));

			}

		}

		return emptCells;

	}

	public void get(int depth, int turn) {

		Score = new ArrayList<State3D>();
		minimax(depth, turn, Integer.MIN_VALUE, Integer.MAX_VALUE);


	}

	public int assessment(int a, int b, int c) {

		int count1 = 0;
		int count2 = 0;

		if 		(a == 1) count1 = count1 + 1;
		else if (a == 2) count2 = count2 + 1;

		if 		(b == 1) count1 = count1 + 1;
		else if (b == 2) count2 = count2 + 1;

		if 		(c == 1) count1 = count1 + 1;
		else if (c == 2) count2 = count2 + 1;

		// computer won
		if (count1 == 3) return  100;
		// user won
		if (count2 == 3) return -100;

		// computer can win
		if ((count1 > 0) && count2 == 0) return 1;
		// user can win
		if (count1 == 0 && (count2 > 0)) return -1;

		// no one has an advantage
		return 0;

	}

	// heuristic helpers
	public int heuristicScore(int[] grid) {

		int s = 0;

		// row
		s = s + assessment(grid[0], grid[1], grid[2]);
		s = s + assessment(grid[3], grid[4], grid[5]);
		s = s + assessment(grid[6], grid[7], grid[8]);

		// column
		s = s + assessment(grid[0], grid[3], grid[6]);
		s = s + assessment(grid[1], grid[4], grid[7]);
		s = s + assessment(grid[2], grid[5], grid[8]);

		// diagonal
		s = s + assessment(grid[0], grid[4], grid[8]);
		s = s + assessment(grid[2], grid[4], grid[6]);

		return s;

 	}

	// minimax algorithm with alpa-beta pruning and
	// depth-limit search (cut off at 7; 7 chosen experimentally)
	public int minimax(int depth, int turn, int alpha, int beta) {

		if (loose())	return Integer.MAX_VALUE;
		if (win())		return Integer.MIN_VALUE;

		ArrayList<Position3D> emptCells = getEmptCells();
		if (emptCells.isEmpty()) return 0;


		if (depth == 6) { // limit is reached

			int s = 0; // sum assessment

			for (int i = 0; i < 9; i = i + 1) {

				int[] temp = board.get(i);
				s = s + heuristicScore(temp);

			}

			if (turn == 1)	return  s;
			else 			return -s;


		}

		if (turn == 1) { // computer's turn

			int newBound = alpha;

			for (int i = 0; i < emptCells.size(); i = i + 1) {

				Position3D focus = emptCells.get(i);

				changeState(focus, 1);
				int currScore = minimax(depth + 1, 2, alpha, beta);

				// came to the top
				if (depth == 0) Score.add(new State3D(currScore, focus));

				// update alpha: lower boud; if needed
				newBound = Math.max(currScore, newBound);

				// reset the board
				int[] temp = board.get(focus.subGrid);
				temp[focus.pos] = 0;
				board.set(focus.subGrid, temp);

				// prune the tree
				if (newBound > beta) return beta;

			}

			return newBound;

		} else { // user's turn

			int newBound = beta;

			for (int i = 0; i < emptCells.size(); i = i + 1) {

				Position3D focus = emptCells.get(i);

				changeState(focus, 2);
				int currScore = minimax(depth + 1, 1, alpha, beta);

				// update beta: upper boud; if needed
				newBound = Math.min(currScore, newBound);

				// reset the board
				int[] temp = board.get(focus.subGrid);
				temp[focus.pos] = 0;
				board.set(focus.subGrid, temp);

				// prune the tree
				if (newBound < alpha) return alpha;

			}

			return newBound;

		}

	}

	public boolean over() {

		return loose() || win() || getEmptCells().isEmpty();

	}

	public boolean culmination(int[] board, int turn) {

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

	public boolean win() {

		for (int i = 0; i < 9; i = i + 1) {

			int[] subGrid = board.get(i);
			if (culmination(subGrid, 2)) return true;

		}

		return false;

	}

	public boolean loose() {

		for (int i = 0; i < 9; i = i + 1) {

			int[] subGrid = board.get(i);
			if (culmination(subGrid, 1)) return true;

		}

		return false;

	}

	public void changeState(Position3D focus, int turn) {

		int[] curr = board.get(focus.subGrid);
		curr[focus.pos] = turn;
		board.set(focus.subGrid, curr);

		// update the state
		numBoard = focus.pos;

	}

	public Position3D bestAction() {

		int max  = Integer.MIN_VALUE;
		int temp = -1;

		for (int i = 0; i < Score.size(); i = i + 1) {

			if (Score.get(i).score > max) {

				max  = Score.get(i).score;
				temp = i;

			}

		}

		// if there are other actions with identical highest score:
		ArrayList<Integer> indexSet = new ArrayList<Integer>();

		for (int  i = 0; i < Score.size(); i = i + 1) {

			if (Score.get(i).score == max) indexSet.add(i);

		}

		// choose an action at random
		Random random = new Random();
		int index = random.nextInt(indexSet.size());

		return Score.get(indexSet.get(index)).pos;

	}

	public void printBoard(boolean b) {

		ArrayList<String[]> str = new ArrayList<>();
		String ln = "_____________________\n";

        System.err.print(ln);

        for (int i = 0; i < 9; i = i + 1) {

        	int[]  subGrid = board.get(i);
        	String[] temp = new String[9];

        	for (int j = 0; j < 9; j = j + 1) {

        		if (subGrid[j] == 0) { // empty cell

        			temp[j] = " ";

        		} else if ((subGrid[j] == 2 && !b) || (subGrid[j] == 1 && b)) {

        			temp[j] = "O";

        		} else {

        			temp[j] = "X";

        		}


        	}

        	str.add(temp);

        }

        for (int i = 0; i < 9; i = i + 3) {

        	for (int j = 0; j < 9; j = j + 3) {

        		String temp = str.get(i)[j] + " " + str.get(i)[j + 1] + " " + str.get(i)[j + 2]
				+ " | " + str.get(i + 1)[j] + " " + str.get(i + 1)[j + 1] + " " + str.get(i + 1)[j + 2]
				+ " | " + str.get(i + 2)[j] + " " + str.get(i + 2)[j + 1] + " " + str.get(i + 2)[j + 2] + " | ";
				System.err.println(temp);

        	}

        	System.err.print(ln);

        }

	}

}
