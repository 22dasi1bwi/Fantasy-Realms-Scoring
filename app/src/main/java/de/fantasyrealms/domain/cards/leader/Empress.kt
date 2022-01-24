package de.fantasyrealms.domain.cards.leader

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card.EMPRESS
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.BonusCondition
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.PenaltyCondition

private const val BONUS_ARMY_MODIFIER = 10
private const val PENALTY_OTHER_LEADER_MODIFIER = -5

class Empress : AbstractCard(EMPRESS) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +$BONUS_ARMY_MODIFIER for each Army. PENALTY: $PENALTY_OTHER_LEADER_MODIFIER for each other Leader.",
        setOf(
            BonusCondition {
                it.filter { card -> card.suit == Suit.ARMY }
                    .map { card -> ConditionMatch(card, BONUS_ARMY_MODIFIER) }
            },
            PenaltyCondition {
                it.filter { card -> card.suit == Suit.LEADER }
                    .map { card -> ConditionMatch(card, PENALTY_OTHER_LEADER_MODIFIER) }
            }
        )
    )
}
