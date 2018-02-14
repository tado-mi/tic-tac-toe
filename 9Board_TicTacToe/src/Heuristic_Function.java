/*
 *Andrew Nyaisonga
 * /*
 * Heuristic_Function_function = # of row/column/diagonal AI can win -  # of row/column/diagonal player can win
 */

public class Heuristic_Function {
	



	public static int threeValue(int a, int b, int c){ //Number of A and B in a row
		int additionA = 0;
		int additionB = 0;
		
		if(a == 1)
			additionA = additionA+1;
		else if(a == 2)
			additionB = additionB+1;
		
		if(b == 1)
			additionA = additionA+1;
		else if(b == 2)
			additionB = additionB+1;
		
		if(c == 1)
			additionA = additionA+1;
		else if(c == 2)
			additionB = additionB+1;
		
		//if there are 3 Y, Y wins
		if(additionA == 0 && additionB == 3)
			return -100; 
		//if there are 2 Y and no X, Y can win
		else if(additionA == 0 && additionB == 2)
			return -1; 
		//if there is 1 Y and no X, Y can win
		else if(additionA == 0 && additionB == 1)
			return -1; 
		//if there is 1 X and no Y, X can win
		else if(additionA == 1 && additionB == 0)
			return 1; 
		//if there are 2 X and no Y, X can win
		else if(additionA == 2 && additionB == 0)
			return 1; 
		//if there are 3 X, X wins
		else if(additionA == 3 && additionB == 0)
			return 100; 
		//else(either empty or full), no one has advantages. 
		else 
			return 0; 
	}

	public static int Heuristic_Function_score(int[] a){
		int sum = 0;

		//row wins
		sum = sum + threeValue(a[0],a[1],a[2]);
		sum = sum + threeValue(a[3],a[4],a[5]);
		sum = sum + threeValue(a[6],a[7],a[8]);

		//column wins
		sum = sum + threeValue(a[0],a[3],a[6]);
		sum = sum + threeValue(a[1],a[4],a[7]);
		sum = sum + threeValue(a[2],a[5],a[8]);

		//diagonal wins
		sum = sum + threeValue(a[0],a[4],a[8]);
		sum = sum + threeValue(a[2],a[4],a[6]);

		return sum;
	}

}
