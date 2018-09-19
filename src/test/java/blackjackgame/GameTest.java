package blackjackgame;

import junit.framework.TestCase;

public class GameTest extends TestCase {
	public void test52Cards() {
		Deck deck = new Deck();
		assertEquals(52, deck.numCards());
	}
	
	
}
