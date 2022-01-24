package de.fantasyrealms.domain.event

import de.fantasyrealms.domain.EffectType
import de.fantasyrealms.domain.cards.AbstractCard

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
            EffectType.TRANSFORM -> {
                "transformed by $effectIssuerName"
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
