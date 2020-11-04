package game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class AdvancedNPC extends Player{

	private FilterFactory filterFactory = new FilterFactory();
	private String filterType;
	private String selectType;
	private IFilterStrategy filter;
	private Hand hand;

	public AdvancedNPC(String filterType, String selectType) {
		this.filterType = filterType;
		this.selectType = selectType;
	}

	// TODO: remove
	public Card randomCard(Hand hand){
		int x = random.nextInt(hand.getNumberOfCards());
		return hand.get(x);
	}

	@Override
	public Card getSelected(Whist.Suit lead, Whist.Suit trump, Hand hand) {
		ISelectStrategy select = SelectFactory.chooseSelectStrategy(selectType);
		Hand filteredHand = hand;
		if (lead != null) {
			filter = filterFactory.filter(filterType, hand, lead, trump);

			filteredHand = filter.filterHand();
			// TODO: get selected card from filtered hand
			Card curHighest = select.makeSelectStrategy(filteredHand,hand);
			System.out.println(curHighest);
			Card cardToPlay = hand.getCard(((Whist.Suit)curHighest.getSuit()),((Whist.Rank)curHighest.getRank()));
			return cardToPlay;
			//TODO: add filter and select

		}

		// first player randomly selects a card
		return select.makeSelectStrategy(filteredHand,hand);
       //TODO: add seed
	}

	@Override
	public String getPlayerType() {
		return ADVANCED_NPC;
	}

}
