package de.fantasyrealms.domain.cards.leader

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card
import de.fantasyrealms.domain.cards.Card.QUEEN
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.BonusCondition
import de.fantasyrealms.domain.condition.ConditionMatch

private const val BONUS_ARMY_WITHOUT_KING_MODIFIER = 5
private const val BONUS_ARMY_WITH_KING_MODIFIER = 20

class Queen : AbstractCard(QUEEN) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +$BONUS_ARMY_WITHOUT_KING_MODIFIER for each Army, +$BONUS_ARMY_WITH_KING_MODIFIER for each Army if in the same hand with King.",
        setOf(
            BonusCondition {
                val withKing = it.firstOrNull { card -> card.id == Card.KING.id }
                if (withKing != null) {
                    it.filter { card -> card.suit == Suit.ARMY }
                        .map { card -> ConditionMatch(card, BONUS_ARMY_WITH_KING_MODIFIER) }
                } else {
                    it.filter { card -> card.suit == Suit.ARMY }
                        .map { card -> ConditionMatch(card, BONUS_ARMY_WITHOUT_KING_MODIFIER) }
                }
            }
        )
    )
}