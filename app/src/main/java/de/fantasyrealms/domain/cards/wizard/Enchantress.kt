package de.fantasyrealms.domain.cards.wizard

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.ENCHANTRESS

private const val MODIFIER = 5

class Enchantress : AbstractCard(ENCHANTRESS) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +$MODIFIER for each Land, Weather, Flood, and Flame.",
        setOf(
            ForEachCondition(this, EffectType.BONUS) {
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