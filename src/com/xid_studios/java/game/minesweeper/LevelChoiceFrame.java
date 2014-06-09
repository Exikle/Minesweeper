/**
 * 
 */
package com.xid_studios.java.game.minesweeper;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @author Dixon D'Cunha
 * 
 */
@SuppressWarnings("serial")
public class LevelChoiceFrame extends JFrame implements ActionListener {
	JButton[] difficultyButton = new JButton[3];
	String[] buttonLabels = { "Easy", "Medium", "Hard" };
	final int EASY_HW = 7, MEDIUM_HW = 10, HARD_HW = 13;
	final int LV_CHOICE_HEIGHT = 300, LV_CHOICE_WIDTH = 300;

	public LevelChoiceFrame() {
		for (int x = 0; x < 3; x++) {
			difficultyButton[x] = new JButton(buttonLabels[x]);
			difficultyButton[x].addActionListener(this);
		}

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - LV_CHOICE_WIDTH) / 2);
		int y = (int) ((dimension.getHeight() - LV_CHOICE_HEIGHT) / 2);
		setLocation(x, y);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new GridLayout(3, 1));
		add(difficultyButton[0]);
		add(difficultyButton[1]);
		add(difficultyButton[2]);
		setSize(LV_CHOICE_WIDTH, LV_CHOICE_HEIGHT);
		setVisible(true);
		setResizable(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int height = 0, width = 0;
		BoardBuildFrame board = new BoardBuildFrame();
		if (e.getSource() == difficultyButton[0]) {
			height = EASY_HW;
			width = EASY_HW;
		}
		if (e.getSource() == difficultyButton[1]) {
			height = MEDIUM_HW;
			width = MEDIUM_HW;

		}
		if (e.getSource() == difficultyButton[2]) {
			height = HARD_HW;
			width = HARD_HW;
		}
		board.setHeightWidth(height, width);
	}
}
