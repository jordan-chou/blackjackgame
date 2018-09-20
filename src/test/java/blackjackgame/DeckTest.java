package blackjackgame;

import junit.framework.TestCase;

public class DeckTest extends TestCase {
	
	public void testNumberOfCardsInDeckShouldReturn52() {
		Deck deck = new Deck();
		assertEquals(52, deck.numCards());
	}
	
	public void testIfDeckIsShuffled() {
		Deck deck = new Deck();
		Deck deck2 = new Deck();
		deck.shuffle();
		
		assertFalse(deck.equals(deck2));
	}
		

}
