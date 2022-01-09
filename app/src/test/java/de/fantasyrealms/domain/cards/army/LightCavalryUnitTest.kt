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
        val lightCavalry = LightCavalry()
        val hand = handOf(lightCavalry, EarthElemental(), Cavern())

        val score = hand.getTotalScore()[lightCavalry]

        assertThat(score).isEqualTo(lightCavalry.baseScore - 2 - 2)
    }

    @Test
    fun `no penalty when no Land is present`() {
        val lightCavalry = LightCavalry()
        val hand = handOf(lightCavalry, Empress())

        val score = hand.getTotalScore()[lightCavalry]

        assertThat(score).isEqualTo(lightCavalry.baseScore)
    }
}
