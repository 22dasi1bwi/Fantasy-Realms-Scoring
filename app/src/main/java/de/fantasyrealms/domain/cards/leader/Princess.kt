package de.fantasyrealms.domain.cards.leader

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.PRINCESS

private const val MODIFIER = 8

class Princess : AbstractCard(PRINCESS) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition(
            "BONUS: +$MODIFIER for each Army, Wizard, and other Leader.",
            setOf(
                ForEachCondition(this, EffectType.BONUS) {
                    it.filter { card ->
                        card.suit == Suit.ARMY ||
                                card.suit == Suit.WIZARD ||
                                card.suit == Suit.LEADER
                    }.map { card -> ConditionMatch(card, MODIFIER) }
                }
            )
        )
}
