package de.fantasyrealms.domain.cards.flame

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.LIGHTNING
import de.fantasyrealms.domain.Card.RAINSTORM

private const val MODIFIER = 30

class Lightning : AbstractCard(LIGHTNING) {
    override val effectDefinition: EffectDefinition = EffectDefinition("BONUS: +30 with Rainstorm.",
        setOf(
            OneTimeCondition(this, EffectType.BONUS) {
                val rainstorm = it.firstOrNull { card -> card.id == RAINSTORM.id }
                if (rainstorm != null) {
                    listOf(ConditionMatch(rainstorm, MODIFIER))
                } else {
                    listOf(ConditionMiss(this))
                }
            }
        )
    )
}