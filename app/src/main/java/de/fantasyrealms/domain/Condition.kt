package de.fantasyrealms.domain

import de.fantasyrealms.EventLog

sealed class Condition(
    val source: AbstractCard,
    val effectType: EffectType,
    val effectAction: (List<AbstractCard>) -> List<Outcome>
) {
    fun apply(eventLog: EventLog, targets: List<AbstractCard>): List<Event> {
        if (source.blanked) {
            return emptyList()
        }
        val targetsWithoutSelf = targets.ignore(source)
        return apply(targetsWithoutSelf).onEach { eventLog.append(it) }
    }

    protected fun applyClearCondition(
        card: AbstractCard,
        otherAffectedTargets: List<AbstractCard>
    ): List<Event> {
        card.applyClearEffect()

        return otherAffectedTargets
            .filter { card.getOtherAffectedCardSpecifications().map(Card::id).contains(it.id) }
            .fold(mutableListOf(ActionEvent(card, source, effectType))) { acc, affectedTarget ->
                // TODO it's not really "cleared". Rather not affected by the cleared Card's penalties.
                affectedTarget.applyClearEffect()
                acc.add(ActionEvent(affectedTarget, card, effectType))
                acc
            }
    }

    protected abstract fun apply(targets: List<AbstractCard>): List<Event>
}

sealed class Outcome(val target: AbstractCard, val modifier: Int)
class ConditionMatch(target: AbstractCard, modifier: Int) : Outcome(target, modifier) {
    constructor(target: AbstractCard) : this(target, 0)
}
class ConditionMiss(target: AbstractCard, modifier: Int) : Outcome(target, modifier) {
    constructor(target: AbstractCard) : this(target, 0)
}

class ForEachCondition(
    source: AbstractCard,
    effectType: EffectType,
    effectAction: (List<AbstractCard>) -> List<Outcome>
) : Condition(source, effectType, effectAction) {

    override fun apply(targets: List<AbstractCard>): List<Event> {
        val outcomes = effectAction.invoke(targets)
        return outcomes.map {
            when (it) {
                is ConditionMatch -> ForEachEvent(source, it.target, effectType, it.modifier)
                is ConditionMiss -> NoopEvent(source, it.target)
            }
        }
    }
}

class UnlessWithCondition(
    source: AbstractCard,
    effectType: EffectType,
    effectAction: (List<AbstractCard>) -> List<Outcome>
) : Condition(source, effectType, effectAction) {

    override fun apply(targets: List<AbstractCard>): List<Event> {
        val outcomes = effectAction.invoke(targets)
        val effectActive = outcomes.all { it is ConditionMatch }
        return if (effectActive) {
            // TODO a new event might be necessary, telling the difference between the issuer and the one really causing the effect
            // TODO dirty hack: outcomes.first()
            listOf(ForEachEvent(source, source, effectType, outcomes.first().modifier))
        } else {
            listOf(NoopEvent(source, source))
        }
    }
}


class OneTimeCondition(
    source: AbstractCard,
    effectType: EffectType,
    effectAction: (List<AbstractCard>) -> List<Outcome>
) : Condition(source, effectType, effectAction) {

    override fun apply(targets: List<AbstractCard>): List<Event> {
        val outcome =
            effectAction.invoke(targets).firstOrNull { it is ConditionMatch }
        return outcome?.target?.let {
            listOf(ForEachEvent(source, outcome.target, effectType, outcome.modifier))
        } ?: listOf()
    }
}

class SelfBlankCondition(
    source: AbstractCard,
    effectAction: (List<AbstractCard>) -> List<Outcome>
) : Condition(source, EffectType.BLANK, effectAction) {

    override fun apply(targets: List<AbstractCard>): List<Event> {
        val effectActive = effectAction.invoke(targets).all { it is ConditionMatch }
        return if (effectActive) {
            source.applyBlankEffect()
            return listOf(ActionEvent(source, source, effectType))
        } else {
            listOf(NoopEvent(source, source))
        }
    }
}

class BlankCondition(
    source: AbstractCard,
    effectAction: (List<AbstractCard>) -> List<Outcome>
) : Condition(source, EffectType.BLANK, effectAction) {

    override fun apply(targets: List<AbstractCard>): List<Event> {
        val outcomes = effectAction.invoke(targets)
        return outcomes.map {
            val target = it.target
            if (!target.cleared && it is ConditionMatch) {
                target.applyBlankEffect()
                ActionEvent(target, source, effectType)
            } else {
                NoopEvent(source, target)
            }
        }
    }
}

class ClearCondition(
    source: AbstractCard,
    effectAction: (List<AbstractCard>) -> List<Outcome>
) : Condition(source, EffectType.CLEAR, effectAction) {

    override fun apply(targets: List<AbstractCard>): List<Event> {
        val outcomes = effectAction.invoke(targets)
        return outcomes.flatMap {
            when (it) {
                is ConditionMatch -> applyClearCondition(it.target, targets)
                is ConditionMiss -> listOf(NoopEvent(source, it.target))
            }
        }
    }
}


class ClearOnTargetCondition(
    source: AbstractCard,
    private val targetOfClearance: AbstractCard,
    effectAction: (List<AbstractCard>) -> List<Outcome>
) : Condition(source, EffectType.CLEAR, effectAction) {
    override fun apply(targets: List<AbstractCard>): List<Event> {
        val card: AbstractCard? = targets.firstOrNull { it.id == targetOfClearance.id }
        return if (card != null) {
            applyClearCondition(card, targets)
        } else {
            listOf()
        }
    }
}

sealed class Event(
    val effectReceiver: AbstractCard,
    val effectIssuer: AbstractCard,
    val effectType: EffectType,
    val modifier: Int = 0
) {
    override fun toString(): String {
        return ""
    }
}

class ActionEvent(
    effectReceiver: AbstractCard,
    effectIssuer: AbstractCard,
    effectType: EffectType
) : Event(effectReceiver, effectIssuer, effectType) {

    override fun toString(): String {
        val effectIssuerName = effectIssuer.name
        return when (effectType) {
            EffectType.BLANK -> {
                "blanked by $effectIssuerName"
            }
            EffectType.CLEAR -> {
                "cleared by $effectIssuerName"
            }
            else -> {
                // TODO: can we enforce that by the compiler?
                error("${this.javaClass.simpleName} should only called with BLANKS or CLEARS.")
            }
        }
    }
}

class ForEachEvent(
    effectReceiver: AbstractCard,
    effectIssuer: AbstractCard,
    effectType: EffectType,
    modifier: Int
) : Event(effectReceiver, effectIssuer, effectType, modifier) {
    override fun toString(): String {
        val effectIssuerName = effectIssuer.name
        return when (effectType) {
            EffectType.BONUS -> {
                "+${modifier} from $effectIssuerName"
            }
            EffectType.PENALTY -> {
                "$modifier from $effectIssuerName"
            }
            else -> {
                // TODO: can we enforce that by the compiler?
                error("${this.javaClass.simpleName} should only called with BONUSES or PENALTIES.")
            }
        }
    }
}

class BaseEvent(
    effectReceiver: AbstractCard,
) : Event(effectReceiver, effectReceiver, EffectType.NONE, effectReceiver.baseScore)

class NoopEvent(
    effectReceiver: AbstractCard,
    effectIssuer: AbstractCard,
) : Event(effectReceiver, effectIssuer, EffectType.NONE)
