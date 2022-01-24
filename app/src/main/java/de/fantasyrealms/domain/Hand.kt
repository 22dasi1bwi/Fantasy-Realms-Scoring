package de.fantasyrealms.domain

import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.ignore
import de.fantasyrealms.domain.condition.ConditionExecutor
import de.fantasyrealms.domain.event.Event
import de.fantasyrealms.domain.event.EventLog

data class Hand(private val cards: Set<AbstractCard>) {

    val ordered: List<AbstractCard> = cards.sorted()

    fun getTotalScore(): TotalScore {
        val eventLog = EventLog.start()

        val scores = ordered
            .flatMap { applyEffects(it, eventLog) }
            .groupBy { it.effectReceiver }
            .map { (receiver, events) -> receiver.getScore(events) }
        eventLog.end()
        return TotalScore(scores)
    }

    private fun applyEffects(handCard: AbstractCard, eventLog: EventLog): List<Event> {
        val executor = ConditionExecutor(handCard, targets = ordered.ignore(handCard), eventLog)
        return executor.applyConditions()
    }
}
