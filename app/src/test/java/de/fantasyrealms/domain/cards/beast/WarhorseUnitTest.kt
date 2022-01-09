package de.fantasyrealms.domain.cards.beast

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.leader.Princess
import de.fantasyrealms.domain.cards.wizard.Beastmaster
import org.junit.jupiter.api.Test

class WarhorseUnitTest {

    @Test
    fun `returns base score when without any Leader or Wizard`() {
        val warhorse = Warhorse()

        val hand = handOf(warhorse, Knights())

        val score = hand.getTotalScore()[warhorse]

        assertThat(score).isEqualTo(warhorse.baseScore)
    }

    @Test
    fun `+14 when with Leader`() {
        val warhorse = Warhorse()

        val hand = handOf(warhorse, Princess())

        val score = hand.getTotalScore()[warhorse]

        assertThat(score).isEqualTo(warhorse.baseScore + 14)
    }

    @Test
    fun `+14 when with Wizard`() {
        val warhorse = Warhorse()

        val hand = handOf(warhorse, Beastmaster())

        val score = hand.getTotalScore()[warhorse]

        assertThat(score).isEqualTo(warhorse.baseScore + 14)
    }

    @Test
    fun `+14 even when with Wizard AND Leader`() {
        val warhorse = Warhorse()

        val hand = handOf(warhorse, Beastmaster(), Princess())

        val score = hand.getTotalScore()[warhorse]

        assertThat(score).isEqualTo(warhorse.baseScore + 14)
    }
}