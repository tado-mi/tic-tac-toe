/*
 *Andrew Nyaisonga
 */

import java.util.*;

public class Basic_T_Model {

    Scanner input = new Scanner(System.in);

    ArrayList<Integer> applicableAction;
    ArrayList<State> Score;
    int[] board;

    //Constructor
    public Basic_T_Model() {
        board = new int[9];
    }

    public ArrayList<Integer> getapplicableAction() { //send all applicable Action at this state
        applicableAction = new ArrayList<>();
        for (int i = 0; i < 9; ++i) {
            if (board[i] == 0)
                applicableAction.add(i);
        }
        return applicableAction;
    }

    public void getMinimax(int depth, int turn){
        Score = new ArrayList<>();
        MINIMAX(depth, turn);
    }

    //Minimax algorithm
    public int MINIMAX(int depth, int turn) {

        ArrayList<Integer> applicableAction = getapplicableAction();
        ArrayList<Integer> scores = new ArrayList<>();

        /*Terminal check for the win or loose*/
        if (loose())
            return 1;
        if (win())
            return -1;
        if (applicableAction.isEmpty())
            return 0;

        //Recursive call for each possible Action
        for (int i = 0; i < applicableAction.size(); ++i) {
            int point = applicableAction.get(i);

            //Computer's Action
            if (turn == 1) {
                changeState(point, 1);
                int currentScore = MINIMAX(depth + 1, 2);
                scores.add(currentScore);

                //if it reaches back to the top, store the score
                if (depth == 0)
                    Score.add(new State(currentScore, point));
            }

            //Player's turn
            else if (turn == 2) {
                changeState(point, 2);
                int currentScore = MINIMAX(depth + 1, 1);
                scores.add(currentScore);
            }

            //reset the board
            board[point] = 0;
        }

        //Computers's turn, get maximum point
        if(turn == 1)
            return MAX(scores);
            //Player's turn, get maximum point
        else
            return MIN(scores);
    }

    //Game over if someone wins, or board is full (draw)
    public boolean isGameOver() {
        return (loose() || win() ||getapplicableAction().isEmpty());
    }

    // user win
    public boolean loose() {
        if(board[0] == 1 && board[1] == 1 && board[2] == 1)
            return true;
        else if (board[3] == 1 && board[4] == 1 && board[5] == 1)
            return true;
        else if (board[6] == 1 && board[7] == 1 && board[8] == 1)
            return true;
        else if (board[0] == 1 && board[3] == 1 && board[6] == 1)
            return true;
        else if (board[1] == 1 && board[4] == 1 && board[7] == 1)
            return true;
        else if (board[2] == 1 && board[5] == 1 && board[8] == 1)
            return true;
        else if (board[0] == 1 && board[4] == 1 && board[8] == 1)
            return true;
        else if (board[2] == 1 && board[4] == 1 && board[6] == 1)
            return true;
        else
            return false;
    }

    // Computer wins
    public boolean win() {
        if(board[0] == 2 && board[1] == 2 && board[2] == 2)
            return true;
        else if (board[3] == 2 && board[4] == 2 && board[5] == 2)
            return true;
        else if (board[6] == 2 && board[7] == 2 && board[8] == 2)
            return true;
        else if (board[0] == 2 && board[3] == 2 && board[6] == 2)
            return true;
        else if (board[1] == 2 && board[4] == 2 && board[7] == 2)
            return true;
        else if (board[2] == 2 && board[5] == 2 && board[8] == 2)
            return true;
        else if (board[0] == 2 && board[4] == 2 && board[8] == 2)
            return true;
        else if (board[2] == 2 && board[4] == 2 && board[6] == 2)
            return true;
        else
            return false;
    }

    //print the board, change integers into X&O
    public void printBoard(boolean userFirst) {

        String[] p = new String[9];
        System.err.println("_________");
        for(int i=0;i<9;i++){

            if(board[i] == 0)
                p[i] = " ";
            else if(board[i] == 1 && userFirst == false)
                p[i] = "X";
            else if(board[i] == 2 && userFirst == false)
                p[i] = "O";
            else if(board[i] == 1 && userFirst == true)
                p[i] = "O";
            else
                p[i] = "X";
        }

        System.err.print(p[0] + " | "+p[1] +" | "+ p[2]+"\n");
        System.err.println("_________");
        System.err.print(p[3] + " | "+p[4] +" | "+ p[5]+"\n");
        System.err.println("_________");
        System.err.print(p[6] + " | "+p[7] +" | "+ p[8]+"\n");
        System.err.println("---------");

    }

    //get the minimum number from a ArrayList
    public int MIN(ArrayList<Integer> ArrayList) {
        int min = 1;
        for (int i = 0; i < ArrayList.size(); ++i) {
            if (ArrayList.get(i) < min)
                min = ArrayList.get(i);
        }
        return min;
    }

    //get the maximum number from a ArrayList
    public int MAX(ArrayList<Integer> ArrayList) {
        int max = -1;
        for (int i = 0; i < ArrayList.size(); ++i) {
            if (ArrayList.get(i) > max)
                max = ArrayList.get(i);
        }
        return max;
    }

    public void changeState(int point, int player) {
        //board[i] == 2, player Action
        //board[i] == 1, computer Action
        board[point] = player;
    }


    //best Action = the Action with highest score
    public int bestAction() {
        int max = -10;
        int best = -1;

        for (int i = 0; i < Score.size(); ++i) {
            if (Score.get(i).score > max) {
                max = Score.get(i).score;
                best = i;
            }
        }

        return Score.get(best).Action;
    }
}


