package de.fantasyrealms.domain.cards.beast

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card
import de.fantasyrealms.domain.cards.Card.BASILISK
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.BlankCondition
import de.fantasyrealms.domain.condition.ConditionMatch

class Basilisk : AbstractCard(BASILISK) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "PENALTY: Blanks all Armies, Leaders, and other Beasts.",
        setOf(
            BlankCondition {
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
