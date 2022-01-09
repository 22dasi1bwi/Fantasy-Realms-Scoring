package de.fantasyrealms.domain.cards.land

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.army.DwarvishInfantry
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.beast.Dragon
import de.fantasyrealms.domain.cards.flame.Forge
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.weather.Rainstorm
import org.junit.jupiter.api.Test

class CavernUnitTest {

    @Test
    fun `returns base score`() {
        val cavern = Cavern()

        val hand = handOf(cavern, Knights())

        val score = hand.getTotalScore()[cavern]

        assertThat(score).isEqualTo(cavern.baseScore)
    }

    @Test
    fun `+25 when with Dragon`() {
        val cavern = Cavern()

        val hand = handOf(cavern, Dragon())

        val score = hand.getTotalScore()[cavern]

        assertThat(score).isEqualTo(cavern.baseScore + 25)
    }

    @Test
    fun `+25 when with Dwarvish Infantry`() {
        val cavern = Cavern()

        val hand = handOf(cavern, DwarvishInfantry())

        val score = hand.getTotalScore()[cavern]

        assertThat(score).isEqualTo(cavern.baseScore + 25)
    }

    @Test
    fun `+25 even when with Dragon AND Dwarvish Infantry`() {
        val cavern = Cavern()

        val hand = handOf(cavern, Dragon(), DwarvishInfantry())

        val score = hand.getTotalScore()[cavern]

        assertThat(score).isEqualTo(cavern.baseScore + 25)
    }

    @Test
    fun `clears all Penalties on all Weather cards`() {
        val cavern = Cavern()
        val rainstorm = Rainstorm()
        val forge = Forge() // normally blanked by rainstorm

        val hand = handOf(cavern, rainstorm, forge)

        val forgeScore = hand.getTotalScore()[forge] // should not be blanked

        assertThat(forgeScore).isEqualTo(forge.baseScore)
    }
}
