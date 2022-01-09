package de.fantasyrealms.domain.cards.army

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.ELVEN_ARCHERS

private const val MODIFIER = 5

class ElvenArchers : AbstractCard(ELVEN_ARCHERS) {
    override val effectDefinition: EffectDefinition = EffectDefinition("BONUS: +$MODIFIER if no Weather in hand.",
        setOf(
            OneTimeCondition(this, EffectType.BONUS) {
                val noWeatherInHand = it.none { card -> card.suit == Suit.WEATHER }

                if(noWeatherInHand) {
                    // TODO this?
                    listOf(ConditionMatch(this, MODIFIER))
                } else {
                    val weather = it.first { card -> card.suit == Suit.WEATHER }
                    listOf(ConditionMiss(weather, 0))
                }
            }
        )
    )
}