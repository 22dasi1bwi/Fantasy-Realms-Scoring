package de.fantasyrealms.domain.cards.weapon

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.flame.FireElemental
import de.fantasyrealms.domain.cards.flame.Forge
import de.fantasyrealms.domain.cards.flood.Swamp
import de.fantasyrealms.domain.cards.flood.WaterElemental
import de.fantasyrealms.domain.cards.handOf
import org.junit.jupiter.api.Test

class WarshipUnitTest {

    @Test
    fun `returns base score when with Flood`() {
        val warship = Warship()
        val hand = handOf(warship, WaterElemental())

        val score = hand.getTotalScore()[warship]

        assertThat(score).isEqualTo(warship.baseScore)
    }

    @Test
    fun `blanked when without Flood`() {
        val warship = Warship()
        val hand = handOf(warship, FireElemental())

        val score = hand.getTotalScore()[warship]

        assertThat(score).isEqualTo(0)
        assertThat(warship.isBlanked()).isTrue()
    }

    @Test
    fun `clears word Army from all Penalty sections of Floods`() {
        val warship = Warship()
        val swamp = Swamp() // -3 for each army
        val knights = Knights()
        val hand = handOf(warship, swamp, knights)

        val score = hand.getTotalScore()[swamp]

        assertThat(score).isEqualTo(swamp.baseScore) // no penalty for Knights
    }
}
