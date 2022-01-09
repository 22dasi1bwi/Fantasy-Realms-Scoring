package de.fantasyrealms.domain.cards.land

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.handOf
import org.junit.jupiter.api.Test

class EarthElementalUnitTest {

    @Test
    fun `returns base score`() {
        val earthElemental = EarthElemental()
        val hand = handOf(earthElemental, Knights())

        val score = hand.getTotalScore()[earthElemental]

        assertThat(score).isEqualTo(earthElemental.baseScore)
    }

    @Test
    fun `+15 for each other Land`() {
        val earthElemental = EarthElemental()
        val hand = handOf(earthElemental, Cavern(), Forest())

        val score = hand.getTotalScore()[earthElemental]

        assertThat(score).isEqualTo(earthElemental.baseScore + 15 + 15)
    }
}