package de.fantasyrealms.domain.cards.army

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.LIGHT_CAVALRY
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.PenaltyCondition

private const val MODIFIER = -2

class LightCavalry : AbstractCard(LIGHT_CAVALRY) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition(
            "PENALTY: $MODIFIER for each Land.",
            setOf(
                PenaltyCondition {
                    val lands = it.filter { card -> card.suit == Suit.LAND }
                    lands.map { card -> ConditionMatch(card, MODIFIER) }
                }
            )
        )
}
