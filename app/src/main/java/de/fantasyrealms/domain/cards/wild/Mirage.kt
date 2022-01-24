package de.fantasyrealms.domain.cards.wild

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.MIRAGE
import de.fantasyrealms.domain.cards.Suit

private val supportedSuits = listOf(Suit.ARMY, Suit.LAND, Suit.WEATHER, Suit.FLOOD, Suit.FLAME)

class Mirage(target: AbstractCard) : AbstractCard(MIRAGE, targetChoice = target) {

    init {
        if (!supportedSuits.contains(target.suit)) {
            throw IllegalArgumentException("${this.name} does nut support Suit ${target.suit}.")
        }
        this.name = target.name
        this.suit = target.suit
    }

    override val effectDefinition: EffectDefinition = EffectDefinition(
        "May take on the name and suit of any Army, Land, Weather, Flood or Flame. Does not take bonus or penalty.",
        setOf()
    )
}