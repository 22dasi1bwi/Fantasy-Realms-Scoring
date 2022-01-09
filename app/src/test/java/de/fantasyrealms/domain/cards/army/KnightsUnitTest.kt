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
        val hand = handOf(Knights, Beastmaster)

        val score = hand.getScore(Knights)

        assertThat(score).isEqualTo(Knights.baseScore - 8)
    }

    @Test
    fun `no penalty when with Leader`() {
        val hand = handOf(Knights, Empress)

        val score = hand.getScore(Knights)

        assertThat(score).isEqualTo(Knights.baseScore)
    }

    @Test
    fun `multiple Leaders have no additional effect`() {
        val hand = handOf(Knights, Empress, Princess)

        val score = hand.getScore(Knights)

        assertThat(score).isEqualTo(Knights.baseScore)
    }
}
