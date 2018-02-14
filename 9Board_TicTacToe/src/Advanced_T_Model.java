/*
 *Andrew Nyaisonga
 */


import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Advanced_T_Model {
	
	Scanner input = new Scanner(System.in); 

	ArrayList<Advanced_State2 > applicableAction;
	ArrayList<State_Advance> Score;
	ArrayList<int[]> board;
	int pAction;

	//Constructor; create a new 9x9 board 
	public Advanced_T_Model() {
		board = new ArrayList<int[]>();

		for(int i=0;i<9;i++){
			int[] matrix = new int[9];
			board.add(matrix);
		}
	}

	//return applicable Action
	public ArrayList<Advanced_State2 > getapplicableAction() {
		applicableAction = new ArrayList<>();
		int[] matrix = board.get(pAction);
		boolean full = true; 

		//check if the previous matrix is full 
		for (int i = 0; i < 9; i++) {
			if (matrix[i] == 0){
				full = false; 
				applicableAction.add(new Advanced_State2 (pAction, i));
			}
		}

		//if that board is not full, can only play in that matrix 
		if(full == false)
			return applicableAction;
		
		//otherwise, can play in any board; 
		else{
			applicableAction = new ArrayList<>();
			for(int i = 0;i < 9 ;i++){
				int[] matrix2 = board.get(i); 
				for(int j = 0; j < 9; j++){
					if(matrix2[j] == 0)
						applicableAction.add(new Advanced_State2 (i,j));
				}
			}
		}
		return applicableAction;
	}




	//initial call to minimax algorithm 
	public void getMinimax(int depth, int turn){
		Score = new ArrayList<>();
        H_MINIMAX(depth, turn, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	//minimax with depth-limit search (cutoff at depth 7) and alpha beta pruning 
	public int H_MINIMAX(int depth, int turn, int alpha, int beta) {

		ArrayList<Advanced_State2> applicableAction = getapplicableAction();

		//if someone wins the game or there is no applicable Action
		if (loose())
			return Integer.MAX_VALUE;
		if (win())
			return Integer.MIN_VALUE;
		if (applicableAction.isEmpty())
			return 0;


		if(depth == 6){     //if it reaches the cutoff depth, call the Heuristic_Function function to evaluate the score
            //I choose 6 because it had good balance of being fast or being slow
			int sumvalue = 0;
			for(int i=0;i<9;i++){
				int[] a = board.get(i);
				sumvalue = sumvalue + Heuristic_Function.Heuristic_Function_score(a);
			}

			if(turn == 1)
				return sumvalue;
			else 
				return -sumvalue;
		}

		//Computer's turn, get maximum point
		if (turn == 1) {
			int newBound = alpha; 

			//for each possible Action
			for (int i = 0; i < applicableAction.size(); ++i) {
                Advanced_State2  point = applicableAction.get(i);

				//make the Action
				changeState(point, 1);
				//recursive call 
				int currentScore = H_MINIMAX(depth + 1, 2, alpha, beta);

				//if it comes back to the top, add score to the final ArrayList
				if (depth == 0) 
					Score.add(new State_Advance(currentScore, point));

				//update the alpha(lower bound) if currentScore is bigger
				newBound = Math.max(currentScore, newBound);

				//reset the board 
				int[] m = board.get(point.matrix); 
				m[point.position] = 0; 
				board.set(point.matrix, m);

				//if the score bigger than beta, prune tree
				if(newBound > beta)
					return beta;
			} 
			return newBound;
		}

		//player's turn, get minimum point
		else{
			int newBound = beta; 

			//for each possible Action
			for (int i = 0; i < applicableAction.size(); ++i) {
                Advanced_State2  point = applicableAction.get(i);
				//make the Action
				changeState(point, 2);
				//recursive call 
				int currentScore = H_MINIMAX(depth + 1, 1, alpha, beta);

				//update the upper bound if it's lower 
				newBound = Math.min(currentScore,newBound);

				//reset the board 
				int[] m = board.get(point.matrix); 
				m[point.position] = 0; 
				board.set(point.matrix, m);

				//if the score is bigger than alpha, prune tree
				if(newBound < alpha)
					return alpha;
			}
			return newBound;
		}
	}


	//game is over is someone wins, or whole board is full (draw)
	public boolean isGameOver() {
		return (loose() || win() || getapplicableAction().isEmpty());
	}

	//check if the computer wins the game-return true if they winning and false otherwise
	public boolean loose(){
		for(int i=0;i<9;i++){
			int[] single = board.get(i); 
			boolean terminate = xwinning(single);

			if(terminate == true)
				return true;
		}
		return false;
	}

	//check if player wins the game- return true if they winning and false otherwise
	public boolean win(){
		for(int i=0;i<9;i++){
			int[] single = board.get(i); 
			boolean terminate = winning(single);

			if(terminate == true)
				return true;
		}
		return false;
	}

	//check if the x is winning
	public boolean xwinning(int[] single) {
		if(single[0] == 1 && single[1] == 1 && single[2] == 1)
			return true;
		else if (single[3] == 1 && single[4] == 1 && single[5] == 1)
			return true;
		else if (single[6] == 1 && single[7] == 1 && single[8] == 1)
			return true;
		else if (single[0] == 1 && single[3] == 1 && single[6] == 1)
			return true;
		else if (single[1] == 1 && single[4] == 1 && single[7] == 1)
			return true;
		else if (single[2] == 1 && single[5] == 1 && single[8] == 1)
			return true;
		else if (single[0] == 1 && single[4] == 1 && single[8] == 1)
			return true;
		else if (single[2] == 1 && single[4] == 1 && single[6] == 1)
			return true;
		else
			return false;
	}

	/*check if player is winning*/
	public boolean winning(int[] single) {
		if(single[0] == 2 && single[1] == 2 && single[2] == 2)
			return true;
		else if (single[3] == 2 && single[4] == 2 && single[5] == 2)
			return true;
		else if (single[6] == 2 && single[7] == 2 && single[8] == 2)
			return true;
		else if (single[0] == 2 && single[3] == 2 && single[6] == 2)
			return true;
		else if (single[1] == 2 && single[4] == 2 && single[7] == 2)
			return true;
		else if (single[2] == 2 && single[5] == 2 && single[8] == 2)
			return true;
		else if (single[0] == 2 && single[4] == 2 && single[8] == 2)
			return true;
		else if (single[2] == 2 && single[4] == 2 && single[6] == 2)
			return true;
		else
			return false;
	}
    public void changeState(Advanced_State2  point, int player) {

        int[] matrix = board.get(point.matrix);
        matrix[point.position] = player;
        board.set(point.matrix, matrix);

        //Update state
        pAction = point.position;
    }

    public Advanced_State2  bestAction() {
        int max = Integer.MIN_VALUE;
        int best = 0;

        //get the highest score
        for (int i = 0; i < Score.size(); i++) {
            if (Score.get(i).score > max) {
                max = Score.get(i).score;
                best = i;
            }
        }

        //check if there are Action with identical highest scores;
        ArrayList<Integer> index = new ArrayList<>();

        for(int i=0;i<Score.size();i++){
            if(Score.get(i).score == Score.get(best).score)
                index.add(i);
        }

        //if there are several Action with identical highest score, randomly pick one
        Random ran = new Random();
        best = ran.nextInt(index.size());

        return Score.get(index.get(best)).point;
    }


    //Printing the board to the standard output
	public void printBoard(boolean userFirst) {
		ArrayList<String[]> printArray = new ArrayList<>();
        System.err.println("_______________________");
		for(int i=0;i<9;i++){
			int[] matrix = board.get(i); 
			String[] p = new String[9]; 

			for(int j = 0;j<9;j++){
				if(matrix[j] == 0)
					p[j] = " ";
				else if(matrix[j] == 1 && userFirst == false)
					p[j] = "X"; 
				else if(matrix[j] == 2 && userFirst == false)
					p[j] = "O";
				else if(matrix[j] == 1 && userFirst == true)
					p[j] = "O"; 
				else
					p[j] = "X"; 
			}
			printArray.add(p);
		}

		//first
		System.err.print(printArray.get(0)[0] + " "+printArray.get(0)[1] +" "+ printArray.get(0)[2]
				+" |"+printArray.get(1)[0] + " "+printArray.get(1)[1] +" "+ printArray.get(1)[2]
						+" |"+printArray.get(2)[0] + " "+printArray.get(2)[1] +" "+ printArray.get(2)[2]+" | "+"\n");
		//second
		System.err.print(printArray.get(0)[3] + " "+printArray.get(0)[4] +" "+ printArray.get(0)[5]
				+" |"+printArray.get(1)[3] + " "+printArray.get(1)[4] +" "+ printArray.get(1)[5]
						+" |"+printArray.get(2)[3] + " "+printArray.get(2)[4] +" "+ printArray.get(2)[5]+" | "+"\n");
		//third
		System.err.print(printArray.get(0)[6] + " "+printArray.get(0)[7] +" "+ printArray.get(0)[8]
				+" |"+printArray.get(1)[6] + " "+printArray.get(1)[7] +" "+ printArray.get(1)[8]
						+" |"+printArray.get(2)[6] + " "+printArray.get(2)[7] +" "+ printArray.get(2)[8]+" | "+"\n");
		System.err.println("_______________________");
		//fourth
		System.err.print(printArray.get(3)[0] + " "+printArray.get(3)[1] +" "+ printArray.get(3)[2]
				+" |"+printArray.get(4)[0] + " "+printArray.get(4)[1] +" "+ printArray.get(4)[2]
						+" |"+printArray.get(5)[0] + " "+printArray.get(5)[1] +" "+ printArray.get(5)[2]+" | "+"\n");
		//fifth
		System.err.print(printArray.get(3)[3] + " "+printArray.get(3)[4] +" "+ printArray.get(3)[5]
				+" |"+printArray.get(4)[3] + " "+printArray.get(4)[4] +" "+ printArray.get(4)[5]
						+" |"+printArray.get(5)[3] + " "+printArray.get(5)[4] +" "+ printArray.get(5)[5]+" | "+"\n");
		//six
		System.err.print(printArray.get(3)[6] + " "+printArray.get(3)[7] +" "+ printArray.get(3)[8]
				+" |"+printArray.get(4)[6] + " "+printArray.get(4)[7] +" "+ printArray.get(4)[8]
						+" |"+printArray.get(5)[6] + " "+printArray.get(5)[7] +" "+ printArray.get(5)[8]+" | "+"\n");
		System.err.println("_______________________");
		//seventh
		System.err.print(printArray.get(6)[0] + " "+printArray.get(6)[1] +" "+ printArray.get(6)[2]
				+" |"+printArray.get(7)[0] + " "+printArray.get(7)[1] +" "+ printArray.get(7)[2]
						+" |"+printArray.get(8)[0] + " "+printArray.get(8)[1] +" "+ printArray.get(8)[2]+" | "+"\n");
		//eighth
		System.err.print(printArray.get(6)[3] + " "+printArray.get(6)[4] +" "+ printArray.get(6)[5]
				+" |"+printArray.get(7)[3] + " "+printArray.get(7)[4] +" "+ printArray.get(7)[5]
						+" |"+printArray.get(8)[3] + " "+printArray.get(8)[4] +" "+ printArray.get(8)[5]+" | "+"\n");
		//ninth
		System.err.print(printArray.get(6)[6] + " "+printArray.get(6)[7] +" "+ printArray.get(6)[8]
				+" |"+printArray.get(7)[6] + " "+printArray.get(7)[7] +" "+ printArray.get(7)[8]
						+" |"+printArray.get(8)[6] + " "+printArray.get(8)[7] +" "+ printArray.get(8)[8]+" | "+"\n");
        System.err.println("_______________________");
	}
}
