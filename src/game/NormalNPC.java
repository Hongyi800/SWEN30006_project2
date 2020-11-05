package game;

import ch.aplu.jcardgame.*;


public class NormalNPC extends Player{

	private FilterFactory filterFactory = new FilterFactory();
	private SelectFactory selectFactory = new SelectFactory();
	private String filterType;
	private String selectType;
	private IFilterStrategy filter;

	public NormalNPC(String filterType, String selectType) {
		this.filterType = filterType;
		this.selectType = selectType;
	}

	@Override
	public Card getSelected(Whist.Suit lead, Whist.Suit trump, Hand hand, Card winningCard, String seedProp) {
		ISelectStrategy select = selectFactory.chooseSelectStrategy(selectType);
		Hand filteredHand = hand;

		if (lead != null) {
			filter = filterFactory.filter(filterType, hand, lead, trump);

			filteredHand = filter.filterHand();
			// get selected card from filtered hand
			Card curSelected = select.makeSelectStrategy(filteredHand, hand, winningCard, trump, seedProp);
			Card cardToPlay = hand.getCard(((Whist.Suit)curSelected.getSuit()),((Whist.Rank)curSelected.getRank()));
			return cardToPlay;
		}

		// first player randomly selects a card
		select = selectFactory.chooseSelectStrategy(selectFactory.RANDOM_SELECT);
		return select.makeSelectStrategy(filteredHand, hand, winningCard, trump, seedProp);
	}

	@Override
	public String getPlayerType() {
		return NORMAL_NPC;
	}
}
