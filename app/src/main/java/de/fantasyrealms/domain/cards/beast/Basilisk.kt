package de.fantasyrealms.domain.cards.beast

import de.fantasyrealms.domain.*

object Basilisk : Card {
    override val id: Int = 37
    override val name: String = "Basilisk"
    override val baseScore: Int = 35
    override val suit: Suit = Suit.BEAST
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "PENALTY: Blanks all Armies, Leaders, and other Beasts.",
        setOf(
            DefaultEffect(EffectType.BLANK, Suit.ARMY, includeSelf = this to true),
            DefaultEffect(EffectType.BLANK, Suit.LEADER, includeSelf = this to true),
            DefaultEffect(EffectType.BLANK, Suit.BEAST, includeSelf = this to false)
        )
    )

    override fun getScoreInHand(hand: Hand): Int {
        val cardsTriggeringEffect =
            hand.searchBy(Suit.ARMY, Suit.LEADER, Suit.BEAST).filter { this != it }

        return baseScore
    }
}