package de.fantasyrealms.domain.cards.wild

import de.fantasyrealms.domain.AbstractCard
import de.fantasyrealms.domain.Card.DOPPELGAENGER
import de.fantasyrealms.domain.EffectDefinition

class Doppelgaenger : AbstractCard(DOPPELGAENGER) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "May duplicate the name, suit, base strength, and penalty but not bonus of any one other card in your hand",
        setOf()
    )
}