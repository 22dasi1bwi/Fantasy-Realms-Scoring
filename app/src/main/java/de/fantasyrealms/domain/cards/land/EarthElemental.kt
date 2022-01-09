package de.fantasyrealms.domain.cards.land

import de.fantasyrealms.domain.*

object EarthElemental : Card {
    override val id: Int = 5
    override val name: String = "Earth Elemental"
    override val baseScore: Int = 4
    override val suit: Suit = Suit.LAND
    override val effectDefinition: EffectDefinition =
        EffectDefinition("BONUS: +15 for each other Land.", EffectType.BONUS)

    override fun getScoreInHand(hand: Hand): Int {
        TODO("Not yet implemented")
    }
}