package de.fantasyrealms.domain.condition

import de.fantasyrealms.domain.EffectType
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.cards.ignore
import de.fantasyrealms.domain.event.ActionEvent
import de.fantasyrealms.domain.event.Event
import de.fantasyrealms.domain.event.EventLog
import de.fantasyrealms.domain.event.ForEachEvent

sealed class Condition(
    val type: EffectType,
    val effectAction: (List<AbstractCard>) -> List<ConditionMatch>,
) {

    val status = ConditionStatus()

    protected val disabledSuits = mutableListOf<Suit>()

    protected abstract fun apply(source: AbstractCard, targets: List<AbstractCard>): List<Event>

    fun blank() {
        status.blank()
    }

    fun clear() {
        status.clear()
    }

    fun disable(suit: Suit) {
        disabledSuits.add(suit)
    }

    fun apply(source: AbstractCard, targets: List<AbstractCard>, eventLog: EventLog): List<Event> {
        if (source.isBlanked()) {
            return emptyList()
        }
        val targetsWithoutSelf = targets.ignore(source)
        return if (status.isActive()) {
            apply(source, targetsWithoutSelf).onEach { eventLog.append(it) }
        } else {
            listOf()
        }
    }

    protected fun applyClearCondition(
        source: AbstractCard,
        target: AbstractCard,
        targetsAffectedTargets: List<AbstractCard>
    ): List<Event> {
        target.effectDefinition.conditions.forEach { it.clear() }

        return targetsAffectedTargets
            .filter { target.getOtherAffectedCardSpecifications().map(Card::id).contains(it.id) }
            .fold(mutableListOf(ActionEvent(target, source, type))) { acc, affectedTarget ->
                // TODO it's not really "cleared". Rather not affected by the cleared Card's penalties.
                affectedTarget.effectDefinition.conditions.forEach { it.clear() }
                acc.add(ActionEvent(affectedTarget, target, type))
                acc
            }
    }
}

class ConditionStatus {

    private var active: Boolean = true

    private var cleared: Boolean = false

    private var blanked: Boolean = false

    fun blank() {
        active = false
        blanked = true
    }

    fun clear() {
        cleared = true
        blanked = false
        active = true
    }

    fun isBlanked(): Boolean {
        return blanked
    }

    fun isCleared(): Boolean {
        return cleared
    }

    fun isActive(): Boolean {
        return active
    }
}

class BonusCondition(
    effectAction: (List<AbstractCard>) -> List<ConditionMatch>
) : Condition(EffectType.BONUS, effectAction) {

    override fun apply(source: AbstractCard, targets: List<AbstractCard>): List<Event> {
        val outcomes = effectAction.invoke(targets)
        return outcomes.map {
            ForEachEvent(source, it.target, type, it.modifier)
        }
    }
}

class PenaltyCondition(
    effectAction: (List<AbstractCard>) -> List<ConditionMatch>
) : Condition(EffectType.PENALTY, effectAction) {

    override fun apply(source: AbstractCard, targets: List<AbstractCard>): List<Event> {
        val outcomes = effectAction.invoke(targets)
        return outcomes.flatMap {
            if (disabledSuits.contains(it.target.suit)) {
                listOf()
            } else {
                listOf(ForEachEvent(source, it.target, type, it.modifier))
            }
        }
    }
}

class OneTimeCondition(
    effectType: EffectType,
    effectAction: (List<AbstractCard>) -> List<ConditionMatch>
) : Condition(effectType, effectAction) {

    override fun apply(source: AbstractCard, targets: List<AbstractCard>): List<Event> {
        val conditionMatch = effectAction.invoke(targets).firstOrNull()
        return conditionMatch?.let {
            listOf(ForEachEvent(source, it.target, type, it.modifier))
        } ?: listOf()
    }
}

class BlankCondition(
    effectAction: (List<AbstractCard>) -> List<ConditionMatch>
) : Condition(EffectType.BLANK, effectAction) {

    override fun apply(source: AbstractCard, targets: List<AbstractCard>): List<Event> {
        val outcomes = effectAction.invoke(targets)
        return outcomes.mapNotNull {
            val target = it.target
            if (!target.effectDefinition.conditions.all { it.status.isCleared() }) {
                target.effectDefinition.conditions.forEach { it.blank() }
                ActionEvent(target, source, type)
            } else {
                null
            }
        }
    }
}

class ClearCondition(
    effectAction: (List<AbstractCard>) -> List<ConditionMatch>
) : Condition(EffectType.CLEAR, effectAction) {

    override fun apply(source: AbstractCard, targets: List<AbstractCard>): List<Event> {
        val outcomes = effectAction.invoke(targets)
        return outcomes.flatMap {
            applyClearCondition(source, it.target, targets)
        }
    }
}

class PartClearCondition(
    val suit: Suit,
    effectAction: (List<AbstractCard>) -> List<ConditionMatch>
) : Condition(EffectType.CLEAR, effectAction) {

    override fun apply(source: AbstractCard, targets: List<AbstractCard>): List<Event> {
        val outcomes = effectAction.invoke(targets)
        return outcomes.flatMap {
            it.target.effectDefinition.conditions.map { condition ->
                condition.disable(suit)
                ActionEvent(it.target, source, type)
            }
        }
    }
}

class TransformCondition(
    effectAction: (List<AbstractCard>) -> List<ConditionMatch>
) : Condition(EffectType.TRANSFORM, effectAction) {

    override fun apply(source: AbstractCard, targets: List<AbstractCard>): List<Event> {
        val outcomes = effectAction.invoke(targets)
        return outcomes.map {
            ActionEvent(it.target, source, type)
        }
    }
}

class ConditionMatch(val target: AbstractCard, val modifier: Int) {
    constructor(target: AbstractCard) : this(target, 0)
}
