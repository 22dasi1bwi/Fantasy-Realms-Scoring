package de.fantasyrealms.domain.cards.leader

import de.fantasyrealms.domain.*

object Princess : Card {
    override val id: Int = 33
    override val name: String = "Princess"
    override val baseScore: Int = 2
    override val suit: Suit = Suit.LEADER
    override val effectDefinition: EffectDefinition =
        EffectDefinition(
            "BONUS: +8 for each Army, Wizard, and other Leader.",
            setOf(
                DefaultEffect(EffectType.BONUS, Suit.ARMY, includeSelf = this to true, modifier = 8),
                DefaultEffect(EffectType.BONUS, Suit.WIZARD, includeSelf = this to true, modifier = 8),
                DefaultEffect(EffectType.BONUS, Suit.LEADER, includeSelf = this to false, modifier = 8)
            )
        )


    override fun getScoreInHand(hand: Hand): Int {
        val cardsTriggeringEffect =
            hand.searchBy(Suit.ARMY, Suit.WIZARD, Suit.LEADER).filter { this != it }

        return baseScore + cardsTriggeringEffect.size * 8
    }
}
