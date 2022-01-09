package de.fantasyrealms.domain.cards.flame

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.*

class Wildfire : AbstractCard(WILDFIRE) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "PENALTY: BLANKS all cards except Flames, Weather, Wizards, Weapons, Artifacts, Great Flood, Island, Mountain, Unicorn, Dragon.",
        setOf(
            BlankCondition(this) {
                val matchingCards = it.filter { card ->
                    card.suit != Suit.FLAME &&
                            card.suit != Suit.WEATHER &&
                            card.suit != Suit.WIZARD &&
                            card.suit != Suit.WEAPON &&
                            card.suit != Suit.ARTIFACT &&
                            card.name != GREAT_FLOOD.cardName &&
                            card.name != ISLAND.cardName &&
                            card.name != MOUNTAIN.cardName &&
                            card.name != UNICORN.cardName &&
                            card.name != DRAGON.cardName
                }

                matchingCards.map(::ConditionMatch)
            }
        )
    )

    // TODO: test
    override fun getOtherAffectedCardSpecifications(): List<Card> {
        val exceptedByCard = Card.except(GREAT_FLOOD, ISLAND, MOUNTAIN, UNICORN, DRAGON)
        val exceptedBySuit =
            Card.except(Suit.FLAME, Suit.WEATHER, Suit.WIZARD, Suit.WEAPON, Suit.ARTIFACT)

        return exceptedByCard.intersect(exceptedBySuit).toList()
    }
}