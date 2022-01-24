package de.fantasyrealms.domain.cards

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.EffectType
import de.fantasyrealms.domain.cards.army.LightCavalry
import de.fantasyrealms.domain.cards.land.EarthElemental
import de.fantasyrealms.domain.cards.leader.Princess
import de.fantasyrealms.domain.event.ForEachEvent
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class AbstractCardUnitTest {

    @Nested
    inner class `getting Score` {

        @Test
        fun `returns Zero on no Events`() {
            val card = Princess()

            val score = card.getScore(listOf())

            assertThat(score.raw).isEqualTo(0)
        }

        @Test
        fun `returns Zero if Card is blanked`() {
            val card = Princess()
            card.effectDefinition.conditions.forEach { it.blank() }
            val event = ForEachEvent(card, LightCavalry(), EffectType.BONUS, 5)

            val score = card.getScore(listOf(event))

            assertThat(score.raw).isEqualTo(0)
        }

        @Test
        fun `returns Zero for Event indicating Penalty and Card has been cleared`() {
            val card = LightCavalry()
            card.effectDefinition.conditions.forEach { it.clear() }
            val zeroScoreEvent = ForEachEvent(card, EarthElemental(), EffectType.PENALTY, -2)
            val event = ForEachEvent(card, LightCavalry(), EffectType.BONUS, 5)

            val score = card.getScore(listOf(event, zeroScoreEvent))

            assertThat(score.raw).isEqualTo(5)
        }

        @Test
        fun `returns Effect modifier for Event if Card has not been cleared`() {
            val card = LightCavalry()
            val event = ForEachEvent(card, EarthElemental(), EffectType.PENALTY, -2)

            val score = card.getScore(listOf(event))

            assertThat(score.raw).isEqualTo(-2)
        }

        @Test
        fun `returns Effect modifier for Event indicating a non-penalty for Card that has been cleared`() {
            val card = LightCavalry()
            card.effectDefinition.conditions.forEach { it.clear() }
            val event = ForEachEvent(card, LightCavalry(), EffectType.BONUS, 5)

            val score = card.getScore(listOf(event))

            assertThat(score.raw).isEqualTo(5)
        }

        @Test
        fun `returns sum of multiple Events`() {
            val card = LightCavalry()
            val zeroScoreEvent =
                ForEachEvent(card, EarthElemental(), EffectType.PENALTY, -2)
            val event = ForEachEvent(card, LightCavalry(), EffectType.BONUS, 5)

            val score = card.getScore(listOf(event, zeroScoreEvent))

            assertThat(score.raw).isEqualTo(3)
        }
    }
}
