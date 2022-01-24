package de.fantasyrealms.domain.cards.weather

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.SMOKE
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.BlankCondition
import de.fantasyrealms.domain.condition.ConditionMatch

class Smoke : AbstractCard(SMOKE) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition(
            "PENALTY: This card is blanked unless with at least one Flame.",
            setOf(
               BlankCondition  {
                   val withNoFlame = it.none { card -> card.suit == Suit.FLAME }

                   if(withNoFlame) {
                       listOf(ConditionMatch(this))
                   } else {
                       listOf()
                   }
               }
            )
        )
}
