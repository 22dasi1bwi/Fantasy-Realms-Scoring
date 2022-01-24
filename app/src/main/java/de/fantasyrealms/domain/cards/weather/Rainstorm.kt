package de.fantasyrealms.domain.cards.weather

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card
import de.fantasyrealms.domain.cards.Card.LIGHTNING
import de.fantasyrealms.domain.cards.Card.RAINSTORM
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.BlankCondition
import de.fantasyrealms.domain.condition.BonusCondition
import de.fantasyrealms.domain.condition.ConditionMatch

private const val MODIFIER = 10

class Rainstorm : AbstractCard(RAINSTORM) {
    override val effectDefinition: EffectDefinition =
        EffectDefinition("BONUS: +$MODIFIER for each Flood; PENALTY: Blanks all Flames except Lightning",
            setOf(
                BonusCondition {
                it.filter { card -> card.suit == Suit.FLOOD }.map { card -> ConditionMatch(card, MODIFIER) }
            },
                BlankCondition {
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