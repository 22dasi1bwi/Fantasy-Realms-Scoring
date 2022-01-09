package de.fantasyrealms.domain.cards.leader

import de.fantasyrealms.domain.*

object Empress : Card {
    override val id: Int = 35
    override val name: String = "Empress"
    override val baseScore: Int = 15
    override val suit: Suit = Suit.LEADER
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +10 for each Army. PENALTY: -5 for each other Leader.",
        setOf(
            DefaultEffect(EffectType.BONUS, Suit.ARMY, includeSelf = this to true, modifier = 10),
            DefaultEffect(EffectType.PENALTY, Suit.LEADER, includeSelf = this to false, modifier = -5)
        )
    )

    override fun getScoreInHand(hand: Hand): Int {
        val cardsTriggeringEffect = hand.searchBy(Suit.ARMY, Suit.LEADER).filter { this != it }

        val (armies, otherLeaders) = cardsTriggeringEffect.partition { it.suit == Suit.ARMY }

        return baseScore + armies.size * 10 - otherLeaders.size * 5
    }
}