package de.fantasyrealms.domain.cards.flood

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.WATER_ELEMENTAL
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.BonusCondition
import de.fantasyrealms.domain.condition.ConditionMatch

private const val MODIFIER = 15

class WaterElemental : AbstractCard(WATER_ELEMENTAL) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition("BONUS: +$MODIFIER for each other Flood.",
            setOf(
                BonusCondition {
                    it.filter { card -> card.suit == Suit.FLOOD }
                        .map { card -> ConditionMatch(card, MODIFIER) }
                }
            )
        )
}