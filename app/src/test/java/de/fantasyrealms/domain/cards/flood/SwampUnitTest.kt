package de.fantasyrealms.domain.cards.flood

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.army.LightCavalry
import de.fantasyrealms.domain.cards.flame.Forge
import de.fantasyrealms.domain.cards.flame.Lightning
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.weapon.MagicWand
import org.junit.jupiter.api.Test

class SwampUnitTest {

    @Test
    fun `returns base score`() {
        val swamp = Swamp()
        val hand = handOf(swamp, MagicWand())

        val score = hand.getTotalScore()[swamp]

        assertThat(score).isEqualTo(swamp.baseScore)
    }

    @Test
    fun `-3 for each Army`() {
        val swamp = Swamp()
        val hand = handOf(swamp, Knights(), LightCavalry())

        val score = hand.getTotalScore()[swamp]

        assertThat(score).isEqualTo(swamp.baseScore - 3 - 3)
    }

    @Test
    fun `-3 for each Flame`() {
        val swamp = Swamp()
        val hand = handOf(swamp, Forge(), Lightning())

        val score = hand.getTotalScore()[swamp]

        assertThat(score).isEqualTo(swamp.baseScore - 3 - 3)
    }

    @Test
    fun `multiple penalties from different Suits`() {
        val swamp = Swamp()
        val hand = handOf(swamp, Knights(), LightCavalry(), Forge(), Lightning())

        val score = hand.getTotalScore()[swamp]

        assertThat(score).isEqualTo(swamp.baseScore - 3 - 3 - 3 -3)
    }
}