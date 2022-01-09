package de.fantasyrealms.domain.cards.weather

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.SMOKE

class Smoke : AbstractCard(SMOKE) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition(
            "PENALTY: This card is blanked unless with at least one Flame.",
            setOf(
               SelfBlankCondition(this)  {
                   val withNoFlame = it.none { card -> card.suit == Suit.FLAME }

                   if(withNoFlame) {
                       listOf(ConditionMatch(this))
                   } else {
                       listOf(ConditionMiss(this))
                   }
               }
            )
        )
}
