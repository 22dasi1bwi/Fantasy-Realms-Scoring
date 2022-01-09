package de.fantasyrealms.domain.cards.leader

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.army.LightCavalry
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.land.Cavern
import org.junit.jupiter.api.Test

class EmpressUnitTest {

    @Test
    fun `returns base score`() {
        val empress = Empress()
        val hand = handOf(empress, Cavern())

        val score = hand.getTotalScore()[empress]

        assertThat(score).isEqualTo(empress.baseScore)

    }

    @Test
    fun `+10 for each Army`() {
        val empress = Empress()
        val hand = handOf(empress, Knights(), LightCavalry())

        val score = hand.getTotalScore()[empress]

        assertThat(score).isEqualTo(empress.baseScore + 10 + 10)
    }

    @Test
    fun `-5 for each other Leader`() {
        val empress = Empress()
        val hand = handOf(empress, Princess())

        val score = hand.getTotalScore()[empress]

        assertThat(score).isEqualTo(empress.baseScore - 5)
    }

    @Test
    fun `gains and losses with other Leader and Armies`() {
        val empress = Empress()
        val hand = handOf(empress, Princess(), Knights(), LightCavalry())

        val score = hand.getTotalScore()[empress]

        assertThat(score).isEqualTo(empress.baseScore - 5 + 10 + 10)
    }
}