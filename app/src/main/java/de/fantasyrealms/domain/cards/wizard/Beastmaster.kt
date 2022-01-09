package de.fantasyrealms.domain.cards.wizard

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.BEASTMASTER

private const val MODIFIER = 9

class Beastmaster : AbstractCard(BEASTMASTER) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +$MODIFIER for each Beast. CLEARS the Penalty on all Beasts.",
        setOf(
            ForEachCondition(this, EffectType.BONUS) {
                it.filter { card -> card.suit == Suit.BEAST }
                    .map { card -> ConditionMatch(card, MODIFIER) }
            },
            ClearCondition(this) {
                it.filter { card -> card.suit == Suit.BEAST }.map(::ConditionMatch)
            }
        )
    )
}
