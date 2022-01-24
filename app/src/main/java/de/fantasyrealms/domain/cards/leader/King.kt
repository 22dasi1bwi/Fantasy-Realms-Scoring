package de.fantasyrealms.domain.cards.leader

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card
import de.fantasyrealms.domain.cards.Card.KING
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.BonusCondition
import de.fantasyrealms.domain.condition.ConditionMatch

private const val BONUS_ARMY_WITHOUT_QUEEN_MODIFIER = 5
private const val BONUS_ARMY_WITH_QUEEN_MODIFIER = 20

class King : AbstractCard(KING) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +$BONUS_ARMY_WITHOUT_QUEEN_MODIFIER for each Army, +$BONUS_ARMY_WITH_QUEEN_MODIFIER for each Army if in the same hand with Queen.",
        setOf(
            BonusCondition {
                val withQueen = it.firstOrNull { card -> card.id == Card.QUEEN.id }
                if (withQueen != null) {
                    it.filter { card -> card.suit == Suit.ARMY }
                        .map { card -> ConditionMatch(card, BONUS_ARMY_WITH_QUEEN_MODIFIER) }
                } else {
                    it.filter { card -> card.suit == Suit.ARMY }
                        .map { card -> ConditionMatch(card, BONUS_ARMY_WITHOUT_QUEEN_MODIFIER) }
                }
            }
        )
    )
}
