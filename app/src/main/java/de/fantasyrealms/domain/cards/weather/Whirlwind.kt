package de.fantasyrealms.domain.cards.weather

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.EffectType
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.OneTimeCondition

private const val MODIFIER = 40

class Whirlwind : AbstractCard(Card.WHIRLWIND) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +$MODIFIER with Rainstorm and either Blizzard or Great Flood.",
        setOf(
            OneTimeCondition(EffectType.BONUS) {
                val rainstorm = it.firstOrNull { card -> card.id == Card.RAINSTORM.id }
                val blizzardOrGreatFlood =
                    it.firstOrNull { card -> card.id == Card.BLIZZARD.id || card.id == Card.GREAT_FLOOD.id }

                if (rainstorm != null && blizzardOrGreatFlood != null) {
                    // TODO would be cool if we could see the cards actually triggering the effect
                    // here we would need to pass multiple cards
                    listOf(ConditionMatch(this, modifier = MODIFIER))
                } else {
                    listOf()
                }
            }
        )
    )
}
