package de.fantasyrealms.domain.cards.weapon

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.EffectType
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.MAGIC_WAND
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.OneTimeCondition

private const val MODIFIER = 25

class MagicWand : AbstractCard(MAGIC_WAND) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +$MODIFIER with at least one Wizard",
        setOf(OneTimeCondition(EffectType.BONUS) {
            val wizard = it.firstOrNull { card -> card.suit == Suit.WIZARD }
            if (wizard != null) {
                listOf(ConditionMatch(wizard, MODIFIER))
            } else {
                // TODO: this?
                listOf()
            }
        })
    )
}