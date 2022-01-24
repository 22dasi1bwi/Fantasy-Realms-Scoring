package de.fantasyrealms.domain.cards.artifact

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.PROTECTION_RUNE
import de.fantasyrealms.domain.condition.ClearCondition
import de.fantasyrealms.domain.condition.ConditionMatch

class ProtectionRune : AbstractCard(PROTECTION_RUNE) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "Clears the Penalty sections on all cards in hand.",
        setOf(ClearCondition { it.map(::ConditionMatch) })
    )
}