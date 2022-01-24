package de.fantasyrealms.domain.cards.land

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.EffectType
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.*
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.ClearCondition
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.OneTimeCondition

private const val MODIFIER = 25

class Cavern : AbstractCard(CAVERN) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +$MODIFIER with Dwarvish Infantry or Dragon; CLEARS the Penalty on all Weather.",
        setOf(
            OneTimeCondition(EffectType.BONUS) {
                val dwarvishInfantryOrDragon =
                    it.firstOrNull { card -> card.id == DWARVISH_INFANTRY.id || card.id == DRAGON.id }
                if (dwarvishInfantryOrDragon != null) {
                    listOf(ConditionMatch(dwarvishInfantryOrDragon, MODIFIER))
                } else {
                    listOf()
                }
            },
            ClearCondition {
                it.filter { card -> card.suit == Suit.WEATHER }.map(::ConditionMatch)
            }
        )
    )
}
