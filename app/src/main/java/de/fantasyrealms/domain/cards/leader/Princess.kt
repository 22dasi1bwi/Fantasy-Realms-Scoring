package de.fantasyrealms.domain.cards.leader

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.PRINCESS
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.BonusCondition
import de.fantasyrealms.domain.condition.ConditionMatch

private const val MODIFIER = 8

class Princess : AbstractCard(PRINCESS) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition(
            "BONUS: +$MODIFIER for each Army, Wizard, and other Leader.",
            setOf(
                BonusCondition {
                    it.filter { card ->
                        card.suit == Suit.ARMY ||
                                card.suit == Suit.WIZARD ||
                                card.suit == Suit.LEADER
                    }.map { card -> ConditionMatch(card, MODIFIER) }
                }
            )
        )
}
