//Phase 1: Create text based deck testing program. This would allow decks to be loaded, cards to be draw, cards to be played or discarded
//         and cards in play to be tapped (italicized), untapped, and discarded, and turns to pass
//
//Phase 2: Add visual support to allow mouse interaction and boxes for cards, tapped cards. Add graveyard, exile, stack, and library zones 
//         and allow cards to be dragged and dropped to different zones, snap placement for cards
//
//Phase 3: Add library manipulation, and graveyard viewing
//
//Phase 4: Create format for card pictures and add pictures to the game - loading pictures pregame and add a loading bar
//
//Phase 5: Add internet connection support
//
//Phase 6: Add chat capabilities
//
//Phase 7: Add notation abilities: +1/+1 and -1/-1 Counters, Other Counters, X-Spell amounts, Target names, Pass/Wait Signal etc
//
//Phase 8: Add target arrows when picking targets for a spell
//
//Phase 9: Add creature combat deaths/damage, and player damage

public class main {

	public static void main(String[] args) {
		GameManager gm = new GameManager();
		gm.runGame();

	}

}
