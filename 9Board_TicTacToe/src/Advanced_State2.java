/*
 *Andrew Nyaisonga
 */

public class Advanced_State2 {

	int matrix, position;

	public Advanced_State2 (int matrix, int position) {
		this.matrix = matrix;
		this.position = position;
	}

	@Override
	public String toString(){
		return "["+(matrix+1)+","+(position+1)+"]";
	}
}
