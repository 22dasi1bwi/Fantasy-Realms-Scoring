package de.fantasyrealms.domain.cards.army

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.EffectType
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.KNIGHTS
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.OneTimeCondition

private const val MODIFIER = -8

class Knights : AbstractCard(KNIGHTS) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition(
            "PENALTY: $MODIFIER unless with at least one Leader.",
            setOf(
                OneTimeCondition(EffectType.PENALTY) {
                    val firstLeader = it.firstOrNull { card -> card.suit == Suit.LEADER }
                    if (firstLeader != null) {
                        listOf()
                    } else {
                        // TODO: this?
                        listOf(ConditionMatch(this, MODIFIER))
                    }
                }

            )
        )
}
