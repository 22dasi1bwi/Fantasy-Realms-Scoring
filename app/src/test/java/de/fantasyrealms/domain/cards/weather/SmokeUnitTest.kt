package de.fantasyrealms.domain.cards.weather

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import de.fantasyrealms.domain.cards.flame.FireElemental
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.weapon.MagicWand
import org.junit.jupiter.api.Test

class SmokeUnitTest {

    @Test
    fun `returns base score when with at least one Flame`() {
        val smoke = Smoke()
        val hand = handOf(smoke, FireElemental())

        val score = hand.getTotalScore()[smoke]

        assertThat(score).isEqualTo(smoke.baseScore)
    }

    @Test
    fun `blanked when not with Flame`() {
        val smoke = Smoke()
        val hand = handOf(smoke, MagicWand())

        val score = hand.getTotalScore()[smoke]

        assertAll {
            assertThat(score).isEqualTo(0)
            assertThat(smoke.blanked).isTrue()
        }
    }
}