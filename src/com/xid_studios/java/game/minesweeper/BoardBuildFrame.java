/**
 * 
 */
package com.xid_studios.java.game.minesweeper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * @author Xid Studios - Exikle
 * 
 */
@SuppressWarnings("serial")
public class BoardBuildFrame extends JFrame implements ActionListener {
	int gridHeight = 10, gridWidth = 10, gridMines = 7;
	final int BOARD_HEIGHT = 450, BOARD_WIDTH = 500;
	int[][] valueMask;
	final String TITLE = "Minesweeper";
	private ValueCreation valueGen;

	JButton btnReset = new JButton("Reset");
	JButton btnLevelChange = new JButton("Change Difficulty");
	JButton[][] boardButtons;
	JPanel gridPanel = new JPanel();
	JPanel bottomPanel = new JPanel();

	public void setHeightWidth(int h, int w) {
		gridHeight = h;
		gridWidth = w;
		System.out.println("Height/Width Set");
		createBoard();
	}

	public void createBoard() {
		JLabel lblTitle = new JLabel(TITLE);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblTitle, BorderLayout.NORTH);

		btnReset.addActionListener(this);
		btnLevelChange.addActionListener(this);
		bottomPanel.setLayout(new GridLayout(1, 2));
		bottomPanel.add(btnReset);
		bottomPanel.add(btnLevelChange);

		this.add(bottomPanel, BorderLayout.SOUTH);
		setGrid(gridHeight, gridWidth, gridMines);
		this.add(gridPanel, BorderLayout.CENTER);
	}

	public void setGrid(int h, int w, int m) {
		gridPanel.setLayout(new GridLayout(h, w));
		boardButtons = new JButton[w][h];
		valueMask = new int[w][h];
		valueGen = new ValueCreation(h, w, m);
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				boardButtons[x][y].addActionListener(this);
				gridPanel.add(boardButtons[x][y]);
				valueMask[x][y] = valueGen.getMaskValues(x, y);
				boardButtons[x][y] = new JButton("" + valueMask[x][y]);
			}
		}
	}

	public BoardBuildFrame() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - BOARD_WIDTH) / 2);
		int y = (int) ((dimension.getHeight() - BOARD_HEIGHT) / 2);
		setLocation(x, y);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(BOARD_WIDTH, BOARD_HEIGHT);
		setVisible(true);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLevelChange) {
			System.out.println("change");
			for (int x = 0; x < gridWidth; x++) {
				for (int y = 0; y < gridHeight; y++) {
					gridPanel.remove(boardButtons[x][y]);
				}
			}
			// setGrid(gridHeight, gridWidth);//set new grid
			this.add(gridPanel, BorderLayout.CENTER);
			revalidate();
			repaint();

		} else if (e.getSource() == btnReset) {

		}
		for (int x = 0; x < gridWidth; x++) {
			for (int y = 0; y < gridHeight; y++) {
				if (e.getSource() == boardButtons[x][y]) {
					System.out.println(x + "," + y);
				}
			}
		}

	}
}
