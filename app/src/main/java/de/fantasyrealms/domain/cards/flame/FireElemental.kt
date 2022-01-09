package de.fantasyrealms.domain.cards.flame

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.FIRE_ELEMENTAL

private const val MODIFIER = 15

class FireElemental : AbstractCard(FIRE_ELEMENTAL) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition("BONUS: +$MODIFIER for each other Flame.",
            setOf(
                ForEachCondition(this, EffectType.BONUS) {
                    it.filter { card -> card.suit == Suit.FLAME }
                        .map { card -> ConditionMatch(card, MODIFIER) }
                }
            )
        )
}