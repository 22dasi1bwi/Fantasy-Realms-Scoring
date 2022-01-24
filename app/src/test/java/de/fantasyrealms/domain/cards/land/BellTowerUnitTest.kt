package de.fantasyrealms.domain.cards.land

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.flame.FireElemental
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.wizard.Beastmaster
import org.junit.jupiter.api.Test

class BellTowerUnitTest {

    @Test
    fun `returns base score when without any Wizard`() {
        val bellTower = BellTower()
        val hand = handOf(bellTower, FireElemental())

        val score = hand.getTotalScore()[bellTower]

        assertThat(score).isEqualTo(bellTower.baseScore)
    }

    @Test
    fun `+15 when with at least one Wizard`() {
        val bellTower = BellTower()
        val hand = handOf(bellTower, Beastmaster())

        val score = hand.getTotalScore()[bellTower]

        assertThat(score).isEqualTo(bellTower.baseScore + 15)
    }
}
