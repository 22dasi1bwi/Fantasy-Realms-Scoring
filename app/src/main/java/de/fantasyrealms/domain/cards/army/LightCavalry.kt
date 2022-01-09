package de.fantasyrealms.domain.cards.army

import de.fantasyrealms.domain.*

object LightCavalry : Card {
    override val id: Int = 23
    override val name: String = "Light Cavalry"
    override val baseScore: Int = 17
    override val suit: Suit = Suit.ARMY
    override val effectDefinition: EffectDefinition =
        EffectDefinition(
            "PENALTY: -2 for each Land.",
            setOf(
                DefaultEffect(
                    EffectType.PENALTY,
                    Suit.LAND,
                    includeSelf = this to true,
                    modifier = -2
                )
            )
        )

    override fun getScoreInHand(hand: Hand): Int {
        val cardsTriggeringEffect = hand.searchBy(Suit.LAND)

        return baseScore - cardsTriggeringEffect.size * 2
    }
}