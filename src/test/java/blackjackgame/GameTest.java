package blackjackgame;

import junit.framework.TestCase;
import java.io.File;

public class GameTest extends TestCase {
	
	// Check there are 52 cards
	public void testNumberOfCardsInDeckShouldReturn52() {
		Deck deck = new Deck();
		assertEquals(52, deck.numCards());
	}
	
	// test shuffling procedure
	public void testIfDeckIsShuffled() {
		Deck deck = new Deck();
		Deck deck2 = new Deck();
		deck.shuffle();
		
		assertFalse(deck.equals(deck2));
	}
	
	// check file input
	public void testFileInput() {
		File file = new File("test1.txt");
		
		assertTrue(file.exists());
	}
		
	// check console input
	public void testConsoleInput() {
		char input = 'a';
		while (input != 'c' && input != 'f') {
			input++;
		}
		
		assertEquals('c', input);
	}

	// offer choice
	
	//2 player cards visible
	public void test2PlayerCardsVisible() {
		Hand hand = new Hand();
		hand.newCard(new Card('C', '3'));
		hand.newCard(new Card('S', 'K'));
		
		assertEquals("C3 SK ", hand.showHand(2));
	}
	
	//1 dealer card visible
	public void test1DealerCardVisible() {
		Hand hand = new Hand();
		hand.newCard(new Card('C', '3'));
		hand.newCard(new Card('S', 'K'));
		
		assertEquals("C3 --", hand.showHand(1));
	}
	
	//player can hit
	public void testPlayerCanHit() {
		Card card = new Card('S', 'A');
		Hand hand = new Hand();
		hand.newCard(card);
		
		assertEquals(1, hand.handSize());
		assertEquals("SA ", hand.showHand(1));
	}
	
	//player can stand
	
	
}
