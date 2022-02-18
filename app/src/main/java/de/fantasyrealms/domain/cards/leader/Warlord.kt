package de.fantasyrealms.domain.cards.leader

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.BonusCondition
import de.fantasyrealms.domain.condition.ConditionMatch

class Warlord : AbstractCard(Card.WARLORD) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: Equal to the base strengths of all Armies in your hand.",
        setOf(
            BonusCondition {
                it
                    .filter { card -> card.suit == Suit.ARMY }
                    .map { card -> ConditionMatch(card, modifier = card.baseScore) }
            }
        )
    )
}