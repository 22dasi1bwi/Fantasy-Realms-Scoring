package de.fantasyrealms.domain.cards.wild

import de.fantasyrealms.domain.AbstractCard
import de.fantasyrealms.domain.Card.MIRAGE
import de.fantasyrealms.domain.EffectDefinition

class Mirage : AbstractCard(MIRAGE) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "May take on the name and suit of any Army, Land, Weather, Flood or Flame. Does not take bonus or penalty.",
        setOf()
    )
}