package game;

import javax.swing.*;

/**
 * @author David
 *
 */
public class Property extends Square{
	private int price;
	private int rent;
	private Player owner;
	private boolean mortgaged;
	private JLabel ownerToken;

	public Property(String n, int p, int r){
		name = n;
		price = p;
		rent = r;
		owner = null;
		mortgaged = false;
		ownerToken = null;
	}

	public String getName(){
		return name;
	}

	public int getPrice(){
		return price;
	}

	public int getRent(){
		return rent;
	}

	public Player getOwner(){
		return owner;
	}

	public JLabel getOwnerToken() {
		return ownerToken;
	}
  
	public void setOwner(Player p){
		owner=p;
		if(owner == null) {
			ownerToken = null;
		}
		else {
			ownerToken = new JLabel("P"+owner.getId());
		}
	}

	/**
	 * Performs the action on a property square. If the property is owned, this makes the player pay rent 
	 * on the property and is alerted. If the property is unowned, the player is prompted to purchase this property.
	 *
	 * @param	player		The player that this property is interacting with
	 * @return				Returns true if the "buy" button should be disabled
	 */
	public boolean act(Player player) {
		if(owner != null) {
			player.payRent(this);
			JOptionPane.showMessageDialog(null, player.getName()+ " pays $" + getRent() + "  to " + owner.getName());
			return true;
		}
		else {
			int choice = JOptionPane.showConfirmDialog(null, "Would you like to buy " + getName() + "?", "Buy property?", JOptionPane.YES_NO_OPTION);
			if(choice == JOptionPane.YES_OPTION) {
				player.buy(this);
				return true;
			}
			return false;
		}
	}

	public void setMortgaged(boolean _mortgaged) {
		mortgaged = _mortgaged;
	}

	public boolean getMortgaged() {
		return mortgaged;
	}
}