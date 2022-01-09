package de.fantasyrealms.domain.cards.wizard

import de.fantasyrealms.domain.*

object Beastmaster : Card {
    override val id: Int = 27
    override val name: String = "Beastmaster"
    override val baseScore: Int = 9
    override val suit: Suit = Suit.WIZARD
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +9 for each Beast. CLEARS the Penalty on all Beasts.",
        setOf(
            DefaultEffect(EffectType.BONUS, Suit.BEAST, includeSelf = this to true, modifier = 9),
            DefaultEffect(EffectType.CLEAR, Suit.BEAST, includeSelf = this to true)
        )
    )

    override fun getScoreInHand(hand: Hand): Int {
        val cardsTriggeringEffect = hand.searchBy(Suit.BEAST)

        return baseScore + cardsTriggeringEffect.size * 9
    }
}