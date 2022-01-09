package de.fantasyrealms.domain.cards.army

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.land.Cavern
import org.junit.jupiter.api.Test


class DwarvishInfantryUnitTest {

    @Test
    fun `return base score`() {
        val dwarvishInfantry = DwarvishInfantry()
        val hand = handOf(dwarvishInfantry, Cavern())

        val score = hand.getTotalScore()[dwarvishInfantry]

        assertThat(score).isEqualTo(dwarvishInfantry.baseScore)
    }

    @Test
    fun `-2 for each other Army`() {
        val dwarvishInfantry = DwarvishInfantry()
        val hand = handOf(dwarvishInfantry, Knights(), LightCavalry())

        val score = hand.getTotalScore()[dwarvishInfantry]

        assertThat(score).isEqualTo(dwarvishInfantry.baseScore -2 -2)
    }
}