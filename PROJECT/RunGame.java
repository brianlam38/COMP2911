
public class RunGame {
	
	public static void main(String args[]){
		Square[][] puzzle = Puzzle.createPuzzle(2);
		GameInterface board = new GameInterface(puzzle);
	}

}
