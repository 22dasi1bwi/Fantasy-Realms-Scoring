package de.fantasyrealms.domain.cards.flame

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.EffectType
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.LIGHTNING
import de.fantasyrealms.domain.cards.Card.RAINSTORM
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.OneTimeCondition

private const val MODIFIER = 30

class Lightning : AbstractCard(LIGHTNING) {
    override val effectDefinition: EffectDefinition = EffectDefinition("BONUS: +30 with Rainstorm.",
        setOf(
            OneTimeCondition(EffectType.BONUS) {
                val rainstorm = it.firstOrNull { card -> card.id == RAINSTORM.id }
                if (rainstorm != null) {
                    listOf(ConditionMatch(rainstorm, MODIFIER))
                } else {
                    listOf()
                }
            }
        )
    )
}