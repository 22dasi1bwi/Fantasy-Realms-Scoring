package de.fantasyrealms.domain.cards.beast

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.EffectType
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.DRAGON
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.OneTimeCondition

private const val MODIFIER = -40

class Dragon : AbstractCard(DRAGON) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition(
            "PENALTY: $MODIFIER unless with at least one Wizard.",
            setOf(
                OneTimeCondition(EffectType.PENALTY) {
                    val wizard = it.firstOrNull { card -> card.suit == Suit.WIZARD }
                    if (wizard != null){
                        listOf()
                    }else {
                        // TODO this?
                        listOf(ConditionMatch(this, MODIFIER))
                    }

                })
        )
}
