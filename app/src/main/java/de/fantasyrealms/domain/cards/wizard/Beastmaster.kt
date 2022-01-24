package de.fantasyrealms.domain.cards.wizard

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.BEASTMASTER
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.BonusCondition
import de.fantasyrealms.domain.condition.ClearCondition
import de.fantasyrealms.domain.condition.ConditionMatch

private const val MODIFIER = 9

class Beastmaster : AbstractCard(BEASTMASTER) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +$MODIFIER for each Beast. CLEARS the Penalty on all Beasts.",
        setOf(
            BonusCondition {
                it.filter { card -> card.suit == Suit.BEAST }
                    .map { card -> ConditionMatch(card, MODIFIER) }
            },
            ClearCondition {
                it.filter { card -> card.suit == Suit.BEAST }.map(::ConditionMatch)
            }
        )
    )
}
