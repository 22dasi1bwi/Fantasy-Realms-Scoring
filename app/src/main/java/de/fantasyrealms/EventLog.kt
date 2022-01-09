package de.fantasyrealms

import de.fantasyrealms.domain.AbstractCard
import de.fantasyrealms.domain.Event

private const val START = "---------- EVENT LOG START ----------"
private const val TEMPLATE = "\n%s=\n\t%s"
private const val END = "\n---------- EVENT LOG END ----------\n"

class EventLog private constructor() {

    private val eventsByCard = mutableMapOf<AbstractCard, MutableList<Event>>()

    fun append(event: Event) {
        eventsByCard.getOrPut(event.effectReceiver) { mutableListOf() }.add(event)
    }

    override fun toString(): String {
        val log = eventsByCard
            .mapKeys { (card, events) ->
                TEMPLATE.format(card.name, events.map(Event::toString).filter(String::isNotBlank))
            }.keys
            .joinToString()
        return START + log + END
    }

    companion object {

        fun start(): EventLog {
            return EventLog()
        }
    }

}
