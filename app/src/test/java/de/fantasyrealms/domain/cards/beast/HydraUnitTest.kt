package de.fantasyrealms.domain.cards.beast

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.flame.FireElemental
import de.fantasyrealms.domain.cards.flood.Swamp
import de.fantasyrealms.domain.cards.handOf
import org.junit.jupiter.api.Test

class HydraUnitTest {

    @Test
    fun `returns base score when without Swamp`() {
        val hydra = Hydra()
        val hand = handOf(hydra, FireElemental())

        val score = hand.getTotalScore()[hydra]

        assertThat(score).isEqualTo(hydra.baseScore)
    }

    @Test
    fun `+28 when with Swamp`() {
        val hydra = Hydra()
        val hand = handOf(hydra, Swamp())

        val score = hand.getTotalScore()[hydra]

        assertThat(score).isEqualTo(hydra.baseScore + 28)
    }
}
