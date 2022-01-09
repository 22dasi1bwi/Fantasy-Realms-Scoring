package de.fantasyrealms.domain.cards.army

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.DWARVISH_INFANTRY

private const val MODIFIER = -2

class DwarvishInfantry : AbstractCard(DWARVISH_INFANTRY) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition("PENALTY: $MODIFIER for each other Army", setOf(
            ForEachCondition(this, EffectType.PENALTY) {
                it
                    .filter { card -> card.suit == Suit.ARMY }
                    .map { card -> ConditionMatch(card, MODIFIER) }
            }
        ))
}