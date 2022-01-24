package de.fantasyrealms.domain.cards.land

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.ELVEN_ARCHERS
import de.fantasyrealms.domain.cards.Card.FOREST
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.BonusCondition
import de.fantasyrealms.domain.condition.ConditionMatch

private const val MODIFIER = 12

class Forest : AbstractCard(FOREST) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition("BONUS: +12 for each Beast and Elven Archers.",
            setOf(
                BonusCondition {
                    it.filter { card -> card.suit == Suit.BEAST || card.id == ELVEN_ARCHERS.id }
                        .map { card -> ConditionMatch(card, MODIFIER) }
                }
            )
        )
}
