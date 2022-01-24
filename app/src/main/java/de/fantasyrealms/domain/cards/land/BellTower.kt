package de.fantasyrealms.domain.cards.land

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.EffectType
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.OneTimeCondition

private const val MODIFIER = 15

class BellTower : AbstractCard(Card.BELL_TOWER) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition("BONUS: +$MODIFIER with any one Wizard.",
            setOf(
                OneTimeCondition(EffectType.BONUS) {
                    val wizard = it.firstOrNull { card -> card.suit == Suit.WIZARD }
                    if (wizard != null) {
                        listOf(ConditionMatch(wizard, MODIFIER))
                    } else {
                        listOf()
                    }
                }
            )
        )
}
