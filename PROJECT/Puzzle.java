import java.util.Random;

/**
 * A class that generates the Sudoku puzzle
 * according the level specified.
 * The board generation code was greatly insight from
 * http://www.codeproject.com/Articles/23206/Sudoku-Algorithm-Generates-a-Valid-Sudoku-in-0-018
 * @author tsph001 - Sandy Phan
 *
 */
public class Puzzle {
	
	/**
	 * Create a new Sudoku puzzle with the
	 * specified difficulty level.
	 * @param dl The difficulty level
	 * partitioned into Easy, Medium and Hard.
	 * @return Returns the 2D array of the puzzle.
	 */
	public static Square[][] createPuzzle(int dl) {
		difficultyLevel = dl;
		initialisePuzzle();
		generatePuzzle();
		printPuzzle();
		return puzzle;
	}
	
	/**
	 * Generate the whole puzzle with appropriate
	 * cells removed according to the difficulty
	 * level specified.
	 */
	private static void generatePuzzle() {
		Random rand = new Random();
		boolean conflict = false;
		int k = 0;
		int duplicateFound = 0;
		
		// Loop through the board and assign numbers to the squares
		for (int i = 0; i < ROW_NUMBER; i++) {
			for (int j = 0; j < COLUMN_NUMBER; j++) {
				
				duplicateFound = 0;
				
				// This is when backtracking a square, and that square 
				if (puzzle[i][j].getAvailableValues().size() == 0) {
					puzzle[i][j].resetTrackingValues();
					puzzle[i][j].setCurrentValue(0);
					if (j > 1)
						j = j - 2;
					else {
						j = COLUMN_NUMBER - 2;
						i--;
					}
					continue;
				}
				
				try {
					k = rand.nextInt(puzzle[i][j].getAvailableValues().size());
					while (LegalCheck.checkLegal(puzzle, puzzle[i][j], puzzle[i][j].getAvailableValues().get(k))) {
						duplicateFound++;
						puzzle[i][j].getUsedValues().add(puzzle[i][j].getAvailableValues().get(k));
						puzzle[i][j].getAvailableValues().remove(k);
						if (puzzle[i][j].getAvailableValues().size() == 0) {
							puzzle[i][j].resetTrackingValues();
							puzzle[i][j].setCurrentValue(0);
							if (j > 1)
								j = j - 2;
							else {
								j = COLUMN_NUMBER - 2;
								i--;
							}
							conflict = true;
							break;
						} else {
							conflict = false;
						}
							
						k = rand.nextInt(puzzle[i][j].getAvailableValues().size());					
					}
					if (duplicateFound == 0) {
						conflict = false;
					}
						
				} catch (IllegalArgumentException e) {
					System.out.println("Size was: "+ puzzle[i][j].getAvailableValues().size());
				}
				
				
				if (!conflict) {
					puzzle[i][j].setCurrentValue(puzzle[i][j].getAvailableValues().get(k));
					puzzle[i][j].getUsedValues().add(puzzle[i][j].getAvailableValues().get(k));
					puzzle[i][j].getAvailableValues().remove(k);
					puzzle[i][j].setType(Square.PREDEFINE_CELL);
//					System.out.println("Cell[" + i + "][" + j + "]: " + puzzle[i][j].getCurrentValue());
				}
				
			}
//			System.out.println();
		}
		removeCells();
	}
	
	/**
	 * Initialize the puzzle by setting
	 * the values of the rows, columns
	 * and 3x3 box a square should be in.
	 */
	private static void initialisePuzzle() {
		// iterate through the grids/squares.
		for (int i = 0; i < ROW_NUMBER; i++) {
			for (int j = 0; j < COLUMN_NUMBER; j++) {
				if (i <= 2) {
					if (j <= 2) {
						puzzle[i][j] = new Square(boxNum[0], Square.EMPTY_CELL);
					} else if (j > 2 && j <= 5) {
						puzzle[i][j] = new Square(boxNum[1], Square.EMPTY_CELL);
					} else if (j > 5 && j <= 8) {
						puzzle[i][j] = new Square(boxNum[2], Square.EMPTY_CELL);
					}
				} else if (i > 2 && i <= 5) {
					if (j <= 2) {
						puzzle[i][j] = new Square(boxNum[3], Square.EMPTY_CELL);
					} else if (j > 2 && j <= 5) {
						puzzle[i][j] = new Square(boxNum[4], Square.EMPTY_CELL);
					} else if (j > 5 && j <= 8) {
						puzzle[i][j] = new Square(boxNum[5], Square.EMPTY_CELL);
					}
				} else if (i > 5 && i <= 8) {
					if (j <= 2) {
						puzzle[i][j] = new Square(boxNum[6], Square.EMPTY_CELL);
					} else if (j > 2 && j <= 5) {
						puzzle[i][j] = new Square(boxNum[7], Square.EMPTY_CELL);
					} else if (j > 5 && j <= 8) {
						puzzle[i][j] = new Square(boxNum[8], Square.EMPTY_CELL);
					}
				}
				puzzle[i][j].setColumn(j);
				puzzle[i][j].setRow(i);
			}
		}
	}
	
	public static void printPuzzle() {
		for (int i = 0; i < ROW_NUMBER; i++) {
			for (int j = 0; j < COLUMN_NUMBER; j++) {
				if (puzzle[i][j].getCurrentValue() == 0) {
					System.out.print("  ");
				} else
					System.out.print(puzzle[i][j].getCurrentValue() + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Remove the number of cells according
	 * to the difficulty level set from the
	 * constructor.
	 */
	private static void removeCells() {
		Random rand = new Random();
		int removeNum = 0;
		int min = 0, max = 0;
		if (difficultyLevel == EASY) {
			max = 49;
			min = 44;
		} else if (difficultyLevel == MEDIUM) {
			max = 56;
			min = 50;
			
		} else if (difficultyLevel == HARD) {
			max = 64;
			min = 57; 
		}
		
		removeNum = min + (int)(Math.random() * ((max - min) + 1));
		
		for (int i = 0; i < removeNum; i++) {
			int j = rand.nextInt(ROW_NUMBER);
			int k = rand.nextInt(COLUMN_NUMBER);
			if (puzzle[j][k].getCurrentValue() == 0 && 
					puzzle[j][k].getType() == Square.EMPTY_CELL) {
				i--;
				continue;
			}
			puzzle[j][k].setCurrentValue(INITIAL_VALUE);
			puzzle[j][k].setType(Square.USER_INPUT_CELL);
			puzzle[j][k].resetTrackingValues();
		}
	}
	
	// levels of difficulty (not sure where to place them yet).
	// Should this be placed in the main function or should this be
	// placed in any classes that have a difficulty defined.
	public static final int EASY = 0;
	public static final int MEDIUM = 1;
	public static final int HARD = 2;
	public static final int ROW_NUMBER = 9;
	public static final int COLUMN_NUMBER = 9;
	private static final int INITIAL_VALUE = 0;
	private static int difficultyLevel;
	
	// An array of the index of the 3x3 boxes.
	// An array of the position index of the 3x3 boxes in the following format.
	//  0 1 2
	//  3 4 5
	//  6 7 8
	// These should be a constant, as it will not be modified.
	private static final int[] boxNum = {0, 1, 2, 3, 4, 5, 6, 7, 8};
	private static Square puzzle[][] = new Square[ROW_NUMBER][COLUMN_NUMBER];
}
