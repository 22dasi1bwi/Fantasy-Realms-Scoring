package de.fantasyrealms.domain.cards.army

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.RANGERS
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.*

private const val MODIFIER = 10

class Rangers : AbstractCard(RANGERS) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "Bonus: +$MODIFIER for each Land; Clears the word Army from Penalty section of all cards in hand.",
        setOf(
            BonusCondition {
                it.filter { card -> card.suit == Suit.LAND }
                    .map { card -> ConditionMatch(card, MODIFIER) }
            },
            PartClearCondition(Suit.ARMY) {
                it.flatMap { card ->
                    if (card.effectDefinition.conditions.any { it is PenaltyCondition }) {
                        listOf(ConditionMatch(card))
                    } else {
                        listOf()
                    }
                }
            }
        )
    )
}