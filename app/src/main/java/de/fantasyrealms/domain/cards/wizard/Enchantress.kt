package de.fantasyrealms.domain.cards.wizard

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.ENCHANTRESS
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.BonusCondition
import de.fantasyrealms.domain.condition.ConditionMatch

private const val MODIFIER = 5

class Enchantress : AbstractCard(ENCHANTRESS) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +$MODIFIER for each Land, Weather, Flood, and Flame.",
        setOf(
            BonusCondition {
                it.filter { card ->
                    card.suit == Suit.LAND ||
                            card.suit == Suit.WEATHER ||
                            card.suit == Suit.FLOOD ||
                            card.suit == Suit.FLAME
                }.map { card -> ConditionMatch(card, MODIFIER) }
            }
        )
    )
}