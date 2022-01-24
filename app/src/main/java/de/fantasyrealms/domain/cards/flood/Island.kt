package de.fantasyrealms.domain.cards.flood

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.ISLAND
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.ClearCondition
import de.fantasyrealms.domain.condition.ConditionMatch

class Island(targetChoice: AbstractCard) : AbstractCard(ISLAND, targetChoice) {

    init {
        require(targetChoice.suit == Suit.FLAME || targetChoice.suit == Suit.FLOOD) {
            "$name can only clear Penalty on ${Suit.FLAME} or ${Suit.FLOOD}"
        }
    }

    override val effectDefinition: EffectDefinition =
        EffectDefinition("CLEARS the Penalty on any one Flood or Flame.",
            setOf(
                ClearCondition {
                    it.filter { card -> card.id == targetChoice.id }.map(::ConditionMatch)
                }
            )
        )
}