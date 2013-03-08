/**
 * 
 */
package com.xid_studios.java.game.minesweeper;

/**
 * @author Exikle
 * @company Xid Studios
 */
public class ValueCreation {
	final int BOMB_VALUE = 99;
	int[][] gridMask;

	/**
	 * 
	 */
	public ValueCreation(int h, int w, int m) {
		int height = h, width = w, mines = m;
		int row = 7, column = 7, tempHold, numCells;

		numCells = height * width;
		gridMask = new int[width][height];

		for (int j = 0; j < mines; j++) {
			do {
				tempHold = (int) (Math.random() * numCells);
				row = tempHold / width;
				column = tempHold % height;
			} while (gridMask[width][height] == BOMB_VALUE);
			gridMask[width][height] = BOMB_VALUE;
		}
		proximityChecker();
	}

	public void proximityChecker() {
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				if (gridMask[x][y] >= BOMB_VALUE) {
					if (y != 9)// East
						gridMask[x][y + 1] += 1;
					if (y != 0)// West
						gridMask[x][y - 1] += 1;
					if (x != 0)// North
						gridMask[x - 1][y] = gridMask[x - 1][y] + 1;
					if (x != 9)// South
						gridMask[x + 1][y] = gridMask[x + 1][y] + 1;
					if (y != 9 && x != 0)// N.E.
						gridMask[x - 1][y + 1] += 1;
					if (x != 0 && y != 0)// N.W.
						gridMask[x - 1][y - 1] += 1;
					if (x != 9 && y != 0)// S.W.
						gridMask[x + 1][y - 1] += 1;
					if (x != 9 && y != 9)// S.E.
						gridMask[x + 1][y + 1] += 1;
				}
			}
		}
	}

	public int getMaskValues(int r, int c) {
		return (gridMask[r][c]);
	}
}
