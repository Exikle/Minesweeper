package com.xid_studios.java.game.minesweeper;

/**
 * GenerateMines class.
 */
public class GenerateMines {
	final int BOMB_VALUE = 99;
	int[][] board;

	/**
	 * Constructor
	 * pre: none
	 * post: All Mines are created.
	 */
	public GenerateMines(int height, int width, int bombs) {
		int temp;
		int row, column;
		final int BOARD_HEIGHT = height;
		final int BOARD_WIDTH = width;
		final int NUMBER_OF_CELLS = BOARD_HEIGHT * BOARD_WIDTH;
		final int NUM_OF_MINES = 10;
		board = new int[BOARD_WIDTH][BOARD_HEIGHT];

		for (int j = 0; j < NUM_OF_MINES; j++) {
			do {
				temp = (int) (Math.random() * NUMBER_OF_CELLS);
				row = temp / BOARD_WIDTH;
				column = temp % BOARD_HEIGHT;
			} while (board[row][column] == BOMB_VALUE);
			board[row][column] = BOMB_VALUE;
		}
		proximityValueGenerate();
	}

	/**
	 * Check around the mine "block" and adds one to the proximity counter
	 * pre: none
	 * post: All "blocks" around the counter have been given there value based on mine proximity.
	 */
	public void proximityValueGenerate() {
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				if (board[x][y] >= BOMB_VALUE) {
					if (y != 9)// East
						board[x][y + 1] += 1;
					if (y != 0)// West
						board[x][y - 1] += 1;
					if (x != 0)// North
						board[x - 1][y] = board[x - 1][y] + 1;
					if (x != 9)// South
						board[x + 1][y] = board[x + 1][y] + 1;
					if (y != 9 && x != 0)// N.E.
						board[x - 1][y + 1] += 1;
					if (x != 0 && y != 0)// N.W.
						board[x - 1][y - 1] += 1;
					if (x != 9 && y != 0)// S.W.
						board[x + 1][y - 1] += 1;
					if (x != 9 && y != 9)// S.E.
						board[x + 1][y + 1] += 1;
				}
			}
		}
	}

	/**
	 * Returns the value of each position on the board.
	 * pre: int r, int c
	 * post: The value of each position on the board has been returned.
	 */
	public int getMinePos(int r, int c) {
		return (board[r][c]);
	}
}