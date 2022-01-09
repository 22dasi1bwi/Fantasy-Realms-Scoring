package de.fantasyrealms.domain.cards.weapon

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.MAGIC_WAND

private const val MODIFIER = 25

class MagicWand : AbstractCard(MAGIC_WAND) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +$MODIFIER with at least one Wizard",
        setOf(OneTimeCondition(this, EffectType.BONUS) {
            val wizard = it.firstOrNull { card -> card.suit == Suit.WIZARD }
            if (wizard != null) {
                listOf(ConditionMatch(wizard, MODIFIER))
            } else {
                // TODO: this?
                listOf(ConditionMiss(this))
            }
        })
    )
}