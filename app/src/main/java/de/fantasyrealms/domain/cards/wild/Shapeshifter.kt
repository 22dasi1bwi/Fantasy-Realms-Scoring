package de.fantasyrealms.domain.cards.wild

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.SHAPESHIFTER
import de.fantasyrealms.domain.cards.Suit

private val supportedSuits = listOf(Suit.ARTIFACT, Suit.LEADER, Suit.WIZARD, Suit.WEAPON, Suit.BEAST)

class Shapeshifter(target: AbstractCard) : AbstractCard(SHAPESHIFTER, target) {

    init {
        if (!supportedSuits.contains(target.suit)) {
            throw IllegalArgumentException("${this.name} does nut support Suit ${target.suit}.")
        }
        this.name = target.name
        this.suit = target.suit
    }

    override val effectDefinition: EffectDefinition = EffectDefinition(
        "May take on the name and suit of any Artifact, Leader, Wizard, Weapon or Beast. Does not take bonus or penalty.",
        setOf()
    )
}