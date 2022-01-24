package de.fantasyrealms.domain.cards.artifact

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.EffectType
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.OneTimeCondition

private const val BONUS_WITHOUT_SWORD_OF_KETH_MODIFIER = 15
private const val BONUS_WITH_SWORD_OF_KETH_MODIFIER = 40

class ShieldOfKeth : AbstractCard(Card.SHIELD_OF_KETH) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +${BONUS_WITHOUT_SWORD_OF_KETH_MODIFIER} with any one Leader, +${BONUS_WITH_SWORD_OF_KETH_MODIFIER} with both Leader and Sword of Keth.",
        setOf(
            OneTimeCondition(EffectType.BONUS) {
                val swordOfKeth = it.firstOrNull { card -> card.id == Card.SWORD_OF_KETH.id }
                val anyLeader = it.any { card -> card.suit == Suit.LEADER }
                if (swordOfKeth != null && anyLeader) {
                    listOf(ConditionMatch(this, modifier = BONUS_WITH_SWORD_OF_KETH_MODIFIER))
                } else if (anyLeader) {
                    listOf(ConditionMatch(this, modifier = BONUS_WITHOUT_SWORD_OF_KETH_MODIFIER))
                } else {
                    listOf()
                }
            }
        )
    )
}