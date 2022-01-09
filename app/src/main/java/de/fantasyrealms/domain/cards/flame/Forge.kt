package de.fantasyrealms.domain.cards.flame

import de.fantasyrealms.domain.*

object Forge : Card {
    override val id: Int = 18
    override val name: String = "Forge"
    override val baseScore: Int = 9
    override val suit: Suit = Suit.FLAME
    override val effectDefinition: EffectDefinition =
        EffectDefinition("BONUS: +9 for each Weapon and Artifact.", EffectType.BONUS)

    override fun getScoreInHand(hand: Hand): Int {
        TODO("Not yet implemented")
    }
}