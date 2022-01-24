package de.fantasyrealms.domain.condition

import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.event.BaseEvent
import de.fantasyrealms.domain.event.Event
import de.fantasyrealms.domain.event.EventLog

class ConditionExecutor(
    private val source: AbstractCard,
    private val targets: List<AbstractCard>,
    private val eventLog: EventLog
) {

    fun applyConditions(): List<Event> {
        val events =
            source.effectDefinition.conditions.flatMap { it.apply(source, targets, eventLog) }

        return (listOf(BaseEvent(source)) + events)
    }
}