package de.fantasyrealms.domain

class EffectDefinition(
    val text: String,
    val effects: Set<Condition>
)
data class Effect(
    val type: EffectType,
    val modifier: Int = 0
)

enum class EffectType {
    BONUS,
    PENALTY,
    BLANK,
    CLEAR,
    NONE
}