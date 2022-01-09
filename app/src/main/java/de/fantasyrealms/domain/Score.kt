package de.fantasyrealms.domain

const val ZERO = 0

data class TotalScore(private val scores: List<Score>) {

    private val scoresByCard = scores.groupBy { it.card }

    val raw = scores.sumOf { it.raw }

    operator fun get(card: AbstractCard): Int {
        return scoresByCard[card]?.sumOf { it.raw } ?: ZERO
    }
}

data class Score(val card: AbstractCard, val raw: Int = ZERO)
