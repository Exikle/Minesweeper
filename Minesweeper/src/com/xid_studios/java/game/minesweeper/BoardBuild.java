package com.xid_studios.java.game.minesweeper;

/**
 * BoardBuild class
 */

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class BoardBuild extends JFrame implements ActionListener {
	public static int boardHeight = 10;
	public static int boardWidth = 10;
	public static int bombAmount = 10;
	public static final int BOMB_VALUE = 99; 
	public static final int NO_MINE = 0;//The empty 

	static BoardBuild board;
	static JPanel p1, p2, p3;

	JButton easyBut = new JButton("Easy");
	JButton medBut = new JButton("Medium");
	JButton hardBut = new JButton("Hard");

	int miss = 0; // top label
	JLabel lbl1 = new JLabel("Minesweeper"); // all 100 of the board pieces to click
	JButton[][] btn = new JButton[boardWidth][boardHeight]; // board piece values
	int mines[][] = new int[boardWidth][boardHeight];
	private GenerateMines mineGen;
	JButton btnReset = new JButton("Reset");

	static JFrame d = new JFrame();
	static JButton btnEasy = new btnDif("Easy");
	static JButton btnMed = new btnDif("Med");
	static JButton btnHard = new btnDif("Hard");


	/**
	 * Constructer
	 * pre: none
	 * post:  The board has been built and all values have been given.
	 */
	public BoardBuild() {
		super("Minesweeper");
		p1 = new JPanel();
		p1.add(lbl1, BorderLayout.CENTER);

		p2 = new JPanel();
		p2.setLayout(new GridLayout(boardWidth, boardHeight));

		for (int x = 0; x < boardHeight; x++) {
			for (int y = 0; y < boardWidth; y++) {
				btn[x][y] = new JButton("");
				btn[x][y].addActionListener(this);
				p2.add(btn[x][y]);
			}
		}
		getMines();
		p3 = new JPanel();
		btnReset.addActionListener(this);
		p3.add(btnReset, BorderLayout.CENTER);
	}

	/**
	 * Obtains new values for each button and changes the text color of each button accordingly
	 * pre: none
	 * post:  The new values for each button were obtained and the text color of each button is changed accordingly
	 */
	public void getMines() {
		mineGen = new GenerateMines(boardHeight, boardWidth, bombAmount);
		System.out.println("New Mine Values Generated"); // sets indicator according to how many bombs around
		for (int x = 0; x < boardHeight; x++) { 
			for (int y = 0; y < boardWidth; y++) {
				btn[x][y].setText("");
				mines[x][y] = mineGen.getMinePos(x, y);
				if (mines[x][y] >= BOMB_VALUE) 
					btn[x][y].setForeground(Color.RED);
				else {
					btn[x][y].setBackground(null);
					if (mines[x][y] == 1)
						btn[x][y].setForeground(Color.BLUE);
					else if (mines[x][y] == 2)
						btn[x][y].setForeground(Color.GREEN);
					else if (mines[x][y] == 3)
						btn[x][y].setForeground(Color.YELLOW);
					else if (mines[x][y] == 4)
						btn[x][y].setForeground(Color.ORANGE);
					else if (mines[x][y] == 5)
						btn[x][y].setForeground(Color.RED);
				}
			}
		}
	}

	/**
	 * Checks if any of the buttons are clicked
	 * pre: none
	 * post: The button is displayed as clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		for (int x = 0; x < boardHeight; x++) {
			for (int y = 0; y < boardWidth; y++) {
				if (e.getSource() == btn[x][y]) {
					btn[x][y].removeActionListener(this);
					if (mines[x][y] >= BOMB_VALUE) {
						showBoard();
						break;
						//            return;
					} else if (mines[x][y] != NO_MINE) {
						btn[x][y].setText("" + mines[x][y]);
						btn[x][y].setBackground(Color.GRAY);
						miss++;
					} else if (mines[x][y] == NO_MINE)
						floodFill(x, y);
					System.out.println(miss);
					checkWin();
				}
			}
		}
		if (e.getSource() == btnReset) {
			resetBoard();
		}

		if (e.getSource()==btnEasy){
			System.out.println("easy");
		}
		else if (e.getSource()==btnMed){
			System.out.println("med");
		}
		else if (e.getSource()==btnHard){
			System.out.println("hard");
		}
	}

	/**
	 * Checks the proximity of the button clicked and "displays" it
	 * pre: none
	 * post: The button clicked, the buttons in its proximity are displayed as visible.
	 */
	public void floodFill(int x, int y) {
		if (x >= 0 && x <= boardWidth-1 && y >= 0 && y <= boardHeight-1) {
			for (int z = 1; z < 6; z++) {
				if (mines[x][y] == z) {
					miss++;
					btn[x][y].setText(z + "");
					btn[x][y].setBackground(Color.GRAY);
					return;
				}
			}
			if (mines[x][y] == 0 && btn[x][y].getBackground() != Color.GRAY) {
				miss++;
				btn[x][y].setBackground(Color.GRAY);
				btn[x][y].removeActionListener(this);
				floodFill(x - 1, y);
				floodFill(x + 1, y);
				floodFill(x, y - 1);
				floodFill(x, y + 1);
			} else {
				return;
			}
		}
	}

	/**
	 * Displays the board if player has lost with a accompanying JDialog
	 * pre: none
	 * post: The board has been revealed and the JDialog has been displayed
	 */
	public void showBoard() {
		System.out.println("BOOOM");
		System.out.println("LOSE");
		JOptionPane.showMessageDialog(this, "Unfortunately, You Lose", "",JOptionPane.PLAIN_MESSAGE);
		for (int x = 0; x < boardHeight; x++) {
			for (int y = 0; y < boardWidth; y++) {
				btn[x][y].setBackground(Color.GRAY);
				btn[x][y].removeActionListener(this);
				if (mines[x][y] != NO_MINE) {
					if (mines[x][y] >= BOMB_VALUE)
						//            btn[x][y].setText("B");
						btn[x][y].setIcon(new ImageIcon("Bomb.png"));
					else
						btn[x][y].setText("" + mines[x][y]);
				}
			}
		}
		miss = 0;
	}

	/**
	 * Check's if the amount of buttons clicked are equal to the total buttons. If they are will open a WIN JDialog
	 * pre: none
	 * post: If player has won, JDialog has been opened
	 */
	public void checkWin() {
		if (miss == 100) {
			System.out.println("WIN");
			JOptionPane.showMessageDialog(this, "Congratulations, You Win!!","", JOptionPane.PLAIN_MESSAGE);
		}
	}

	/**
	 * Resets the buttons on the board.
	 * pre: none
	 * post: The board's buttons has been reset
	 */
	public void resetBoard(){
		miss = 0;
		getMines();
		for (int x = 0; x < boardHeight; x++) {
			for (int y = 0; y < boardWidth; y++) {
				btn[x][y].addActionListener(this);
				btn[x][y].setIcon(null);
				btn[x][y].setBackground(null);
			}
		}
	}


	public static class btnDif extends JButton implements ActionListener{

		public btnDif(String string) {
			super(string);
			addActionListener(this);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			int height = 0,width = 0, bombs = 0;
			if (e.getSource()==btnEasy){
				System.out.println("easy");
				height = 5;
				width = 5;
				bombs = (int) (5 * Math.random() + 1);
			}
			else if (e.getSource()==btnMed){
				System.out.println("med");
				height = 10;
				width = 10;
				bombs = (int) (10 - 5 + 1 * Math.random() + 5);
			}
			else if (e.getSource()==btnHard){
				System.out.println("hard");
				height = 15;
				width = 15;
				bombs = (int) (15 - 10 + 1 * Math.random() + 10);
			}

			boardHeight = height;
			boardWidth = width;
			bombAmount = bombs;
			board = new BoardBuild();
			d.dispose();
			
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (int) ((dimension.getWidth() - 450) / 2);
			int y = (int) ((dimension.getHeight() - 500) / 2);

			board.setLocation(x, y);
			board.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			board.add(p1, BorderLayout.NORTH);
			board.add(p2, BorderLayout.CENTER);
			board.add(p3, BorderLayout.SOUTH);
			board.setSize(450, 500);
			board.setVisible(true);
			board.setResizable(true);

		}

	}
	/**
	 * Adds the Top label, the 10x10 grid of Buttons and the reset button to the board
	 * pre: none
	 * post:  The board has been displayed in the middle of the screen.
	 */
	public static void main(String[] args) {
		final int WIDTH = 450;
		final int HEIGHT = 350;
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - WIDTH) / 2);
		int y = (int) ((dimension.getHeight() - HEIGHT) / 2);

		d.setLocation(x, y);
		d.setVisible(true);
		d.setSize(WIDTH, HEIGHT);
		d.setVisible(true);
		d.setResizable(true);

		d.setLayout(new GridLayout(1,3));
		d.add(btnEasy);
		d.add(btnMed);
		d.add(btnHard);
	}
}