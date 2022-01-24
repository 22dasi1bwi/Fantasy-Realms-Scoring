package de.fantasyrealms.domain

import de.fantasyrealms.domain.condition.Condition

class EffectDefinition(
    val text: String,
    val conditions: Set<Condition>
)

enum class EffectType {
    BONUS,
    PENALTY,
    BLANK,
    CLEAR,
    TRANSFORM,
    NONE
}