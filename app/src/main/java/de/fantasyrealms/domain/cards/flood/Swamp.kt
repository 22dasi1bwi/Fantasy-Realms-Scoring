package de.fantasyrealms.domain.cards.flood

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.SWAMP
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.PenaltyCondition

private const val MODIFIER = -3

class Swamp : AbstractCard(SWAMP) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition("PENALTY: $MODIFIER for each Army and Flame.",
            setOf(
                PenaltyCondition {
                    it.filter { card -> card.suit == Suit.ARMY || card.suit == Suit.FLAME }
                        .map { card -> ConditionMatch(card, MODIFIER) }
                }
            )
        )
}