package de.fantasyrealms.domain.cards.weather

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.BLIZZARD

private const val MODIFIER = -5

class Blizzard : AbstractCard(BLIZZARD) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "PENALTY: Blanks all Floods, $MODIFIER for each Army, Leader, Beast, and Flame.",
        setOf(
            BlankCondition(this) {
                it.filter { card -> card.suit == Suit.FLOOD }.map(::ConditionMatch)
            },
            ForEachCondition(this, EffectType.PENALTY) {
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