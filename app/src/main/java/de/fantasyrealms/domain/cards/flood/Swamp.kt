package de.fantasyrealms.domain.cards.flood

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.SWAMP

private const val MODIFIER = -3

class Swamp : AbstractCard(SWAMP) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition("PENALTY: $MODIFIER for each Army and Flame.",
            setOf(
                ForEachCondition(this, EffectType.PENALTY) {
                    it.filter { card -> card.suit == Suit.ARMY || card.suit == Suit.FLAME }
                        .map { card -> ConditionMatch(card, MODIFIER) }
                }
            )
        )
}