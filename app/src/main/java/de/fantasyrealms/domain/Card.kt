package de.fantasyrealms.domain

interface Card {

    val id: Int

    val name: String

    val baseScore: Int

    val suit: Suit

    val effectDefinition: EffectDefinition

    fun getScoreInHand(hand: Hand): Int
}