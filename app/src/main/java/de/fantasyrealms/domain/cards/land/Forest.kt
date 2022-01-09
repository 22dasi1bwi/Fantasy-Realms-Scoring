package de.fantasyrealms.domain.cards.land

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.*

private const val MODIFIER = 12

class Forest : AbstractCard(FOREST) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition("BONUS: +12 for each Beast and Elven Archers.",
            setOf(
                ForEachCondition(this, EffectType.BONUS) {
                    it.filter { card -> card.suit == Suit.BEAST || card.id == ELVEN_ARCHERS.id }
                        .map { card -> ConditionMatch(card, MODIFIER) }
                }
            )
        )
}
