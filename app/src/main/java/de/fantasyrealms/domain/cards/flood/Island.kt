package de.fantasyrealms.domain.cards.flood

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.ISLAND

class Island(targetChoice: AbstractCard) : AbstractCard(ISLAND, targetChoice) {

    init {
        require(targetChoice.suit == Suit.FLAME || targetChoice.suit == Suit.FLOOD) {
            "$name can only clear Penalty on ${Suit.FLAME} or ${Suit.FLOOD}"
        }
    }

    override val effectDefinition: EffectDefinition =
        EffectDefinition("CLEARS the Penalty on any one Flood or Flame.",
            setOf(
                ClearOnTargetCondition(this, targetChoice) {
                    it.map(::ConditionMatch)
                }
            )
        )
}