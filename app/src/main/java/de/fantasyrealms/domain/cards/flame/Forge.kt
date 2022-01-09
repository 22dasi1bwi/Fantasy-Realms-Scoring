package de.fantasyrealms.domain.cards.flame

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.FORGE

private const val MODIFIER = 9

class Forge : AbstractCard(FORGE) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition("BONUS: +$MODIFIER for each Weapon and Artifact.", setOf(
            ForEachCondition(this, EffectType.BONUS) {
                it.filter { card -> card.suit == Suit.WEAPON || card.suit == Suit.ARTIFACT }
                    .map { card -> ConditionMatch(card, MODIFIER) }
            }
        ))
}
