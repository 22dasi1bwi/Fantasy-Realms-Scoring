package de.fantasyrealms.domain.cards.leader

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.EMPRESS

private const val BONUS_ARMY_MODIFIER = 10
private const val PENALTY_OTHER_LEADER_MODIFIER = -5

class Empress : AbstractCard(EMPRESS) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +$BONUS_ARMY_MODIFIER for each Army. PENALTY: $PENALTY_OTHER_LEADER_MODIFIER for each other Leader.",
        setOf(
            ForEachCondition(this, EffectType.BONUS) {
                it.filter { card -> card.suit == Suit.ARMY }
                    .map { card -> ConditionMatch(card, BONUS_ARMY_MODIFIER) }
            },
            ForEachCondition(
                this,
                EffectType.PENALTY
            ) {
                it.filter { card -> card.suit == Suit.LEADER }
                    .map { card -> ConditionMatch(card, PENALTY_OTHER_LEADER_MODIFIER) }
            }
        )
    )
}
