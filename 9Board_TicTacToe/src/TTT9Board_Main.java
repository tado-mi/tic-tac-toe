/*
 *Andrew Nyaisonga
 */

import java.util.ArrayList;

public class TTT9Board_Main {





/*
    THis is the main driver of Part 2
    It uses Advanced_T_Model class intessively
 */



















	public static void main(String[] args){

		//infinite loop 
		while(true){
			Advanced_T_Model game = new Advanced_T_Model();
			boolean userFirst = true; 

			System.err.println("There are two players in this game, Which one you wanna be X or O (X plays first)?");
			String user = game.input.next(); 

			//check who will play first 
			//if computer plays first, default at [1,5] 
			if((user.equalsIgnoreCase("o"))){
				userFirst = false; 
				game.changeState(new Advanced_State2(0,4), 1);
				game.printBoard(userFirst);
			}

			else
				game.printBoard(userFirst);

			int count =0;

			while (!game.isGameOver()) {

				//Print all possible Action
				ArrayList<Advanced_State2> applicableActions = game.getapplicableAction();
				if(count != 0){
					System.err.print("Applicable Actions:");
					for(int i = 0;i<applicableActions.size();i++)
						System.err.print(applicableActions.get(i)+",");
					System.err.println();
				}

				boolean applicable = false;
				int userMatrix = -1;
				int userPosition = -1; 

				//if it's the first Action,ask for a Action
				if(count == 0){
					System.err.println("Your Turn");
					userMatrix = game.input.nextInt() - 1; 
					userPosition = game.input.nextInt() - 1;
				}
				else{
					//check if the input Action is applicable
					int count2 = 0;
					while(applicable == false){
						if(count2 == 0) System.err.println("Your turn ");
						else {
							game.printBoard(userFirst);
							System.err.println("That is a Ilapplicable Action. Play on the Applicable Action");
							System.err.print("Applicable Actions:");
							for(int i = 0;i<applicableActions.size();i++)
								System.err.print(applicableActions.get(i)+",");
							System.err.println();
						}
						userMatrix = game.input.nextInt() - 1; 
						userPosition = game.input.nextInt() - 1; 

						for(int i=0;i<applicableActions.size();i++){
							if(userMatrix == applicableActions.get(i).matrix && userPosition == applicableActions.get(i).position){
								applicable = true;
								break; 
							}
						}
						count2++;
					}


				}

				//make the Action and print the board
				game.changeState(new Advanced_State2(userMatrix, userPosition), 2);
				game.printBoard(userFirst);

				//check if the game is over
				if (game.isGameOver()) 
					break;

				//calculate the scores for computer's next Action
				game.getMinimax(0, 1);

				//make the Action with highest score
				Advanced_State2 AIAction = game.bestAction();
				game.changeState(AIAction, 1);

				//print out the computer's Action
				System.err.println();
				System.out.print("I am moving on board: "+ (AIAction.matrix +1)+" On position:"+(AIAction.position + 1));
				System.err.println();
				
				//print out the board after the Action
				game.printBoard(userFirst);

				//check if the game is over 
				if (game.isGameOver()) 
					break;
				count++;
			}

			//When the game is over check who won ot if it's a draw
			if (game.win())
				System.err.println("You actually won!!! You lucky I am using heuristic function buddy. ");
			else if (game.loose())
				System.err.println("I win, can't compete with AI!!");
			else
				System.err.println("We drew, You can do better!");
		}

	}
}
