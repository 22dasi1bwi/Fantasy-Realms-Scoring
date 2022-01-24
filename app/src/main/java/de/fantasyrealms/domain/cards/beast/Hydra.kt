package de.fantasyrealms.domain.cards.beast

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.EffectType
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.OneTimeCondition

private const val MODIFIER = 28

class Hydra : AbstractCard(Card.HYDRA){

    override val effectDefinition: EffectDefinition = EffectDefinition("BONUS: +$MODIFIER with Swamp.",
        setOf(
            OneTimeCondition(EffectType.BONUS) {
                val swamp = it.firstOrNull { card -> card.id == Card.SWAMP.id }

                if(swamp != null) {
                    listOf(ConditionMatch(this, MODIFIER))
                } else {
                    listOf()
                }
            }
        )
    )
}