package de.fantasyrealms.domain.cards.beast

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.DRAGON

private const val MODIFIER = -40

class Dragon : AbstractCard(DRAGON) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition(
            "PENALTY: $MODIFIER unless with at least one Wizard.",
            setOf(
                UnlessWithCondition(this, EffectType.PENALTY) {
                    val wizard = it.firstOrNull { card -> card.suit == Suit.WIZARD }
                    if (wizard != null){
                        listOf(ConditionMiss(wizard))
                    }else {
                        // TODO this?
                        listOf(ConditionMatch(this, MODIFIER))
                    }

                })
        )
}
