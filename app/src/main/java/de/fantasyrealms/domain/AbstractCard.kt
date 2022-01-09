package de.fantasyrealms.domain

import de.fantasyrealms.domain.cards.artifact.BookOfChanges
import de.fantasyrealms.domain.cards.wild.Doppelgaenger
import de.fantasyrealms.domain.cards.wild.Mirage
import de.fantasyrealms.domain.cards.wild.Shapeshifter

abstract class AbstractCard(private val card: Card, val targetChoice: AbstractCard? = null) : Comparable<AbstractCard> {

    val id = card.id

    val name = card.cardName

    val baseScore = card.baseScore

    val suit = card.suit

    abstract val effectDefinition: EffectDefinition

    var blanked = false

    var cleared = false

    /** Ensure that [AbstractCard]s are always ordered according to the official rules. **/
    override fun compareTo(other: AbstractCard): Int {
        if (this is Doppelgaenger) {
            return -1 // higher priority
        }

        if (this is Mirage && other !is Doppelgaenger) {
            return -1
        }

        if (this is Shapeshifter && other !is Mirage) {
            return -1
        }

        if (this is BookOfChanges && other !is Shapeshifter) {
            return -1
        }

        if (this.effectDefinition.effects.any { it.effectType == EffectType.CLEAR } && other !is BookOfChanges) {
            return -1
        }

        if (this.targetChoice != null && other.targetChoice == null) {
            return -1
        }

        if (other.targetChoice != null && this.targetChoice == null) {
           return 1
        }

        // TODO improve!
        val map: List<Card> =
            this.effectDefinition.effects.filter { it.effectType == EffectType.PENALTY || it.effectType == EffectType.BLANK }
                .flatMap { it.source.getOtherAffectedCardSpecifications() }
        if (map.contains(other.card)) {
            return -1
        }

        val map2: List<Card> =
            other.effectDefinition.effects.filter { it.effectType == EffectType.PENALTY || it.effectType == EffectType.BLANK }
                .flatMap { it.source.getOtherAffectedCardSpecifications() }
        if (map2.contains(this.card)) {
            return 1
        }

        if (!this.blanked) {
            return -1
        }

        return 0
    }

    fun applyClearEffect() {
        blanked = false
        cleared = true
    }

    fun applyBlankEffect() {
        blanked = true
    }

    fun getScore(events: List<Event>): Score {
        if(blanked) {
            return Score(this, ZERO)
        }
        val sum = events.sumOf {
            // TODO this shouldn't be necessary here,
            // instead we need to make sure that the ordering of the cards(and thus event creation)
            // is correct
            // The goal is to just some up the events without any additional logic in here
            // EventLog is also printing something different (see WildfireUnitTest#doesNotBlankSpecificCards#Mountain)
            val modifier = it.modifier
            if (cleared && modifier < ZERO) {
                ZERO
            } else if (it.effectIssuer.blanked) {
                ZERO
            } else {
                modifier
            }
        }
        return Score(this, sum)
    }

    open fun getOtherAffectedCardSpecifications(): List<Card> {
        return listOf()
    }

    override fun toString(): String {
        return name
    }
}

fun List<AbstractCard>.ignore(card: AbstractCard): List<AbstractCard> {
    return filterNot { it == card }
}