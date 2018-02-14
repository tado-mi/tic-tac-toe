/*
 *Andrew Nyaisonga
 */

import java.util.ArrayList;

public class TTTBasic_Main {

/*
    THis is the main driver of Part 1
 */













    public static void main(String[] args){



        while(true){
            Basic_T_Model game = new Basic_T_Model();
            boolean userFirst = true;

            boolean isApplicable = false;

            //decide who plays first
            System.err.println("There are two players in this game, Which one you wanna be X or O (X plays first)?");
            String user = game.input.next();

            //if computer plays first, default at position[5]
            if((user.equalsIgnoreCase("o"))){
                userFirst = false;
                game.changeState(4, 1);
                game.printBoard(userFirst);
            }

            //if human plays first, print the board
            else
                game.printBoard(userFirst);

            while (!game.isGameOver()) {

                ArrayList<Integer> applicable = game.getapplicableAction();
                int userAction = 1;
                int count =0;
                //Ask user for input
                while(isApplicable == false){
                    if(count == 0) System.err.println("Your turn ");
                    else {
                        game.printBoard(userFirst);
                        System.err.println("That is a Ilapplicable Action. Play on the open position");
                    }
                    userAction = game.input.nextInt() -1;

                    for(int i=0;i<applicable.size();i++){
                        if(userAction == applicable.get(i)){
                            isApplicable = true;
                            break;
                        }
                    }
                    count ++;
                }
                game.changeState(userAction, 2);
                game.printBoard(userFirst);
                isApplicable = false;

                //check if the game is over
                if (game.isGameOver())
                    break;

                //calculate the best Action
                game.getMinimax(0, 1);



                //pick and make the best Action
                int AIAction = game.bestAction();
                game.changeState(AIAction, 1);
                game.printBoard(userFirst);
            }

            //When the game is over check who won ot if it's a draw
            if (game.loose())
                System.err.println("I win, can't compete with AI!!");
            else if (game.win())
                System.err.println("This is impossible. You actually won!!!");
            else
                System.err.println("We drew, You can do better!");
        }
    }

}
