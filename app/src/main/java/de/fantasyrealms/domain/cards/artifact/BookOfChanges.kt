package de.fantasyrealms.domain.cards.artifact

import de.fantasyrealms.domain.AbstractCard
import de.fantasyrealms.domain.Card.BOOK_OF_CHANGES
import de.fantasyrealms.domain.EffectDefinition

class BookOfChanges : AbstractCard(BOOK_OF_CHANGES) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: you may change the suit of one other card. Its name, bonuses and penalties remain the same.",
        setOf()
    )
}