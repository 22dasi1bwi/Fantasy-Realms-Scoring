package de.fantasyrealms.domain.cards.weapon

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.EffectType
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.OneTimeCondition

private const val BONUS_WITHOUT_SHIELD_OF_KETH_MODIFIER = 10
private const val BONUS_WITH_SHIELD_OF_KETH_MODIFIER = 40

class SwordOfKeth : AbstractCard(Card.SWORD_OF_KETH) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +$BONUS_WITHOUT_SHIELD_OF_KETH_MODIFIER with any one Leader, +$BONUS_WITH_SHIELD_OF_KETH_MODIFIER with both Leader and Shield of Keth.",
        setOf(
            OneTimeCondition(EffectType.BONUS) {
                val shieldOfKeth = it.firstOrNull { card -> card.id == Card.SHIELD_OF_KETH.id }
                val anyLeader = it.any { card -> card.suit == Suit.LEADER }
                if (shieldOfKeth != null && anyLeader) {
                    listOf(ConditionMatch(this, modifier = BONUS_WITH_SHIELD_OF_KETH_MODIFIER))
                } else if (anyLeader) {
                    listOf(ConditionMatch(this, modifier = BONUS_WITHOUT_SHIELD_OF_KETH_MODIFIER))
                } else {
                    listOf()
                }
            }
        )
    )
}