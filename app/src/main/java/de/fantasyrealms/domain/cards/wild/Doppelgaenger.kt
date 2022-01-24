package de.fantasyrealms.domain.cards.wild

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.DOPPELGAENGER
import de.fantasyrealms.domain.condition.PenaltyCondition

class Doppelgaenger private constructor() : AbstractCard(DOPPELGAENGER) {

    companion object {
        fun mimic(other: AbstractCard): Doppelgaenger {
            return Doppelgaenger().apply {
                val effects = other.effectDefinition.conditions
                    .filterIsInstance<PenaltyCondition>()
                    .toSet()

                name = other.name
                suit = other.suit
                baseScore = other.baseScore
                effectDefinition = EffectDefinition(this.effectDefinition.text, effects)
            }
        }
    }

    override var effectDefinition: EffectDefinition = EffectDefinition(
        "May duplicate the name, suit, base strength, and penalty but not bonus of any other card in your hand.",
        setOf()
    )
}