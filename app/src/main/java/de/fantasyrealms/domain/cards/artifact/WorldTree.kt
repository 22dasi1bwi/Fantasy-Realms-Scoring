package de.fantasyrealms.domain.cards.artifact

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.EffectType
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.OneTimeCondition

private const val MODIFIER = 50

class WorldTree : AbstractCard(Card.WORLD_TREE) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition("BONUS: +$MODIFIER if every active card in hand is a different suit.",
            setOf(
                OneTimeCondition(EffectType.BONUS) {
                    val countBySuit =
                        it.filterNot { card -> card.isBlanked() }
                            .groupingBy { card -> card.suit }
                            .eachCount()
                    if (countBySuit.values.all { occurrences -> occurrences == 1 }) {
                        listOf(ConditionMatch(this, modifier = MODIFIER))
                    } else {
                        listOf()
                    }
                }
            )
        )
}