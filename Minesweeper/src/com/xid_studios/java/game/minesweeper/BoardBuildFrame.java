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
	int gridHeight = 10, gridWidth = 10;
	final int BOARD_HEIGHT = 450, BOARD_WIDTH = 500;
	final String TITLE = "Minesweeper";

	JButton btnReset = new JButton("Reset");
	JButton[][] boardButtons;
	JPanel gridPanel = new JPanel();

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
		this.add(btnReset, BorderLayout.SOUTH);
		setGrid(gridHeight, gridWidth);
		this.add(gridPanel, BorderLayout.CENTER);
	}

	public void setGrid(int h, int w) {
		gridPanel.setLayout(new GridLayout(h, w));
		boardButtons = new JButton[w][h];
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				boardButtons[x][y] = new JButton();
				boardButtons[x][y].addActionListener(this);
				gridPanel.add(boardButtons[x][y]);
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
		for (int x = 0; x < gridWidth; x++) {
			for (int y = 0; y < gridHeight; y++) {
				if (e.getSource() == boardButtons[x][y])
					System.out.println(x + "," + y);
			}
		}

	}
}
