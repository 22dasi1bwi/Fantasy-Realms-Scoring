package de.fantasyrealms.domain.cards.flame

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.FORGE
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.BonusCondition
import de.fantasyrealms.domain.condition.ConditionMatch

private const val MODIFIER = 9

class Forge : AbstractCard(FORGE) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition("BONUS: +$MODIFIER for each Weapon and Artifact.", setOf(
            BonusCondition {
                it.filter { card -> card.suit == Suit.WEAPON || card.suit == Suit.ARTIFACT }
                    .map { card -> ConditionMatch(card, MODIFIER) }
            }
        ))
}
