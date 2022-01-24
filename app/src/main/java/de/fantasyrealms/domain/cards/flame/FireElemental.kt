package de.fantasyrealms.domain.cards.flame

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.FIRE_ELEMENTAL
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.BonusCondition
import de.fantasyrealms.domain.condition.ConditionMatch

private const val MODIFIER = 15

class FireElemental : AbstractCard(FIRE_ELEMENTAL) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition("BONUS: +$MODIFIER for each other Flame.",
            setOf(
                BonusCondition {
                    it.filter { card -> card.suit == Suit.FLAME }
                        .map { card -> ConditionMatch(card, MODIFIER) }
                }
            )
        )
}