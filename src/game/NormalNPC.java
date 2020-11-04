package game;

import ch.aplu.jcardgame.*;


public class NormalNPC extends Player{

	private FilterFactory filterFactory = new FilterFactory();
	private String filterType;
	private String selectType;
	private IFilterStrategy filter;
	private Hand hand;

	public NormalNPC(String filterType, String selectType) {
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

		if (lead != null) {
			filter = filterFactory.filter(filterType, hand, lead, trump);
			Hand filteredhand = filter.filterHand();

			// TODO: get selected card from filtered hand
			return randomCard(filteredhand); //TODO: add filter and select
		}

		// first player randomly selects a card
		return randomCard(hand); //TODO: add seed
	}

	@Override
	public String getPlayerType() {
		return NORMAL_NPC;
	}
}
