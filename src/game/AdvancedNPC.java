package game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class AdvancedNPC extends Player{

	private FilterFactory filterFactory = new FilterFactory();
	private SelectFactory selectFactory = new SelectFactory();
	private String filterType;
	private String selectType;
	private IFilterStrategy filter;

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
	public Card getSelected(Whist.Suit lead, Whist.Suit trump, Hand hand, Card winningCard) {
		ISelectStrategy select = selectFactory.chooseSelectStrategy(selectType);
		Hand filteredHand = hand;
		if (lead != null) {
			filter = filterFactory.filter(filterType, hand, lead, trump);

			filteredHand = filter.filterHand();
			// get selected card from filtered hand
			Card curSelected = select.makeSelectStrategy(filteredHand, hand, winningCard, trump);
			Card cardToPlay = hand.getCard(((Whist.Suit)curSelected.getSuit()),((Whist.Rank)curSelected.getRank()));
			return cardToPlay;
		}

		// first player randomly selects a card
		select = selectFactory.chooseSelectStrategy(selectFactory.RANDOM_SELECT);
		return select.makeSelectStrategy(filteredHand, hand, winningCard, trump);
       //TODO: add seed
	}

	@Override
	public String getPlayerType() {
		return ADVANCED_NPC;
	}

}
