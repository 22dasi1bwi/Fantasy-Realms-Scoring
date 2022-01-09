package de.fantasyrealms.domain.cards.weapon

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.land.Cavern
import de.fantasyrealms.domain.cards.wizard.Beastmaster
import org.junit.jupiter.api.Test

class MagicWandUnitTest {

    @Test
    fun `returns base score when without Wizard`() {
        val magicWand = MagicWand()
        val hand = handOf(magicWand, Cavern())

        val score = hand.getTotalScore()[magicWand]

        assertThat(score).isEqualTo(magicWand.baseScore)
    }

    @Test
    fun `+25 when with Wizard`() {
        val magicWand = MagicWand()
        val hand = handOf(magicWand, Beastmaster())

        val score = hand.getTotalScore()[magicWand]

        assertThat(score).isEqualTo(magicWand.baseScore + 25)
    }

    @Test
    fun `no additional gains when with multiple Wizards`() {
        val magicWand = MagicWand()
        val hand = handOf(magicWand, Beastmaster(), Beastmaster())

        val score = hand.getTotalScore()[magicWand]

        assertThat(score).isEqualTo(magicWand.baseScore + 25)
    }
}