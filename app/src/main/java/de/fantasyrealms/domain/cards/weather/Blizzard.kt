package de.fantasyrealms.domain.cards.weather

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card
import de.fantasyrealms.domain.cards.Card.BLIZZARD
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.BlankCondition
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.PenaltyCondition

private const val MODIFIER = -5

class Blizzard : AbstractCard(BLIZZARD) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "PENALTY: Blanks all Floods, $MODIFIER for each Army, Leader, Beast, and Flame.",
        setOf(
            BlankCondition {
                it.filter { card -> card.suit == Suit.FLOOD }.map(::ConditionMatch)
            },
            PenaltyCondition {
                it.filter { card ->
                    card.suit == Suit.ARMY ||
                            card.suit == Suit.LEADER ||
                            card.suit == Suit.BEAST ||
                            card.suit == Suit.FLAME
                }.map { card -> ConditionMatch(card, MODIFIER)}
            }
        )
    )

    override fun getOtherAffectedCardSpecifications(): List<Card> {
        return Card.get(Suit.FLOOD)
    }
}