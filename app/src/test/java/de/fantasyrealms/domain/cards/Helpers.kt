package de.fantasyrealms.domain.cards

import de.fantasyrealms.domain.AbstractCard
import de.fantasyrealms.domain.Hand

fun handOf(vararg cards: AbstractCard): Hand {
    return Hand(cards.map { it }.toSet())
}
