package de.fantasyrealms.domain.cards.flood

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.EffectType
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.OneTimeCondition

private val allowedSuits = setOf(Suit.WEAPON, Suit.FLOOD, Suit.FLAME, Suit.LAND, Suit.WEATHER)

class FountainOfLife(choice: AbstractCard) : AbstractCard(Card.FOUNTAIN_OF_LIFE, choice) {

    init {
        require(allowedSuits.contains(choice.suit)) { "Target of ${this.javaClass.simpleName} needs to be one of the following Suits: $allowedSuits ." }
    }

    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: Add the base strength of any Weapon, Flood, Flame, Land, or Weather in your hand.",
        setOf(
            OneTimeCondition(EffectType.BONUS) {
                val chosenCard = it.firstOrNull { card -> card.id == choice.id }
                if (chosenCard != null) {
                    // TODO: is this a bonus or additional base score for FountainOfLife?
                    listOf(ConditionMatch(chosenCard, modifier = chosenCard.baseScore))
                } else {
                    listOf()
                }
            }
        )
    )
}