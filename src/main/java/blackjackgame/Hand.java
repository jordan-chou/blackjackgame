/*
 * Jordan Chou
 * 100836466 
 * COMP3004
 * Sept. 20, 2018
 */

package blackjackgame;
import java.util.ArrayList;

public class Hand {
	private int value;
	private ArrayList<Card> hand;
	private boolean hasAce;

	public Hand() {
		hasAce = false;
		value = 0;
		hand = new ArrayList<Card>();
	}
	
	public Hand(Card c) {
		hasAce = false;
		hand = new ArrayList<Card>();
		value = 0;
		newCard(c);
	}
	
	
	// Add a card to the hand
	public void newCard(Card c) {
		hand.add(c);
		value += c.getValue();
		if (c.getValue() == 11)
			hasAce = true;
	}
	
	
	// Return the hand as String (C3 SK ...)
	public void showHand(int numCards) {
		for (int i = 0; i < numCards; i++) {
			System.out.print(hand.get(i).toString() + " ");
		}
	}
	
	// Getters
	public int handSize()	{ return hand.size(); }
	public boolean hasAce()	{ return hasAce; }
	public int handValue() 	{
		if (hasAce && value > 21)	// Handle ace if Hand is over 21
			value -= 10;
		return value; 
	}
	
}

