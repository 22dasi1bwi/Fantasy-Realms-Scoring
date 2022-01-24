package de.fantasyrealms.domain.cards.army

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.EffectType
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.ELVEN_ARCHERS
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.OneTimeCondition

private const val MODIFIER = 5

class ElvenArchers : AbstractCard(ELVEN_ARCHERS) {
    override val effectDefinition: EffectDefinition = EffectDefinition("BONUS: +$MODIFIER if no Weather in hand.",
        setOf(
            OneTimeCondition(EffectType.BONUS) {
                val noWeatherInHand = it.none { card -> card.suit == Suit.WEATHER }

                if(noWeatherInHand) {
                    // TODO this?
                    listOf(ConditionMatch(this, MODIFIER))
                } else {
                    listOf()
                }
            }
        )
    )
}