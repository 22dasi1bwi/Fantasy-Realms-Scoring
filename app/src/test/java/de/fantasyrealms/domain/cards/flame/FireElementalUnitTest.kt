package de.fantasyrealms.domain.cards.flame

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.flood.Swamp
import de.fantasyrealms.domain.cards.handOf
import org.junit.jupiter.api.Test

class FireElementalUnitTest {

    @Test
    fun `returns base score when no other Flame present`() {
        val fireElemental = FireElemental()
        val hand = handOf(fireElemental, Swamp())

        val score = hand.getTotalScore()[fireElemental]

        assertThat(score).isEqualTo(fireElemental.baseScore)
    }

    @Test
    fun `+15 for every other Flame`() {
        val fireElemental = FireElemental()
        val hand = handOf(fireElemental, Forge(), Lightning())

        val score = hand.getTotalScore()[fireElemental]

        assertThat(score).isEqualTo(fireElemental.baseScore + 15 + 15)
    }
}