package de.fantasyrealms.domain.cards.weather

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.flood.GreatFlood
import de.fantasyrealms.domain.cards.handOf
import org.junit.jupiter.api.Test

class WhirlwindUnitTest {

    @Test
    fun `returns base score when without Rainstorm and Blizzard or Great Flood`() {
        val whirlwind = Whirlwind()
        val hand = handOf(whirlwind, Rainstorm())

        val score = hand.getTotalScore()[whirlwind]

        assertThat(score).isEqualTo(whirlwind.baseScore)
    }

    @Test
    fun `+40 when with Rainstorm and Blizzard`() {
        val whirlwind = Whirlwind()
        val hand = handOf(whirlwind, Rainstorm(), Blizzard())

        val score = hand.getTotalScore()[whirlwind]

        assertThat(score).isEqualTo(whirlwind.baseScore + 40)
    }

    @Test
    fun `+40 when with Rainstorm and Great Flood`() {
        val whirlwind = Whirlwind()
        val hand = handOf(whirlwind, Rainstorm(), GreatFlood())

        val score = hand.getTotalScore()[whirlwind]

        assertThat(score).isEqualTo(whirlwind.baseScore + 40)
    }
}
