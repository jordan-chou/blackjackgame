/*
 * Jordan Chou
 * 100836466 
 * COMP3004
 * Sept. 20, 2018
 */

package blackjackgame;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Game {
	static Hand player;
	static Hand dealer;
	static Deck deck;
	static Scanner sc;
	static boolean endTurn;
	
	public static void main(String[] args) throws FileNotFoundException {
		player = new Hand();
		dealer = new Hand();
		sc = new Scanner(System.in);
		char input = 0;
		
		// Launch Game
		System.out.println(	"Welcome to BLACKJACK3004");
		System.out.println(	"------------------------");
		input = promptInput();
		
		// If input is file
		if (input == 'f') {
			endTurn = false;
			File file = new File("");
			while (!file.canExecute()) {
				System.out.print("Please enter the file name: ");
				String filename = sc.next();
				file = new File(filename);
			}
			
			int i = 0;
			
			
			sc = new Scanner(file);
			
			do {
				String thisInput = sc.next();
				
				System.out.println();
				System.out.println("Dealing Cards...");
				// First 4 inputs are cards
				// Deal to player
				while (i < 2) {
					player.newCard(new Card(thisInput.charAt(0), thisInput.charAt(1)));
					thisInput = nextToken(thisInput, sc);
					i++;
				}
				// Deal to dealer
				while (i < 4) {
					dealer.newCard(new Card(thisInput.charAt(0), thisInput.charAt(1)));
					thisInput = nextToken(thisInput, sc);
					i++;
				}
				
				showDealerHand();
				showPlayerHand();
				
				System.out.println();
				System.out.println("Player's Turn");
				while (player.handValue() < 21 && dealer.handValue() < 21 && !endTurn) {
					
					if (thisInput.charAt(0) == 'H' && thisInput.length() == 1) {
						System.out.println("Player Hits");
						thisInput = nextToken(thisInput, sc);
						playerHits(new Card(thisInput.charAt(0), thisInput.charAt(1)));
					}
					if (thisInput.charAt(0) == 'S' && thisInput.length() == 1) {
						System.out.println("Player Stands");
						playerStands();
						thisInput = nextToken(thisInput, sc);
					}
					thisInput = nextToken(thisInput, sc);
				}
				
				System.out.println();
				System.out.println("Dealer's Turn");
				if (dealer.handValue() < 21 && player.handValue() < 21) {
					System.out.println("Dealer Hits");
					dealCard(dealer, new Card(thisInput.charAt(0), thisInput.charAt(1)));
						
					if (dealer.handValue() > 16) {
						System.out.println("Dealer Stands");
						break;
					}
				}
				
			} while (sc.hasNext());
			determineWinner();
		}
		
		
		// If input is console
		if (input == 'c') {
			initGame();
			
			// Player's turn
			System.out.println();
			System.out.println("Your Turn!");
			while (player.handValue() < 21 && dealer.handValue() < 21 && !endTurn) {
				input = 0;
				while (input != 'H' && input != 'h' && input != 'S' && input != 's')
					input = promptHitOrStand();
				
				// Player chose to Hit
				if (input == 'H' || input == 'h') {
					playerHits(deck.drawCard());
				}
				
				// Player chose to Stand
				if (input == 'S' || input == 's') {
					playerStands();
				}
			}
			
			// Dealer's Turn
			if (dealer.handValue() < 21 && player.handValue() < 21) {
				showDealerHand();
				// Dealer's score <= 16, or soft 17, or <= than Player's
				while ((dealer.handValue() <= 16 || (dealer.handValue() == 17 && dealer.hasAce())) && dealer.handValue() < player.handValue()) {
					System.out.println("Dealer Hits");
					dealCard(dealer, deck.drawCard());
					showDealerHand();
				}
			}
			System.out.println("Dealer Stands");
			
			// Determining the Winner
			determineWinner();
		}
		
		sc.close();
		System.exit(0);
	}
	
	static String nextToken(String input, Scanner sc) {
		if (sc.hasNext()) {
			input = sc.next();
			return input;
		}
		return "";
	}
	
	static char promptInput() {
		char i = 0;
		while (i != 'c' && i != 'f') {
			System.out.print("Select console (c) or file (f) input: ");
			i = sc.next().charAt(0);
		}
		return i;
	}
	
	static void initGame() {
		deck = new Deck();
		deck.shuffle();
		endTurn = false;
		
		System.out.println();
		System.out.println("Dealing Cards...");
		dealCard(player, deck.drawCard());
		dealCard(player, deck.drawCard());
		dealCard(dealer, deck.drawCard());
		dealCard(dealer, deck.drawCard());
		System.out.println();
		System.out.println("Dealer's Hand: --");
		dealer.showHand(1);
		System.out.println("--");
		showPlayerHand();
		
		if (player.handValue() >= 21) endTurn = true;
	}
	
	static char promptHitOrStand() {
		char i = 0;
		while (i != 'H' && i != 'h' && i != 'S' && i != 's') {
			System.out.print("Hit (H) or Stand (S): ");
			i = sc.next().charAt(0);
		}
		return i;
	}
	
	static void playerHits(Card card) {
		dealCard(player, card);
		showPlayerHand();
	}
	
	static void playerStands() {
		showPlayerHand();
		endTurn = true;
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
	
	static void dealCard(Hand person, Card card) {
		person.newCard(card);
	}
	
	static void determineWinner() {
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
}
