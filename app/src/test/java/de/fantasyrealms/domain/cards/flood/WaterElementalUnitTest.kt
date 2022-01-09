package de.fantasyrealms.domain.cards.flood

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.handOf
import org.junit.jupiter.api.Test

class WaterElementalUnitTest {

    @Test
    fun `returns base score`() {
        val waterElemental = WaterElemental()
        val hand = handOf(waterElemental, Knights())

        val score = hand.getTotalScore()[waterElemental]

        assertThat(score).isEqualTo(waterElemental.baseScore)
    }

    @Test
    fun `+15 for each Flood`() {
        val waterElemental = WaterElemental()
        val hand = handOf(waterElemental, Island(waterElemental), Swamp())

        val score = hand.getTotalScore()[waterElemental]

        assertThat(score).isEqualTo(waterElemental.baseScore + 15 + 15)
    }
}