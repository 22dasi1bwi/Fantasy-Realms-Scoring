package de.fantasyrealms.domain.cards.beast

import de.fantasyrealms.domain.*

object Dragon : Card {
    override val id: Int = 39
    override val name: String = "Dragon"
    override val baseScore: Int = 30
    override val suit: Suit = Suit.BEAST
    override val effectDefinition: EffectDefinition =
        EffectDefinition("PENALTY: -40 unless with at least one Wizard.", EffectType.PENALTY)

    override fun getScoreInHand(hand: Hand): Int {
        return 0
    }
}