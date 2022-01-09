package de.fantasyrealms.domain.cards.army

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.leader.Empress
import de.fantasyrealms.domain.cards.leader.Princess
import de.fantasyrealms.domain.cards.wizard.Beastmaster
import org.junit.jupiter.api.Test

class KnightsUnitTest {

    @Test
    fun `-8 when without Leader`() {
        val knights = Knights()
        val hand = handOf(knights, Beastmaster())

        val score = hand.getTotalScore()[knights]

        assertThat(score).isEqualTo(knights.baseScore - 8)
    }

    @Test
    fun `no penalty when with Leader`() {
        val knights = Knights()
        val hand = handOf(knights, Empress())

        val score = hand.getTotalScore()[knights]

        assertThat(score).isEqualTo(knights.baseScore)
    }

    @Test
    fun `multiple Leaders have no additional effect`() {
        val knights = Knights()
        val hand = handOf(knights, Empress(), Princess())

        val score = hand.getTotalScore()[knights]

        assertThat(score).isEqualTo(knights.baseScore)
    }
}
