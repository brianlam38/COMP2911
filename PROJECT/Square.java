import java.util.LinkedList;

/**
 * This class is a representation of the 81 cells
 * or square in a Sudoku game. It contains data
 * that are needed in generating the puzzle or during
 * solving a game.
 * @author Sandy
 *
 */
public class Square {
	
	/**
	 * Generate a square with its position and index
	 * defined.
	 * @param row The row in which the square is positioned.
	 * @param column The column in which the square is in.
	 * @param boxNum The 3x3 box number in which the square is in.
	 * @param positionInBox The position in the 3x3 box the square is in.
	 */
	public Square(int boxNum, int type) {
		this.threeByThreeBoxIndex = boxNum;
		this.type = type;
		this.prevType = EMPTY_CELL;
		this.availableValues = new LinkedList<Integer>();
		this.usedValues = new LinkedList<Integer>();
		
		// initialise the list of available numbers
		// at the start, all squares have all 9 numbers
		// available.
		for (int i = 1; i <= 9; i++) {
			availableValues.add(i);
		}		
	}
	
	/**
	 * Reset the available list used in backtracking.
	 */
	public void resetTrackingValues() {
		this.usedValues.clear();
		for (int i = 1; i <= 9; i++) {
			availableValues.add(i);
		}
	}
	
	/**
	 * Set the current value of the square. This is
	 * the value to be displayed in the box in GUI.
	 * @param value An integer that specifies the value
	 * to be displayed.
	 */
	public void setCurrentValue(int value) {
		this.currentValue = value;
	}
	
	/**
	 * Changes the value of the draft entry for the given
	 * number.
	 * @param value Integer specifying draft value
	 */
	public void switchDraftValue(int value){
		this.draftEntry[value] = !this.draftEntry[value];
	}
	
	/**
	 * Set the 3x3 box in which the current square is in.
	 * Index from 0 to 8.
	 * @param threeByThreeBoxIndex The 3x3 box number that
	 * the square is in range from 0 to 8.
	 */
	public void setThreeByThreeBox(int threeByThreeBoxIndex) {
		this.threeByThreeBoxIndex = threeByThreeBoxIndex;
	}

	/**
	 * Set the type of a square. The types can be Empty,
	 * or User-input, or hint, or predefined or error. This
	 * is used to determine the GUI for the cell/square.
	 * @param squareType
	 */
	public void setType(int squareType) {
		if (this.type != squareType){
			if (squareType == ERROR_CELL){
				this.prevType = this.type;
			}
			this.type = squareType;
		}
	}
	
	/**
	 * Set the row of a square at initialisation.
	 * @param row The row where the square belongs to.
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * Set the column of a square.
	 * @param column The column in which the square
	 * belongs to.
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	
	/**
	 * Get the current value of the square. This is the value
	 * displayed in the box in GUI.
	 * @return An integer that specifies the value displayed.
	 */
	public int getCurrentValue() {
		return this.currentValue;
	}
	
	/**
	 * Get the 3x3 box in which the current square is in.
	 * @return The 3x3 box number of the current square.
	 */
	public boolean isMarkedDraft(int value){
		return this.draftEntry[value];
	}
	
	/**
	 * Get the 3x3 region in which the square belongs to.
	 * @return The index of the 3x3 region.
	 */
	public int getThreeByThreeBox() {
		return threeByThreeBoxIndex;
	}
	
	/**
	 * Get the row in which the square belongs to.
	 * @return The row number.
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Get the column in which the square belongs to.
	 * @return The column number.
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * Get the list of available values or values
	 * which have not tried for a particular
	 * square.
	 * @return The list of available values.
	 */
	public LinkedList<Integer> getAvailableValues() {
		return availableValues;
	}

	/**
	 * Get the list of used values that were inappropriate
	 * for a particular square.
	 * @return The list of used values.
	 */
	public LinkedList<Integer> getUsedValues() {
		return usedValues;
	}
	
	/**
	 * Get the type of the square.
	 * @return The type in which the square was set to
	 * throughout the game.
	 */
	public int getType() {
		return this.type;
	}
	
	public int getPreviousType(){
		return this.prevType;
	}
	
	private int row;
	private int column;
	private int threeByThreeBoxIndex;
	private boolean[] draftEntry = {false, false, false, false, false, false, false, false, false};

	//0 for empty
	private int currentValue;
	private int type;
	private int prevType;
	private LinkedList<Integer> availableValues;
	private LinkedList<Integer> usedValues;
	public static final int EMPTY_CELL = 0;
	public static final int HINT_CELL = 1;
	public static final int USER_INPUT_CELL = 2;
	public static final int PREDEFINE_CELL = 3;
	public static final int ERROR_CELL = 4;
}
