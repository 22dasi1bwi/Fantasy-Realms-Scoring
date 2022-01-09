package de.fantasyrealms.domain.cards.army

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.KNIGHTS

private const val MODIFIER = -8

class Knights : AbstractCard(KNIGHTS) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition(
            "PENALTY: $MODIFIER unless with at least one Leader.",
            setOf(
                UnlessWithCondition(this, EffectType.PENALTY) {
                    val firstLeader = it.firstOrNull { card -> card.suit == Suit.LEADER }
                    if (firstLeader != null) {
                        listOf(ConditionMiss(firstLeader, 0))
                    } else {
                        // TODO: this?
                        listOf(ConditionMatch(this, MODIFIER))
                    }
                }

            )
        )
}
