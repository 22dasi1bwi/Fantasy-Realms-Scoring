package de.fantasyrealms.domain.cards.wizard

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.PenaltyCondition

private const val MODIFIER = -10

class WarlockLord : AbstractCard(Card.WARLOCK_LORD) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "PENALTY: $MODIFIER for each Leader and other Wizard.",
        setOf(
            PenaltyCondition {
                it.filter { card -> card.suit == Suit.LEADER || card.suit == Suit.WIZARD }
                    .map { card -> ConditionMatch(card, modifier = MODIFIER) }
            }
        )
    )
}
