package de.fantasyrealms.domain.cards.beast

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.BASILISK

class Basilisk : AbstractCard(BASILISK) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "PENALTY: Blanks all Armies, Leaders, and other Beasts.",
        setOf(
            BlankCondition(this) {
                it.filter { card ->
                    card.suit == Suit.ARMY ||
                            card.suit == Suit.LEADER ||
                            card != this && card.suit == Suit.BEAST
                }.map(::ConditionMatch)
            }
        )
    )

    override fun getOtherAffectedCardSpecifications(): List<Card> {
        return Card.get(Suit.ARMY, Suit.LEADER, Suit.BEAST, except = BASILISK)
    }
}
