package de.fantasyrealms.domain.cards.weapon

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.land.Cavern
import de.fantasyrealms.domain.cards.weather.Rainstorm
import org.junit.jupiter.api.Test

class WarDirigibleUnitTest {

    @Test
    fun `returns base score when with at least with one Army and without any Weather`() {
        val warDirigible = WarDirigible()
        val hand = handOf(warDirigible, Knights())

        val score = hand.getTotalScore()[warDirigible]

        assertThat(score).isEqualTo(warDirigible.baseScore)
    }

    @Test
    fun `blanked when without Army`() {
        val warDirigible = WarDirigible()
        val hand = handOf(warDirigible, Cavern())

        val score = hand.getTotalScore()[warDirigible]

        assertThat(score).isEqualTo(0)
        assertThat(warDirigible.isBlanked()).isTrue()
    }

    @Test
    fun `blanked when with any Weather`() {
        val warDirigible = WarDirigible()
        val hand = handOf(warDirigible, Rainstorm())

        val score = hand.getTotalScore()[warDirigible]

        assertThat(score).isEqualTo(0)
        assertThat(warDirigible.isBlanked()).isTrue()
    }
}