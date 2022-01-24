package de.fantasyrealms.domain.cards.artifact

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.flame.FireElemental
import de.fantasyrealms.domain.cards.flood.WaterElemental
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.land.Cavern
import de.fantasyrealms.domain.cards.land.EarthElemental
import org.junit.jupiter.api.Test

class WorldTreeUnitTest {

    @Test
    fun `returns base score when with multiple active cards with same suit`() {
        val worldTree = WorldTree()
        val hand = handOf(worldTree, EarthElemental(), Cavern())

        val score = hand.getTotalScore()[worldTree]

        assertThat(score).isEqualTo(worldTree.baseScore)
    }

    @Test
    fun `+50 when with no duplicated Suit in hand`() {
        val worldTree = WorldTree()
        val hand = handOf(worldTree, EarthElemental(), FireElemental(), WaterElemental())

        val score = hand.getTotalScore()[worldTree]

        assertThat(score).isEqualTo(worldTree.baseScore + 50)
    }

    @Test
    fun `non active Cards are filtered`() {
        val worldTree = WorldTree()
        val earthElemental = EarthElemental()
        earthElemental.effectDefinition.conditions.forEach { it.blank() }
        // Although with two "LAND"s, a bonus is gained because EarthElemental is not active
        val hand = handOf(worldTree, earthElemental, Cavern(), WaterElemental())

        val score = hand.getTotalScore()[worldTree]

        assertThat(score).isEqualTo(worldTree.baseScore + 50)
    }
}
