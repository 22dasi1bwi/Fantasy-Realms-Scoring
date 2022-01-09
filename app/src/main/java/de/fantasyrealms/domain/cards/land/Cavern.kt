package de.fantasyrealms.domain.cards.land

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.*

private const val MODIFIER = 25

class Cavern : AbstractCard(CAVERN) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +$MODIFIER with Dwarvish Infantry or Dragon; CLEARS the Penalty on all Weather.",
        setOf(
            OneTimeCondition(this, EffectType.BONUS) {
                val dwarvishInfantryOrDragon =
                    it.firstOrNull { card -> card.id == DWARVISH_INFANTRY.id || card.id == DRAGON.id }
                if (dwarvishInfantryOrDragon != null) {
                    listOf(ConditionMatch(dwarvishInfantryOrDragon, MODIFIER))
                } else {
                    listOf(ConditionMiss(this))
                }
            },
            ClearCondition(this) {
                it.filter { card -> card.suit == Suit.WEATHER }.map(::ConditionMatch)
            }
        )
    )
}
