package de.fantasyrealms.domain.cards.army

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.land.Cavern
import de.fantasyrealms.domain.cards.land.EarthElemental
import de.fantasyrealms.domain.cards.leader.Empress
import org.junit.jupiter.api.Test

class LightCavalryUnitTest {

    @Test
    fun `-2 for each Land`() {
        val hand = handOf(LightCavalry, EarthElemental, Cavern)

        val score = hand.getScore(LightCavalry)

        assertThat(score).isEqualTo(LightCavalry.baseScore - 2 - 2)
    }

    @Test
    fun `no penalty when no Land is present`() {
        val hand = handOf(LightCavalry, Empress)

        val score = hand.getScore(LightCavalry)

        assertThat(score).isEqualTo(LightCavalry.baseScore)
    }
}
