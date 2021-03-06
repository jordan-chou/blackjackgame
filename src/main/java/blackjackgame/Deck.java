/*
 * Jordan Chou
 * 100836466 
 * COMP3004
 * Sept. 20, 2018
 */

package blackjackgame;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> deck;	// create a deck as a List
									// index 0 is considered the top
	public Deck() {
		// Go through suits then numbers 1-3
		deck = new ArrayList<Card>();
		for (int i = 0; i < 4; i++)
			for (int j = 1; j <= 13; j++)
				deck.add(new Card(i,j));
	}
	
	// Shuffle the deck
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
	// Remove a card at the top of the deck
	public Card drawCard() {
		return deck.remove(0);
	}
	
	// Get number of cards in deck
	public int numCards() {
		return deck.size();
	}
	
	// Get card at index i
	public Card getCard(int i) {
		return deck.get(i);
	}
	
	// Check if equal
	public boolean equals(Deck deck) {
		if (numCards() != deck.numCards())
			return false;
		for (int i = 0; i < numCards(); i++) {
			if (!getCard(i).equals(deck.getCard(i))) {
				return false;
			}
		}
		return true;
	}
}
