package de.fantasyrealms.domain.cards.land

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.EARTH_ELEMENTAL
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.BonusCondition
import de.fantasyrealms.domain.condition.ConditionMatch

private const val MODIFIER = 15

class EarthElemental : AbstractCard(EARTH_ELEMENTAL) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition(
            "BONUS: +$MODIFIER for each other Land.",
            setOf(
                BonusCondition {
                    it.filter { card -> card.suit == Suit.LAND }
                        .map { card -> ConditionMatch(card, MODIFIER) }
                }
            )
        )
}
