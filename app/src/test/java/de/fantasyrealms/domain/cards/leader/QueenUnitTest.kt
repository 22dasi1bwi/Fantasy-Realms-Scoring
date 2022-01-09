package de.fantasyrealms.domain.cards.leader

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.army.LightCavalry
import de.fantasyrealms.domain.cards.flame.FireElemental
import de.fantasyrealms.domain.cards.handOf
import org.junit.jupiter.api.Test

class QueenUnitTest {

    @Test
    fun `returns base score when without any ARMY`() {
        val queen = Queen()
        val hand = handOf(queen, FireElemental())

        val score = hand.getTotalScore()[queen]

        assertThat(score).isEqualTo(queen.baseScore)
    }

    @Test
    fun `+5 for each ARMY when without King`() {
        val queen = Queen()
        val hand = handOf(queen, Knights(), LightCavalry())

        val score = hand.getTotalScore()[queen]

        assertThat(score).isEqualTo(queen.baseScore + 5 + 5)
    }

    @Test
    fun `+20 for each ARMY when with King`() {
        val queen = Queen()
        val hand = handOf(queen, Knights(), LightCavalry(), King())

        val score = hand.getTotalScore()[queen]

        assertThat(score).isEqualTo(queen.baseScore + 20 + 20)
    }
}
