package de.fantasyrealms.domain.cards.flood

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card

class FountainOfLife(other: AbstractCard) : AbstractCard(Card.FOUNTAIN_OF_LIFE, other) {

    init {
        this.baseScore = baseScore + other.baseScore
    }
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: Add the base strength of any Weapon, Flood, Flame, Land, or Weather in your hand.",
        setOf()
    )
}
