package de.fantasyrealms.domain.cards.beast

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.WARHORSE

private const val MODIFIER = 14

class Warhorse : AbstractCard(WARHORSE) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition(
            "BONUS: +$MODIFIER with any Leader or Wizard.",
            setOf(OneTimeCondition(this, EffectType.BONUS) {
                val anyLeaderOrWizard = it.firstOrNull { card ->
                    card.suit == Suit.LEADER || card.suit == Suit.WIZARD
                }
                if (anyLeaderOrWizard != null) {
                    listOf(ConditionMatch(anyLeaderOrWizard, MODIFIER))
                } else {
                    listOf(ConditionMiss(this))
                }
            }
            )
        )
}
