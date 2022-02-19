package de.fantasyrealms.domain.cards.weapon

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.BlankCondition
import de.fantasyrealms.domain.condition.ConditionMatch

class WarDirigible : AbstractCard(Card.WAR_DIRIGIBLE) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "PENALTY: Blanked unless with at least one Army, Blanked if hand contains any weather.",
        setOf(
            BlankCondition {
                val noArmy = it.none { card -> card.suit == Suit.ARMY}
                if (noArmy) {
                    listOf(ConditionMatch(this))
                } else {
                    listOf()
                }
            },
            BlankCondition {
                val anyWeather = it.any { card -> card.suit == Suit.WEATHER }
                if (anyWeather) {
                    listOf(ConditionMatch(this))
                } else {
                    listOf()
                }
            }
        )
    )
}
