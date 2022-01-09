package de.fantasyrealms.domain.cards.land

import de.fantasyrealms.domain.*

object Cavern : Card {
    override val id: Int = 2
    override val name: String = "Cavern"
    override val baseScore: Int = 6
    override val suit: Suit = Suit.LAND
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +25 with Dwarvish Infantry or Dragon; CLEARS the Penalty on all Weather.",
        EffectType.BONUS,
        EffectType.CLEAR
    )

    override fun getScoreInHand(hand: Hand): Int {
        TODO("Not yet implemented")
    }
}