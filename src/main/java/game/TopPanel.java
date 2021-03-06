package game;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Dan Gilmour
 */
public class TopPanel extends JPanel {


	JLabel clock = new JLabel();
	int loadTime = 0;
	Time time;
	JButton saveButton;

	/**
	 * The constructor for the TopPanel UI element
	 *
	 * @param	random					A psuedo-random number generator used to select the background color
	 * @param	t						The time object used to store the duration of this game
	 * @param	loadListener			Triggers loading a new game on click
	 * @param	saveListener			Triggers saving the game on click
	 * @param	instructionListener		Triggers instruction pop-up
	 * @param	actionSquareListener	Triggers action square info pop-up
	 */
	public TopPanel(Random random, Time t, ActionListener loadListener, ActionListener saveListener, ActionListener instructionListener, ActionListener actionSquareListener) {
		time = t;
		setBackground(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
		setOpaque(true);
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.weightx = 0.25;

		constraints.gridx = 0;
		saveButton = new JButton("Save Game");
		saveButton.addActionListener(saveListener);
		add(saveButton, constraints);

		constraints.gridx = 1;
		JButton loadButton = new JButton("Load Game");
		loadButton.addActionListener(loadListener);
		add(loadButton, constraints);

		constraints.gridx = 2;
		JButton actionSquareButton = new JButton("Actions");
		actionSquareButton.addActionListener(actionSquareListener);
		add(actionSquareButton, constraints);

		constraints.gridx = 3;
		JButton instructionsButton = new JButton("Instructions");
		instructionsButton.addActionListener(instructionListener);
		add(instructionsButton, constraints);

		constraints.gridx = 4;
		clock.setBackground(Color.WHITE);
		clock.setOpaque(true);
		Thread clockThread = new Thread(() -> {
			time.start();
			while(true){
				clock.setText(time.toString());
				time.update();
			}
		});
		clockThread.start();
		add(clock, constraints);

		constraints.gridx = 5;
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ExitListener());
		add(exitButton, constraints);
	}

	private class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int wantToLoad = JOptionPane.showConfirmDialog(null, "Would you like to save the game?", "Save on Exit?", JOptionPane.YES_NO_OPTION);
			if(wantToLoad == JOptionPane.YES_OPTION) {
				saveButton.setEnabled(true);
				saveButton.doClick();
			}
			System.exit(0);
		}
	}
}
