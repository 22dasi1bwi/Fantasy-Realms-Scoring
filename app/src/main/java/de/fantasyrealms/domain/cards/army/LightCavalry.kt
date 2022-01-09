package de.fantasyrealms.domain.cards.army

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.LIGHT_CAVALRY

private const val MODIFIER = -2

class LightCavalry : AbstractCard(LIGHT_CAVALRY) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition(
            "PENALTY: $MODIFIER for each Land.",
            setOf(
                ForEachCondition(this, EffectType.PENALTY) {
                    val lands = it.filter { card -> card.suit == Suit.LAND }
                    lands.map { card -> ConditionMatch(card, MODIFIER) }
                }
            )
        )
}
