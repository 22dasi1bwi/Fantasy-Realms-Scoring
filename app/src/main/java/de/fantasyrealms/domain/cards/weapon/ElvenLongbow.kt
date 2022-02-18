package de.fantasyrealms.domain.cards.weapon

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.EffectType
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.OneTimeCondition

private const val MODIFIER = 30

private val cardsTriggeringEffect = setOf(Card.ELVEN_ARCHERS.id, Card.WARLORD.id, Card.BEASTMASTER.id)

class ElvenLongbow : AbstractCard(Card.ELVEN_LONGBOW) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +$MODIFIER with Elven Archers or Warlord or Beastmaster.",
        setOf(
            OneTimeCondition(EffectType.BONUS) {
                val cardTriggeringEffect = it.firstOrNull { card -> cardsTriggeringEffect.contains(card.id) }
                if (cardTriggeringEffect != null) {
                    listOf(ConditionMatch(cardTriggeringEffect, modifier = MODIFIER))
                } else {
                    listOf()
                }
            }
        )
    )
}