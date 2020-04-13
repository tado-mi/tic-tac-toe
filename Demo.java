import java.util.ArrayList;
import util.*;

public class Demo{

	public static void Part1() {

		while (true) {

			TicTacToe2D game = new TicTacToe2D();

			// user decides who will play first
			System.err.print("which would you like to be? X / O: ");

			String userCh = game.input.next();

			boolean userFirst = true;
			if (userCh.equals("o") || userCh.equals("O")) {

				userFirst = false;
				game.changeState(4, 1);

			}

			System.err.println("starting!");
			game.printBoard(userFirst);

			while (!game.over()) {

				ArrayList<Integer> emptCells = game.getEmptCells();

				int user  = 1;
				int count = 0;

				// prompt for user input
				while (true) {

					if (count == 0) {

						System.err.print("your turn: ");

					} else {

						game.printBoard(userFirst);
						System.err.print("that cell is occupied. ");
						System.err.print("please, choose a free cell: ");

					}

					user = game.input.nextInt() - 1;

					if (game.board[user] == 0) {

						break;

					}

					count = count + 1;

				}

				game.changeState(user, 2);
				game.printBoard(userFirst);

				if (game.over()) break;

				// calc best action
				game.get(0, 1);

				// choose the best action
				int choice = game.bestAction();

				// make the move!
				game.changeState(choice, 1);
				System.err.println("\nI made my move!\n");
				game.printBoard(userFirst);

			}

			System.err.println("we finished this session.");
			System.err.print("would you like to continue? y/n.");

			String cont = game.input.next();

			if (cont.equals("n")) break;

		}

	}

	public static void Part2() {

		while (true) {

			TicTacToe3D game = new TicTacToe3D();

			// user decides who will play first
			System.err.print("which would you like to be? X / O: ");
			String  userCh		= game.input.next();
			boolean userFirst	= true;
			if (userCh.equals("o") || userCh.equals("O")) {

				userFirst = false;
				game.changeState(new Position3D(0, 4), 1);

			}

			System.err.println("starting!");
			game.printBoard(userFirst);

			while (!game.over()) {

				ArrayList<Position3D> emptCells = game.getEmptCells();

				// print legal locations
				System.err.print("your may play on board ");
				int userBoard = emptCells.get(0).subGrid;
				System.err.print(userBoard);

				System.err.print("; on positions ");
				for (int i = 0; i < emptCells.size(); i = i + 1) {
					System.err.print((emptCells.get(i).pos + 1) + ", ");
				}

				System.err.println();

				int 	user 	= -1;
				int 	count 	=  0;

				// prompt for user input
				while (true) {

					if (count == 0) {

						System.err.print("your turn: ");

					} else {

						game.printBoard(userFirst);
						System.err.print("that cell is occupied. ");
						System.err.print("please, choose a free cell on board ");
						System.err.print(userBoard + ": ");

					}

					user = game.input.nextInt() - 1;
					if (game.board.get(userBoard)[user] == 0) break;

					count = count + 1;

				}

				game.changeState(new Position3D(userBoard, user), 2);
				game.printBoard(userFirst);

				if (game.over()) break;

				// calc best action
				game.get(0, 1);

				// choose the best action
				Position3D choice = game.bestAction();

				// make the move!
				game.changeState(choice, 1);
				System.err.println("\nI made my move!\n");
				game.printBoard(userFirst);

			}

			System.err.println("we finished this session.");
			System.err.print("would you like to continue? y/n.");

			String cont = game.input.next();

			if (cont.equals("n")) break;

		}

	}

	public static void main(String[] args) {

		System.err.println("welcome to Tic-Tac-Toe.");
		System.err.println("it is a two player game: X vs O. X plays first.");
		System.err.println("please, insert the # of the cell you'd like to occupy, counting from top-left corner, starting at 1 for your convenience");

		if (args[0].equals("1")) Part1();
		if (args[0].equals("2")) Part2();

		System.out.println("hope you were impressed by me!");
		System.out.println("see ya later! (:");

	}


}
