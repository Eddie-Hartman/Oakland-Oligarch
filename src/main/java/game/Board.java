package game;

import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;

public class Board extends JPanel {

	Tile[] tiles;
	
	static final int NUMBER_OF_TILES=36; // currently actually correlates to number of tiles AND number of properties
	
	public Board(Player[] playerList, Property[] properties){
		//The buttons are just place holders for tiles right now. The layout itself is complicated 
		//current button size is 60X60 just for easy math
		this.setLayout(new GridBagLayout());
		tiles = new Tile[NUMBER_OF_TILES];
		
		for(int i = 0; i < NUMBER_OF_TILES; i++){ //property numbers are currently hard coded
			tiles[i] = new PropertyTile(i, properties[i]);
			//associate action listeners here
			tiles[i].setPreferredSize(new Dimension(60, 60));
		}
		
		for(Player p: playerList){ //adds players to the game
			tiles[0].addPlayer(p.getToken());
		}

		GridBagConstraints constraint = new GridBagConstraints();

		/*
		JButton centerSquare = new JButton("Center");
		constraint.gridx = 1;
		constraint.gridy = 1;
		constraint.gridheight = 8;
		constraint.gridwidth = 8;
		constraint.weightx = 0.8;
		constraint.weighty = 0.8;
		add(centerSquare, constraint);
		*/
		
		//r = button ROW
		//c = button COLLUMN
		//i = button number

		int i=0;
		constraint.weightx = 0.1;
		constraint.weighty = 0.1;
		constraint.fill = GridBagConstraints.BOTH;
		
		//Fills the buttons in one side at a time
		for(int t = 0; t < 10; t++){ //top loop
			constraint.gridx = t;
			constraint.gridy = 0;
			this.add(tiles[i],constraint);
			i++;
		}
		for(int r = 1; r < 10; r++){ //right loop
			constraint.gridx = 9;
			constraint.gridy = r;
			this.add(tiles[i],constraint);
			i++;
		}
		for(int b = 8; b >= 0; b--){ //bottom loop
			constraint.gridx = b;
			constraint.gridy = 9;
			this.add(tiles[i],constraint);
			i++;
		}
		for(int l = 8; l > 0; l--){ //left loop
			constraint.gridx = 0;
			constraint.gridy = l;
			this.add(tiles[i],constraint);
			i++;
		}
	}
}
