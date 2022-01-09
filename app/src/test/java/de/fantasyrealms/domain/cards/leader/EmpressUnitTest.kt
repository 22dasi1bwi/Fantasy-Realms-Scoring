package de.fantasyrealms.domain.cards.leader

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.army.LightCavalry
import de.fantasyrealms.domain.cards.handOf
import org.junit.jupiter.api.Test

class EmpressUnitTest {

    @Test
    fun `+10 for each Army`() {
        val hand = handOf(Empress, Knights, LightCavalry)

        val score = hand.getScore(Empress)

        assertThat(score).isEqualTo(Empress.baseScore + 10 + 10)
    }

    @Test
    fun `-5 for each other Leader`() {
        val hand = handOf(Empress, Princess)

        val score = hand.getScore(Empress)

        assertThat(score).isEqualTo(Empress.baseScore - 5)
    }

    @Test
    fun `gains and losses with other Leader and Armies`() {
        val hand = handOf(Empress, Princess, Knights, LightCavalry)

        val score = hand.getScore(Empress)

        assertThat(score).isEqualTo(Empress.baseScore - 5 + 10 + 10)
    }
}