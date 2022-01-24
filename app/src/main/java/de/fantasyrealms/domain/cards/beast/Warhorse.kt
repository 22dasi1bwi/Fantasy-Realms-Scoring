package de.fantasyrealms.domain.cards.beast

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.EffectType
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.WARHORSE
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.OneTimeCondition

private const val MODIFIER = 14

class Warhorse : AbstractCard(WARHORSE) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition(
            "BONUS: +$MODIFIER with any Leader or Wizard.",
            setOf(OneTimeCondition(EffectType.BONUS) {
                val anyLeaderOrWizard = it.firstOrNull { card ->
                    card.suit == Suit.LEADER || card.suit == Suit.WIZARD
                }
                if (anyLeaderOrWizard != null) {
                    listOf(ConditionMatch(anyLeaderOrWizard, MODIFIER))
                } else {
                    listOf()
                }
            }
            )
        )
}
