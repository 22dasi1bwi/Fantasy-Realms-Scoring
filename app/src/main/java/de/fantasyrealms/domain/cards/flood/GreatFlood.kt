package de.fantasyrealms.domain.cards.flood

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card
import de.fantasyrealms.domain.cards.Card.*
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.BlankCondition
import de.fantasyrealms.domain.condition.ConditionMatch

class GreatFlood : AbstractCard(GREAT_FLOOD) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "PENALTY: Blanks all Armies, all Land except Mountain, all Flames except Lightning.",
        setOf(
            BlankCondition {
                it.filter { card ->
                    card.suit == Suit.ARMY ||
                            (card.suit == Suit.LAND && card.name != MOUNTAIN.cardName) ||
                            (card.suit == Suit.FLAME && card.name != LIGHTNING.cardName)
                }.map(::ConditionMatch)
            }
        )
    )

    override fun getOtherAffectedCardSpecifications(): List<Card> {
        return Card.get(Suit.ARMY) +
                Card.get(Suit.LAND, except = MOUNTAIN) +
                Card.get(Suit.FLAME, except = LIGHTNING)
    }
}