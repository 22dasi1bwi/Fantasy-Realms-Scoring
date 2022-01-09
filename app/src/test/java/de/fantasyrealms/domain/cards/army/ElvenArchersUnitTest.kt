package de.fantasyrealms.domain.cards.army

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.beast.Warhorse
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.weather.Rainstorm
import org.junit.jupiter.api.Test

class ElvenArchersUnitTest {

    @Test
    fun `returns base score when with Weather`() {
        val elvenArchers = ElvenArchers()
        val hand = handOf(elvenArchers, Rainstorm())

        val score = hand.getTotalScore()[elvenArchers]

        assertThat(score).isEqualTo(elvenArchers.baseScore)
    }

    @Test
    fun `+5 when without Weather`() {
        val elvenArchers = ElvenArchers()
        val hand = handOf(elvenArchers, Warhorse())

        val score = hand.getTotalScore()[elvenArchers]

        assertThat(score).isEqualTo(elvenArchers.baseScore + 5)
    }
}
