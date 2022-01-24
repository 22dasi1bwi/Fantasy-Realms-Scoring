package de.fantasyrealms.domain.cards.flame

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.EffectType
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.condition.ConditionMatch
import de.fantasyrealms.domain.condition.OneTimeCondition

private const val MODIFIER = 100

class Candle : AbstractCard(Card.CANDLE) {

    override val effectDefinition: EffectDefinition =
        EffectDefinition("BONUS: +$MODIFIER with Book Of Changes, Bell Tower, and any one Wizard.",
            setOf(
                OneTimeCondition(EffectType.BONUS) {
                    val cardIds = it.map(AbstractCard::id)
                    val withBookOfChangesAndBellTower =
                        cardIds.containsAll(listOf(Card.BOOK_OF_CHANGES.id, Card.BELL_TOWER.id))
                    val withAnyWizard = it.any { card -> card.suit == Suit.WIZARD }
                    if (withBookOfChangesAndBellTower && withAnyWizard) {
                        // TODO: triggered by multiple cards and not "this"
                        listOf(ConditionMatch(this, MODIFIER))
                    } else {
                        listOf()
                    }
                }
            )
        )
}