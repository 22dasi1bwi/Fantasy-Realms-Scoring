package de.fantasyrealms.domain.cards.artifact

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.PROTECTION_RUNE

class ProtectionRune : AbstractCard(PROTECTION_RUNE) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "Clears the Penalty sections on all cards in hand.",
        setOf(ClearCondition(this) {
            it.map(::ConditionMatch)
        })
    )
}