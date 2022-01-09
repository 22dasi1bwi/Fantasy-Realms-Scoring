package de.fantasyrealms.domain.cards.weather

import de.fantasyrealms.domain.*
import de.fantasyrealms.domain.Card.LIGHTNING
import de.fantasyrealms.domain.Card.RAINSTORM

private const val MODIFIER = 10

class Rainstorm : AbstractCard(RAINSTORM) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition("BONUS: +$MODIFIER for each Flood; PENALTY: Blanks all Flames except Lightning",
            setOf(ForEachCondition(this, EffectType.BONUS) {
                it.filter { card -> card.suit == Suit.FLOOD }.map { card -> ConditionMatch(card, MODIFIER) }
            },
                BlankCondition(this) {
                    it.filter { card ->
                        card.suit == Suit.FLAME && card.id != LIGHTNING.id
                    }.map(::ConditionMatch)
                }
            )
        )

    override fun getOtherAffectedCardSpecifications(): List<Card> {
        return Card.get(Suit.FLAME, except = LIGHTNING)
    }
}