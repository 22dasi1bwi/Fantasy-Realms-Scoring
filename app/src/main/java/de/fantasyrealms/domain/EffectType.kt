package de.fantasyrealms.domain

class EffectDefinition(
    val text: String,
    val effects: Set<Effect>
) {
    // TODO: remove as soon as all cards use the correct constructor
    // this is only for making it compile at the moment
    constructor(text: String, vararg effectTypes: EffectType) : this(text, setOf())
}

sealed class Effect(
    val effectType: EffectType,
    val suit: Suit,
    val includeSelf: Pair<Card, Boolean>,
    val modifier: Int = 0
) {
    abstract fun apply(hand: Hand): Int
    fun nullify(): Int {
        return 0
    }
}

class AtLeastEffect(
    effectType: EffectType,
    suit: Suit,
    includeSelf: Pair<Card, Boolean>,
    modifier: Int
) : Effect(effectType, suit, includeSelf, modifier) {
    override fun apply(hand: Hand): Int {
        val cards = if (includeSelf.second) {
            hand.searchBy(suit)
        } else {
            hand.searchBy(suit).filter { it != includeSelf.first }
        }
        val atLeastOneOfSameSuit = cards.any { it.suit == suit }
        return if (atLeastOneOfSameSuit) {
            cards.map {
                when (effectType) {
                    EffectType.BONUS -> {
                        modifier
                    }
                    EffectType.PENALTY -> {
                        0
                    }
                    else -> error("not yet implemented")
                }
            }.sum()
        } else {
            modifier
        }
    }
}

class DefaultEffect(
    effectType: EffectType,
    suit: Suit,
    includeSelf: Pair<Card, Boolean>,
    modifier: Int = 0
) : Effect(effectType, suit, includeSelf, modifier) {

    override fun apply(hand: Hand): Int {
        val cards = if (includeSelf.second) {
            hand.searchBy(suit)
        } else {
            hand.searchBy(suit).filter { it != includeSelf.first }
        }
        return cards.map { card ->
            when (effectType) {
                EffectType.BONUS -> {
                    modifier
                }
                EffectType.PENALTY -> {
                    modifier
                }
                EffectType.CLEAR -> {
                    card.effectDefinition.effects
                        .filter { it.effectType == EffectType.PENALTY }
                        .map { it.nullify() }.sum()
                }
                EffectType.BLANK -> {
                    card.effectDefinition.effects
                        .filter { it.effectType == EffectType.PENALTY }
                        .map { it.nullify() }.sum()
                }
                EffectType.UNBLANKABLE -> TODO()
            }
        }.sum()
    }
}

enum class EffectType(val order: Int) {

    BONUS(100),
    PENALTY(100),
    BLANK(2),
    CLEAR(3),
    UNBLANKABLE(1)
}