package de.fantasyrealms.domain.cards.wizard

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.EffectType
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.OneTimeCondition

class Collector : AbstractCard(Card.COLLECTOR) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +10 if three different cards in same suit, +40 if four different cards, +100 if five different cards.",
        setOf(
            OneTimeCondition(EffectType.BONUS) {
                val amount = (listOf(this) + it).groupingBy { card -> card.suit }.eachCount().maxOf { amount -> amount.value }
                when {
                    amount < 3 -> listOf()
                    amount == 3 -> listOf(ConditionMatch(this, modifier = 10))
                    amount == 4 -> listOf(ConditionMatch(this, modifier = 40))
                    amount >= 5 -> listOf(ConditionMatch(this, modifier = 100))
                    else -> listOf()
                }
            }
        )
    )
}