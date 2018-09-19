/*
 * Jordan Chou
 * 100836466 
 * COMP3004
 * Sept. 20, 2018
 */

package blackjackgame;
import java.util.Scanner;

public class Game {
	static Hand player;
	static Hand dealer;
	static Deck deck;
	static Scanner sc;
	
	public static void main(String[] args) {
		player = new Hand();
		dealer = new Hand();
		sc = new Scanner(System.in);
		char input = 0;
		
		// Launch Game
		System.out.println(	"Welcome to BLACKJACK3004");
		System.out.println(	"------------------------");
		while (input != 'c' && input != 'f')
			input = promptInput();
		
		// If input is console
		if (input == 'c') {
			deck = new Deck();
			System.out.println();
			System.out.println("Dealing Cards...");
			for (int i = 0; i < 2; i++) {
				player.newCard(deck.drawCard());
				dealer.newCard(deck.drawCard());
			}
			System.out.println();
			System.out.println("Dealer's Hand: --");
			dealer.showHand(1);
			System.out.println("--");
			showPlayerHand();
			
			// Player's turn
			System.out.println();
			System.out.println("Your Turn!");
			while (player.handValue() < 21 && dealer.handValue() < 21) {
				input = 0;
				while (input != 'H' && input != 'h' && input != 'S' && input != 's')
					input = promptHitOrStand();
				
				// Player chose to Hit
				if (input == 'H' || input == 'h') {
					player.newCard(deck.drawCard());
					showPlayerHand();
					
					// Player Busts
					if (player.handValue() > 21) {
						break;
					}
				}
				
				// Player chose to Stand
				if (input == 'S' || input == 's') {
					showPlayerHand();
					break;
				}
			}
			
			// Dealer's Turn
			if (dealer.handValue() < 21 && player.handValue() < 21) {
				showDealerHand();
				// Dealer's score <= 16, or soft 17, or <= than Player's
				while ((dealer.handValue() <= 16 || (dealer.handValue() == 17 && dealer.hasAce())) && dealer.handValue() < player.handValue()) {
					System.out.println("Dealer Hits");
					dealer.newCard(deck.drawCard());
					showDealerHand();
				}
			}
			System.out.println("Dealer Stands");
			
			// Determining the Winner
			System.out.println();
			System.out.println();
			
			System.out.println("Final Hands");
			System.out.println("===========");
			showDealerHand();
			showPlayerHand();
			System.out.println();
			
			// Player has Blackjack
			if (player.handSize() == 2 && player.handValue() == 21) {
				System.out.println("You got a Blackjack!");
			}
			// Dealer has Blackjack
			if (dealer.handSize() == 2 && dealer.handValue() == 21) {
				System.out.println("Dealer got a Blackjack!");
				System.out.println("Dealer Wins!");
				System.exit(0);
			}
			// Player busted
			if (player.handValue() > 21) {
				System.out.println("You Busted!");
				System.out.println("Dealer Wins!");
				System.exit(0);
			}
			// Dealer busted
			if (dealer.handValue() > 21) {
				System.out.println("Dealer Busted!");
				System.out.println("You Win!");
				System.exit(0);
			}
			// Dealer's score is better
			if (dealer.handValue() >= player.handValue()) {
				System.out.println("Dealer Wins!");
				System.exit(0);
			}
			
			// Player's score is better
			System.out.println("You Win!");

		}
		
		sc.close();
		System.exit(0);
	}
	
	static char promptInput() {
		System.out.print("Select console (c) or file (f) input: ");
		char i = sc.next().charAt(0);
		return i;
	}
	
	static char promptHitOrStand() {
		System.out.print("Hit (H) or Stand (S): ");
		char i = sc.next().charAt(0);
		return i;
	}
	
	static void showDealerHand() {
		System.out.println();
		System.out.println("Dealer's Hand: " + dealer.handValue());
		dealer.showHand(dealer.handSize());
		System.out.println();
	}
	
	static void showPlayerHand() {
		System.out.println();
		System.out.println("Your Hand: " + player.handValue());
		player.showHand(player.handSize());
		System.out.println();
	}
}
