package game;

import ch.aplu.jcardgame.*;
import ch.aplu.jgamegrid.*;

import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.util.*;

@SuppressWarnings("serial")
public class Whist extends CardGame {

	public enum Suit
	{
		SPADES, HEARTS, DIAMONDS, CLUBS
	}

	public enum Rank
	{
		// Reverse order of rank importance (see rankGreater() below)
		// Order of cards is tied to card images
		ACE, KING, QUEEN, JACK, TEN, NINE, EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO
	}

	final String trumpImage[] = {"bigspade.gif","bigheart.gif","bigdiamond.gif","bigclub.gif"};

	// return random Enum value
	public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
		int x = random.nextInt(clazz.getEnumConstants().length);
		return clazz.getEnumConstants()[x];
	}

	public boolean rankGreater(Card card1, Card card2) {
		return card1.getRankId() < card2.getRankId(); // Warning: Reverse rank order of cards (see comment on enum)
	}

	private final String version = "1.0";
	private final int handWidth = 400;
	private final int trickWidth = 40;
	private final Deck deck = new Deck(Suit.values(), Rank.values(), "cover");
	private final Location[] handLocations = {
			new Location(350, 625),
			new Location(75, 350),
			new Location(350, 75),
			new Location(625, 350)
	};
	private final Location[] scoreLocations = {
			new Location(575, 675),
			new Location(25, 575),
			new Location(575, 25),
			new Location(650, 575)
	};

	private Actor[] scoreActors = {null, null, null, null};
	private final Location trickLocation = new Location(350, 350);
	private final Location textLocation = new Location(350, 450);
	private final int thinkingTime = 2000;
	private Hand[] hands;
	private Location hideLocation = new Location(-500, - 500);
	private Location trumpsActorLocation = new Location(50, 50);
	private boolean enforceRules=false;

	private Player player;
	// switch properties here
	private PropertyReader propertyReader = new PropertyReader("smart.properties");
	private Properties properties = propertyReader.setUpProperties();
	private ArrayList<Player> players = propertyReader.getPlayers();

	// read basic information of properties
	public final int nbPlayers = Integer.parseInt(properties.getProperty("player_num"));
	public final int nbStartCards = Integer.parseInt(properties.getProperty("nbStartCards"));
	public final int winningScore = Integer.parseInt(properties.getProperty("winningScore"));
	public final String HUMAN = "interactive";


	public final String SEED_PROP = properties.getProperty("Seed");

	public static Random random;

	public void setRandom(){
		if(SEED_PROP == null) {
			// and no property
			// so randomise
			random = new Random();
		} else { // Use property seed
			int seed = Integer.parseInt(SEED_PROP);
			System.out.println("seed: "+seed);
			random = new Random(seed);
		}
	}

	public void setStatus(String string) {
		setStatusText(string);
	}

	private int[] scores = new int[nbPlayers];

	Font bigFont = new Font("Serif", Font.BOLD, 36);

	private void initScore() {
		for (int i = 0; i < nbPlayers; i++) {
			scores[i] = 0;
			scoreActors[i] = new TextActor("0", Color.WHITE, bgColor, bigFont);
			addActor(scoreActors[i], scoreLocations[i]);
		}
	}

	private void updateScore(int player) {
		removeActor(scoreActors[player]);
		scoreActors[player] = new TextActor(String.valueOf(scores[player]), Color.WHITE, bgColor, bigFont);
		addActor(scoreActors[player], scoreLocations[player]);
	}

	private Card selected;


	public static void toRand(Hand deck, int i){

		Card temp = deck.get(i);
		deck.remove(i,false);
		deck.insert(temp,false);
	}

	public static Hand shuffle(Hand deck) {
		int length = deck.getNumberOfCards();
		for ( int i = length; i > 0; i-- ){
			int randInd = random.nextInt(i);
			toRand(deck, randInd);
		}
		return deck;
	}


	private void initRound() {
		setRandom();
		Hand newDeck = shuffle(deck.toHand(false));
		hands = new Hand[nbPlayers+1];
		for(int p = 0; p < nbPlayers; ++p) {
			hands[p] = new Hand(deck);

			for(int k = 0; k < nbStartCards; ++k) {
				hands[p].insert(newDeck.get(p * nbStartCards + k), false);
			}
		}
		for (int i = 0; i < nbPlayers; i++) {
			hands[i].sort(Hand.SortType.SUITPRIORITY, true);
		}
		// Set up human player for interaction
		CardListener cardListener = new CardAdapter()  // Human Player plays card
		{
			public void leftDoubleClicked(Card card) { selected = card; hands[0].setTouchEnabled(false); }
		};
		hands[0].addCardListener(cardListener);
		// graphics
		RowLayout[] layouts = new RowLayout[nbPlayers];
		for (int i = 0; i < nbPlayers; i++) {
			layouts[i] = new RowLayout(handLocations[i], handWidth);
			layouts[i].setRotationAngle(90 * i);
			// layouts[i].setStepDelay(10);
			hands[i].setView(this, layouts[i]);
			hands[i].setTargetArea(new TargetArea(trickLocation));
			hands[i].draw();
		}

//	    for (int i = 1; i < nbPlayers; i++)  // This code can be used to visually hide the cards in a hand (make them face down)
//	      hands[i].setVerso(true);
		// End graphics
	}

	private Optional<Integer> playRound() {  // Returns winner, if any
		// Select and display trump suit
		final Suit trumps = randomEnum(Suit.class); // TODO: add seed
		final Actor trumpsActor = new Actor("sprites/"+ trumpImage[trumps.ordinal()]);
		addActor(trumpsActor, trumpsActorLocation);
		// End trump suit
		Hand trick;
		int winner;
		Card winningCard = null;
		Suit lead;

		//TODO: add seed
		int nextPlayer = random.nextInt(nbPlayers); // randomly select player to lead for this round

		for (int i = 0; i < nbStartCards; i++) {
			player = players.get(nextPlayer);
			trick = new Hand(deck);
			selected = null;

			// Select lead depending on player type
			if (player.getPlayerType().equals(HUMAN)) {  // human player
				hands[nextPlayer].setTouchEnabled(true);
				player.addToHand(hands[nextPlayer]);
				setStatus("Player " + nextPlayer + "double-click on card to lead.");
				while (null == selected) delay(100);
			} else {
				player.addToHand(hands[nextPlayer]);
				setStatusText("Player " + nextPlayer + " thinking...");
				delay(thinkingTime);

				// select the card after selection and filter
				selected = player.getSelected(null, trumps, hands[nextPlayer], winningCard);
			}

			// Lead with selected card
			trick.setView(this, new RowLayout(trickLocation, (trick.getNumberOfCards()+2)*trickWidth));
			trick.draw();
			selected.setVerso(false);
			// No restrictions on the card being lead
			lead = (Suit) selected.getSuit();
			selected.transfer(trick, true); // transfer to trick (includes graphic effect)
			winner = nextPlayer;
			winningCard = selected;
			System.out.println("New trick: Lead Player = "+nextPlayer+", Lead suit = "+selected.getSuit()+", Trump suit = "+trumps);
			System.out.println("Player "+nextPlayer+" play: "+selected.toString()+" from [" + player.printHand(hands[nextPlayer].getCardList())+"]");
			// End Lead
			for (int j = 1; j < nbPlayers; j++) {
				if (++nextPlayer >= nbPlayers) nextPlayer = 0;  // From last back to first
				selected = null;
				player = players.get(nextPlayer);

				// Select lead depending on player type
				if (player.getPlayerType().equals(HUMAN)) {  // human player
					hands[nextPlayer].setTouchEnabled(true);
					player.addToHand(hands[nextPlayer]);
					setStatus("Player " + nextPlayer + "double-click on card to follow.");
					while (null == selected) delay(100);
				} else {
					player.addToHand(hands[nextPlayer]);
					setStatusText("Player " + nextPlayer + " thinking...");
					delay(thinkingTime);
					selected = player.getSelected(lead, trumps, hands[nextPlayer], winningCard);
				}

				// Follow with selected card
				trick.setView(this, new RowLayout(trickLocation, (trick.getNumberOfCards()+2)*trickWidth));
				trick.draw();
				selected.setVerso(false);  // In case it is upside down
				// Check: Following card must follow suit if possible
				if (selected.getSuit() != lead && hands[nextPlayer].getNumberOfCardsWithSuit(lead) > 0) {
					// Rule violation
					String violation = "Follow rule broken by player " + nextPlayer + " attempting to play " + selected;
					//System.out.println(violation);
					if (enforceRules)
						try {
							throw(new BrokeRuleException(violation));
						} catch (BrokeRuleException e) {
							e.printStackTrace();
							System.out.println("A cheating player spoiled the game!");
							System.exit(0);
						}
				}
				// End Check
				selected.transfer(trick, true); // transfer to trick (includes graphic effect)

				System.out.println("Winning card: "+winningCard.toString());
				System.out.println("Player "+nextPlayer+" play: "+selected.toString()+" from ["+ player.printHand(hands[nextPlayer].getCardList())+"]");
				if ( // beat current winner with higher card
						(selected.getSuit() == winningCard.getSuit() && rankGreater(selected, winningCard)) ||
								// trumped when non-trump was winning
								(selected.getSuit() == trumps && winningCard.getSuit() != trumps)) {
					winner = nextPlayer;
					winningCard = selected;
				}
				// End Follow
			}
			delay(600);
			trick.setView(this, new RowLayout(hideLocation, 0));
			trick.draw();
			nextPlayer = winner;
			System.out.println("Winner: "+winner);
			setStatusText("Player " + nextPlayer + " wins trick.");
			scores[nextPlayer]++;
			updateScore(nextPlayer);
			if (winningScore == scores[nextPlayer]) return Optional.of(nextPlayer);
		}
		removeActor(trumpsActor);
		return Optional.empty();
	}

	public Whist() throws IOException {
		super(700, 700, 30);
		setTitle("Whist (V" + version + ") Constructed for UofM SWEN30006 with JGameGrid (www.aplu.ch)");
		setStatusText("Initializing...");
		initScore();
		Optional<Integer> winner;
		do {
			initRound();
			winner = playRound();
		} while (!winner.isPresent());
		addActor(new Actor("sprites/gameover.gif"), textLocation);
		setStatusText("Game over. Winner is player: " + winner.get());
		refresh();
	}

	public static void main(String[] args) throws IOException {
		new Whist();
	}

}