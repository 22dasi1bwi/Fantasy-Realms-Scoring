package de.fantasyrealms.domain.cards.weather

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.flame.FireElemental
import de.fantasyrealms.domain.cards.handOf
import org.junit.jupiter.api.Test

class AirElementalUnitTest {

    @Test
    fun `returns base score when without other Weather cards`() {
        val airElemental = AirElemental()
        val hand = handOf(airElemental, FireElemental())

        val score = hand.getTotalScore()[airElemental]

        assertThat(score).isEqualTo(airElemental.baseScore)
    }

    @Test
    fun `+15 for each other Weather`() {
        val airElemental = AirElemental()
        val hand = handOf(airElemental, Blizzard(), Rainstorm())

        val score = hand.getTotalScore()[airElemental]

        assertThat(score).isEqualTo(airElemental.baseScore + 15 + 15)
    }
}
