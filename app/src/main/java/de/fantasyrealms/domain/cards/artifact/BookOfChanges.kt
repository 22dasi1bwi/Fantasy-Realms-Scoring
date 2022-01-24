package de.fantasyrealms.domain.cards.artifact

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.BOOK_OF_CHANGES
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.TransformCondition

class BookOfChanges(other: AbstractCard, newSuit: Suit) : AbstractCard(BOOK_OF_CHANGES, other) {

    init {
        other.suit = newSuit
    }

    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: you may change the suit of one other card. Its name, bonuses and penalties remain the same.",
        setOf(
            TransformCondition {
                val card = it.firstOrNull { card -> card.id == other.id }
                if (card == null) {
                    listOf()
                } else {
                    card.suit = newSuit
                    listOf(ConditionMatch(card))
                }
            }
        )
    )
}