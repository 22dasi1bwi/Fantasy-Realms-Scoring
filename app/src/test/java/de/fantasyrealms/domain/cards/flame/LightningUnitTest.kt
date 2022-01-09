package de.fantasyrealms.domain.cards.flame

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.weather.Rainstorm
import org.junit.jupiter.api.Test

class LightningUnitTest {

    @Test
    fun `returns base score`() {
        val lightning = Lightning()
        val hand = handOf(lightning, Knights())

        val score = hand.getTotalScore()[lightning]

        assertThat(score).isEqualTo(lightning.baseScore)
    }

    @Test
    fun `+30 when with Rainstorm`() {
        val lightning = Lightning()
        val hand = handOf(lightning, Rainstorm())

        val score = hand.getTotalScore()[lightning]

        assertThat(score).isEqualTo(lightning.baseScore + 30)
    }
}