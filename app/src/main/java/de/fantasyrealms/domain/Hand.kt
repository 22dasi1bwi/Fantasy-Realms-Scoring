package de.fantasyrealms.domain

import de.fantasyrealms.EventLog

data class Hand(private val cards: Set<AbstractCard>) {

    val ordered: List<AbstractCard> = cards.sorted()

    fun getTotalScore(): TotalScore {
        val eventLog = EventLog.start()

        val scores = ordered
            .flatMap { applyEffects(eventLog, it) }
            .groupBy { it.effectReceiver }
            .map { (receiver, events) -> receiver.getScore(events) }
        println(eventLog)
        return TotalScore(scores)
    }

    private fun applyEffects(eventLog: EventLog, card: AbstractCard): List<Event> {
        return card.effectDefinition.effects.fold(mutableListOf<Event>(BaseEvent(card))) { acc, condition ->
            acc.addAll(condition.apply(eventLog, ordered))
            acc
        }
    }
}
