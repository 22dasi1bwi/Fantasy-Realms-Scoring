package de.fantasyrealms.domain.cards.army

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.DWARVISH_INFANTRY
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.PenaltyCondition

private const val MODIFIER = -2

class DwarvishInfantry : AbstractCard(DWARVISH_INFANTRY) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition("PENALTY: $MODIFIER for each other Army", setOf(
            PenaltyCondition {
                it
                    .filter { card -> card.suit == Suit.ARMY }
                    .map { card -> ConditionMatch(card, MODIFIER) }
            }
        ))
}