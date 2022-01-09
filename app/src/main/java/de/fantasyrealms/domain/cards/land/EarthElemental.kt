package de.fantasyrealms.domain.cards.land

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.EARTH_ELEMENTAL

private const val MODIFIER = 15

class EarthElemental : AbstractCard(EARTH_ELEMENTAL) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition(
            "BONUS: +$MODIFIER for each other Land.",
            setOf(
                ForEachCondition(this, EffectType.BONUS) {
                    it.filter { card -> card.suit == Suit.LAND }
                        .map { card -> ConditionMatch(card, MODIFIER) }
                }
            )
        )
}
