package de.fantasyrealms.domain.cards.land

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.flame.Wildfire
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.weather.Smoke
import org.junit.jupiter.api.Test

class MountainUnitTest {

    @Test
    fun `returns base score when not with Smoke and Wildfire at the same time`() {
        val mountain = Mountain()
        val hand = handOf(mountain, Knights(), Cavern())

        val score = hand.getTotalScore()[mountain]

        assertThat(score).isEqualTo(mountain.baseScore)
    }

    @Test
    fun `returns base score when with Smoke but without Wildfire`() {
        val mountain = Mountain()
        val hand = handOf(mountain, Smoke())

        val score = hand.getTotalScore()[mountain]

        assertThat(score).isEqualTo(mountain.baseScore)
    }

    @Test
    fun `returns base score when with Wildfire but without Smoke`() {
        val mountain = Mountain()
        val hand = handOf(mountain, Wildfire())

        val score = hand.getTotalScore()[mountain]

        assertThat(score).isEqualTo(mountain.baseScore)
    }

    @Test
    fun `+50 when with Wildfire AND Smoke`() {
        val mountain = Mountain()
        val hand = handOf(mountain, Wildfire(), Smoke())

        val score = hand.getTotalScore()[mountain]

        assertThat(score).isEqualTo(mountain.baseScore + 50)
    }
}