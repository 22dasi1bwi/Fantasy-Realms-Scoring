package de.fantasyrealms.domain.cards.artifact

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.EffectType
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.OneTimeCondition

class GemOfOrder : AbstractCard(Card.GEM_OF_ORDER) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +10 for 3-card run, +30 for 4-card run, +60 for 5-card run, +100 for 6-card run, +150 for 7-card run.",
        setOf(
            OneTimeCondition(EffectType.BONUS) {
                val sortedByBaseScore = (listOf(baseScore) + it.map(AbstractCard::baseScore)).sorted()

                when(identifyLongestRun(sortedByBaseScore)) {
                    3 -> listOf(ConditionMatch(this, modifier = 10))
                    4 -> listOf(ConditionMatch(this, modifier = 30))
                    5 -> listOf(ConditionMatch(this, modifier = 60))
                    6 -> listOf(ConditionMatch(this, modifier = 100))
                    7 -> listOf(ConditionMatch(this, modifier = 150))
                    else -> listOf()
                }
            }
        )
    )

    private fun identifyLongestRun(sortedByBaseScore: List<Int>): Int {
        var currentRun = 1
        var longestRun = 1
        sortedByBaseScore.reduce { latestElement, currentElement ->
            val onRun = currentElement - latestElement == 1
            if (onRun) {
                currentRun++
                currentElement
            } else {
                if (currentRun > longestRun) {
                    longestRun = currentRun
                }
                currentRun = 1
                currentElement
            }
        }
        return maxOf(currentRun, longestRun)
    }
}