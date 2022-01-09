package de.fantasyrealms.domain.cards.beast

import de.fantasyrealms.domain.*

private const val WITH_PRINCESS_MODIFIER = 30
private const val WITH_EMPRESS_OR_QUEEN_OR_ENCHANTRESS_MODIFIER = 15

class Unicorn : AbstractCard(Card.UNICORN) {
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: +$WITH_PRINCESS_MODIFIER with Princess, " +
                "+$WITH_EMPRESS_OR_QUEEN_OR_ENCHANTRESS_MODIFIER with Empress, Queen, or Enchantress",
        setOf(
            OneTimeCondition(this, EffectType.BONUS) {
                val princess = it.firstOrNull { card -> card.id == Card.PRINCESS.id }
                if (princess != null) {
                    return@OneTimeCondition listOf(ConditionMatch(princess, WITH_PRINCESS_MODIFIER))
                }

                val empressOrQueenOrEnchantress =
                    it.firstOrNull { card -> card.id == Card.EMPRESS.id || card.id == Card.QUEEN.id || card.id == Card.ENCHANTRESS.id }
                if (empressOrQueenOrEnchantress != null) {
                    return@OneTimeCondition listOf(
                        ConditionMatch(
                            empressOrQueenOrEnchantress,
                            WITH_EMPRESS_OR_QUEEN_OR_ENCHANTRESS_MODIFIER
                        )
                    )
                }

                listOf(ConditionMiss(this))
            }
        )
    )
}