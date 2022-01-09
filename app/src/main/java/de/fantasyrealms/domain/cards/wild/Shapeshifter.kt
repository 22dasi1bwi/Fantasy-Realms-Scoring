package de.fantasyrealms.domain.cards.wild

import de.fantasyrealms.domain.AbstractCard
import de.fantasyrealms.domain.Card.SHAPESHIFTER
import de.fantasyrealms.domain.EffectDefinition

class Shapeshifter : AbstractCard(SHAPESHIFTER) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "May take on the name and suit of any Artifact, Leader, Wizard, Weapon or Beast. Does not take bonus or penalty.",
        setOf()
    )
}