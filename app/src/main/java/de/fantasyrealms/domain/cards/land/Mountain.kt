package de.fantasyrealms.domain.cards.land

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.EffectType
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.*
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.ClearCondition
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.OneTimeCondition

private const val MODIFIER = 50

class Mountain : AbstractCard(MOUNTAIN) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +$MODIFIER with both Smoke and Wildfire; Clears the Penalty on all Floods",
        setOf(
            OneTimeCondition(EffectType.BONUS) {
                val withSmokeAndWildfire =
                    it.map(AbstractCard::id).containsAll(listOf(SMOKE.id, WILDFIRE.id))
                if (withSmokeAndWildfire) {
                    // TODO: this?
                    listOf(ConditionMatch(this, MODIFIER))
                } else {
                    listOf()
                }
            },
            ClearCondition {
                it.filter { card -> card.suit == Suit.FLOOD }.map(::ConditionMatch)
            }
        )
    )
}