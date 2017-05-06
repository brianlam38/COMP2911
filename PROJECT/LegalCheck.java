import java.util.LinkedList;

//TODO BUG!!! If a number in a square is changed to the same number (e.g. click on a user square with 6 and type 6)
// or if a LegalCheck is done on a square after the number has been turned in
// legal check returns illegal and the number will be turned red.

/**
 * This class provides methods that are used
 * in checking if certain value has duplicates
 * in certain regions such as rows, column or 
 * 3x3 box.
 * @author Sandy
 *
 */
public class LegalCheck {
	
	/**
	 * Check if a particular move in a particular square
	 * for the puzzle is valid/legal. That is, there is
	 * no duplicates in its row, column and region.
	 * @param p The puzzle or board.
	 * @param s The square that we want to place the value
	 * in.
	 * @param val The value needed for duplicate checks.
	 * @return True if there is a duplicate in any direction,
	 * and false otherwise.
	 */
	public static boolean checkLegal(Square[][] p, Square s, int val) {
		puzzle = p;
		int row = s.getRow();
		int column = s.getColumn();
		int boxNum = s.getThreeByThreeBox();
		boolean legal = false;
		
		legal = hasDuplicate(row, column, val);	
		if (legal == true) {
			return true;
		}
		legal = hasDuplicateInBox(boxNum, val, s);
		return legal;
	}
	
	/**
	 * Check duplicates in rows and columns.
	 * @param row The row where the square belongs to.
	 * @param column The column where the square belongs to.
	 * @param val The value that is needed for duplicate checks.
	 * @return True if there is a duplicate in current row or column.
	 * False otherwise.
	 */
	private static boolean hasDuplicate(int row, int column, int val) {
		for (int i = 0; i < Puzzle.COLUMN_NUMBER; i++) {
			if (puzzle[row][i].getType() == Square.EMPTY_CELL)
				continue;
			if (puzzle[row][i].getCurrentValue() == val && i != column)
				return true;
		}
		
		for (int i = 0; i < Puzzle.ROW_NUMBER; i++) {
			if (puzzle[i][column].getType() == Square.EMPTY_CELL)
				continue;
			if (puzzle[i][column].getCurrentValue() == val && i != row)
				return true;
		}
		
		return false;
	}
	
	/**
	 * Check if there are duplicates in the 3x3 box where
	 * the square belongs.
	 * @param boxNum The 3x3 region number in which the
	 * square belongs to.
	 * @param val The value for duplicate verification.
	 * @return True if there is a duplicate in the region.
	 * False otherwise.
	 */
	private static boolean hasDuplicateInBox(int boxNum, int val, Square currentSquare) {
		LinkedList<Square> list = getSquaresInRegion(boxNum);
		for (Square s : list) {
			if (s.getType() == Square.EMPTY_CELL)
				continue;
			if (s.getCurrentValue() == val && s != currentSquare)
				return true;
		}
		return false;
	}
	
	/**
	 * Get the list of squares that belong to the
	 * 3x3 box number given in the parameter.
	 * @param threeByThreeIndex The 3x3 box number.
	 * @return The list of squares that are in the given 
	 * box number.
	 */
	private static LinkedList<Square> getSquaresInRegion(int threeByThreeIndex) {
		LinkedList<Square> threeByList = new LinkedList<Square>();
		int iStart = 0, iEnd = 0;
		int jStart = 0, jEnd = 0;
		if (threeByThreeIndex == 0 ||
				threeByThreeIndex == 1 ||
				threeByThreeIndex == 2) {
			iStart = 0;
			iEnd = 3;
			jStart = 0;
			jEnd = 3;
			if (threeByThreeIndex == 1) {
				jStart = 3;
				jEnd = 6;
			} else if (threeByThreeIndex == 2) {
				jStart = 6;
				jEnd = 6;
			}
		} else if (threeByThreeIndex == 3 ||
				threeByThreeIndex == 4 ||
				threeByThreeIndex == 5) {
			iStart = 3;
			iEnd = 6;
			jStart = 0;
			jEnd = 3;
			if (threeByThreeIndex == 4) {
				jStart = 3;
				jEnd = 6;
			} else if (threeByThreeIndex == 5) {
				jStart = 6;
				jEnd = 9;
			}
		} else if (threeByThreeIndex == 6 ||
				threeByThreeIndex == 7 ||
				threeByThreeIndex == 8) {
			iStart = 6;
			iEnd = 9;
			jStart = 0;
			jEnd = 3;
			if (threeByThreeIndex == 7) {
				jStart = 3;
				jEnd = 6;
			} else if (threeByThreeIndex == 8) {
				jStart = 6;
				jEnd = 9;
			}
		}
		
		for (int i = iStart; i < iEnd; i++) {
			for (int j = jStart; j < jEnd; j++) {
				threeByList.add(puzzle[i][j]);
			}
		}
		return threeByList;
	}
	
	
	private static Square[][] puzzle;
}
