package de.fantasyrealms.domain.cards.army

import de.fantasyrealms.domain.*

object Knights : Card {
    override val id: Int = 21
    override val name: String = "Knights"
    override val baseScore: Int = 20
    override val suit: Suit = Suit.ARMY
    override val effectDefinition: EffectDefinition =
        EffectDefinition(
            "PENALTY: -8 unless with at least one Leader.",
            setOf(
                AtLeastEffect(
                    EffectType.PENALTY,
                    Suit.LEADER,
                    includeSelf = this to true,
                    modifier = -8
                )
            )
        )

    override fun getScoreInHand(hand: Hand): Int {
        val cardsTriggeringEffect = hand.searchBy(Suit.LEADER)

        val anyLeaderCard = cardsTriggeringEffect.any { it.suit == Suit.LEADER }

        return if (anyLeaderCard) {
            baseScore
        } else {
            baseScore - 8
        }
    }
}