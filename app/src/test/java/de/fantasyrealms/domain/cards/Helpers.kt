package de.fantasyrealms.domain.cards

import de.fantasyrealms.domain.Card
import de.fantasyrealms.domain.Hand

fun handOf(vararg cards: Card): Hand {
    return Hand(cards.map { it }.toSet())
}
