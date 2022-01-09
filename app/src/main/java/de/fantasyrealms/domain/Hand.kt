package de.fantasyrealms.domain


data class Hand(private val cards: Set<Card>) {

    init {
//        require(cards.size == 7)
    }

    fun getTotalScore(): Int {
        return cards.map(::getScore).sum()
    }

    fun getScore(card: Card): Int {
        return card.effectDefinition.effects.map { it.apply(this) }.sum() + card.baseScore
    }

    fun searchBy(vararg cards: Card): Set<Card> {
        return this.cards.filter { cards.contains(it) }.toSet()
    }

    fun searchBy(vararg suits: Suit): Set<Card> {
        return cards.filter { suits.contains(it.suit) }.toSet()
    }
}