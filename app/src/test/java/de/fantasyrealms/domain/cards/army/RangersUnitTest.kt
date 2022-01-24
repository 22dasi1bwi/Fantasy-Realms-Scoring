package de.fantasyrealms.domain.cards.army

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.flame.FireElemental
import de.fantasyrealms.domain.cards.flame.Forge
import de.fantasyrealms.domain.cards.flood.Swamp
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.land.Cavern
import de.fantasyrealms.domain.cards.land.EarthElemental
import org.junit.jupiter.api.Test

class RangersUnitTest {

    @Test
    fun `returns base score when with no LAND`() {
        val rangers = Rangers()
        val hand = handOf(rangers, FireElemental())

        val score = hand.getTotalScore()[rangers]

        assertThat(score).isEqualTo(rangers.baseScore)
    }

    @Test
    fun `+10 for each other LAND`() {
        val rangers = Rangers()
        val hand = handOf(rangers, EarthElemental(), Cavern())

        val score = hand.getTotalScore()[rangers]

        assertThat(score).isEqualTo(rangers.baseScore + 10 + 10)
    }

    @Test
    fun `clears ARMY word from all Penalties of each card in hand`() {
        val rangers = Rangers()
        val forge = Forge()
        val swamp = Swamp() // no penalty for Rangers (Army); -3 penalty for Forge
        val hand = handOf(rangers, swamp, forge)

        val score = hand.getTotalScore()[swamp]

        assertThat(score).isEqualTo(swamp.baseScore - 3)
    }
}
