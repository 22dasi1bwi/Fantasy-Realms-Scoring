package de.fantasyrealms.domain.cards.weapon

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.*

class Warship : AbstractCard(Card.WARSHIP) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "PENALTY: Blanked unless with at least one Flood; Clears the word Army from Penalty section of all Floods.",
        setOf(
            BlankCondition {
                val noFlood = it.none { card -> card.suit == Suit.FLOOD }
                if (noFlood) {
                    listOf(ConditionMatch(this))
                } else {
                    listOf()
                }
            },
            PartClearCondition(Suit.ARMY) {
                val toBeCleared = it.filter { card -> card.suit == Suit.FLOOD }
                toBeCleared.map { card -> ConditionMatch(card) }
            }
        )
    )
}