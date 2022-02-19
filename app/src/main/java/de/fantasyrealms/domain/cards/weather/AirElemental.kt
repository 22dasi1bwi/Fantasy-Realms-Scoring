package de.fantasyrealms.domain.cards.weather

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.BonusCondition
import de.fantasyrealms.domain.condition.ConditionMatch

private const val MODIFIER = 15

class AirElemental : AbstractCard(Card.AIR_ELEMENTAL) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +$MODIFIER for each other Weather.",
        setOf(
            BonusCondition {
                it.filter { card -> card.suit == Suit.WEATHER }
                    .map { card -> ConditionMatch(card, modifier = MODIFIER) }
            }
        )
    )
}