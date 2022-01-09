package de.fantasyrealms.domain.cards.land

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.*

private const val MODIFIER = 50

class Mountain : AbstractCard(MOUNTAIN) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +$MODIFIER with both Smoke and Wildfire; Clears the Penalty on all Floods",
        setOf(
            // TODO can't check it with this
            OneTimeCondition(this, EffectType.BONUS) {
                val withSmokeAndWildfire =
                    it.map(AbstractCard::id).containsAll(listOf(SMOKE.id, WILDFIRE.id))
                if (withSmokeAndWildfire) {
                    // TODO: this?
                    listOf(ConditionMatch(this, MODIFIER))
                } else {
                    // TODO: this?
                    listOf(ConditionMiss(this))
                }
            },
            ClearCondition(this) {
                it.filter { card -> card.suit == Suit.FLOOD }.map(::ConditionMatch)
            }
        )
    )
}