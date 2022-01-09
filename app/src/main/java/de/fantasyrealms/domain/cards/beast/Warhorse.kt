package de.fantasyrealms.domain.cards.beast

import de.fantasyrealms.domain.*

object Warhorse : Card {
    override val id: Int = 38
    override val name: String = "Warhorse"
    override val baseScore: Int = 6
    override val suit: Suit = Suit.BEAST
    override val effectDefinition: EffectDefinition =
        EffectDefinition("BONUS: +14 with any Leader or Wizard.", EffectType.BONUS)

    override fun getScoreInHand(hand: Hand): Int {
        TODO("Not yet implemented")
    }
}