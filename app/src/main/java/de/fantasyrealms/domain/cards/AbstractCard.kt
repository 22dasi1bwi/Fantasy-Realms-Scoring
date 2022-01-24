package de.fantasyrealms.domain.cards

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.EffectType
import de.fantasyrealms.domain.Score
import de.fantasyrealms.domain.ZERO
import de.fantasyrealms.domain.cards.artifact.BookOfChanges
import de.fantasyrealms.domain.cards.wild.Doppelgaenger
import de.fantasyrealms.domain.cards.wild.Mirage
import de.fantasyrealms.domain.cards.wild.Shapeshifter
import de.fantasyrealms.domain.condition.*
import de.fantasyrealms.domain.event.Event

abstract class AbstractCard(private val card: Card, val targetChoice: AbstractCard? = null) :
    Comparable<AbstractCard> {

    var id = card.id

    var name = card.cardName

    var baseScore = card.baseScore

    var suit = card.suit

    abstract val effectDefinition: EffectDefinition

    fun isBlanked() : Boolean {
        return effectDefinition.conditions.all { it.status.isBlanked() }
    }

    fun isCleared() : Boolean {
        return effectDefinition.conditions.all { it.status.isCleared() }
    }

    fun getScore(events: List<Event>): Score {
        if (isBlanked()) {
            return Score(this, ZERO)
        }
        val sum = events.sumOf {
            // TODO this shouldn't be necessary here,
            // instead we need to make sure that the ordering of the cards(and thus event creation)
            // is correct
            // The goal is to just some up the events without any additional logic in here
            // EventLog is also printing something different (see WildfireUnitTest#doesNotBlankSpecificCards#Mountain)
            val modifier = it.modifier
            if (isCleared() && modifier < ZERO) {
                ZERO
            } else if (it.effectIssuer.isBlanked()) {
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

    /** Ensure that [AbstractCard]s are always ordered according to the official rules. **/
    override fun compareTo(other: AbstractCard): Int {
        if (this is Doppelgaenger) {
            return -1 // higher priority
        }
        if (other is Doppelgaenger) {
            return 1
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

        if (this.effectDefinition.conditions.any { it is ClearCondition || it is PartClearCondition } && other !is BookOfChanges) {
            return -1
        }

        if (this.effectDefinition.conditions.any { it is ClearCondition || it is PartClearCondition} && other.effectDefinition.conditions.none { it is ClearCondition || it is PartClearCondition }) {
            return -1
        }

        if (other.effectDefinition.conditions.any { it is ClearCondition || it is PartClearCondition} && this.effectDefinition.conditions.none { it is ClearCondition || it is PartClearCondition }) {
            return 1
        }

        if (this.targetChoice != null && other.targetChoice == null) {
            return -1
        }

        if (other.targetChoice != null && this.targetChoice == null) {
            return 1
        }

        // TODO improve!
        val map: List<Card> =
            this.effectDefinition.conditions.filter { it is PenaltyCondition || it is BlankCondition }
                .flatMap { getOtherAffectedCardSpecifications() }
        if (map.contains(other.card)) {
            return -1
        }

        val map2: List<Card> =
            other.effectDefinition.conditions.filter { it is PenaltyCondition || it is BlankCondition }
                .flatMap { other.getOtherAffectedCardSpecifications() }
        if (map2.contains(this.card)) {
            return 1
        }

        if (!this.isBlanked()) {
            return -1
        }

        return 0
    }
}

fun List<AbstractCard>.ignore(card: AbstractCard): List<AbstractCard> {
    return filterNot { it.name == card.name }
}