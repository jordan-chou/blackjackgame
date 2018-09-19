/*
 * Jordan Chou
 * 100836466 
 * COMP3004
 * Sept. 20, 2018
 */

package blackjackgame;

public class Card {
	// A = 1, J = 11, Q = 12, K = 13
	private int		value;	// value of card
	private char 	suit;	// suit of card (S, C, D, H)
	private String 	rank;	// rank of card
	
	public Card(int s, int v) {
		switch (s) {
		case 0:		suit = 'S';
					break;
		case 1:		suit = 'C';
					break;
		case 2:		suit = 'D';
					break;
		case 3:		suit = 'H';
					break;
		default:	break;
		}
		
		switch (v) {
		case 1: 	rank = "A";
					value = 11;
					break;
		case 11:	rank = "J";
					value = 10;
					break;
		case 12:	rank = "Q";
					value = 10;
					break;
		case 13:	rank = "K";
					value = 10;
					break;
		default:	rank = Integer.toString(v);
					value = v;
					break;
		}
	}
	
	// Represent card as SUITRANK (ex. C3, SK)
	public String toString() {
		return suit + rank;
	}

	// Getters
	public int	getValue()	{ return value; }
	public char	getSuit()	{ return suit; }
	
}
