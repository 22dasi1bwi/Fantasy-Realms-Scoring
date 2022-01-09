package de.fantasyrealms.domain.cards.flood

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.WATER_ELEMENTAL

private const val MODIFIER = 15

class WaterElemental : AbstractCard(WATER_ELEMENTAL) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition("BONUS: +$MODIFIER for each other Flood.",
            setOf(
                ForEachCondition(this, EffectType.BONUS) {
                    it.filter { card -> card.suit == Suit.FLOOD }
                        .map { card -> ConditionMatch(card, MODIFIER) }
                }
            )
        )
}