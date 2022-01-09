package de.fantasyrealms.domain.cards.wizard

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.flame.FireElemental
import de.fantasyrealms.domain.cards.flame.Forge
import de.fantasyrealms.domain.cards.flood.Swamp
import de.fantasyrealms.domain.cards.flood.WaterElemental
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.land.Cavern
import de.fantasyrealms.domain.cards.land.EarthElemental
import de.fantasyrealms.domain.cards.leader.King
import de.fantasyrealms.domain.cards.weather.Blizzard
import de.fantasyrealms.domain.cards.weather.Rainstorm
import org.junit.jupiter.api.Test

class EnchantressUnitTest {

    @Test
    fun `returns base score when without Land, Weather, Flood or Flame`() {
        val enchantress = Enchantress()
        val hand = handOf(enchantress, King())

        val score = hand.getTotalScore()[enchantress]

        assertThat(score).isEqualTo(enchantress.baseScore)
    }

    @Test
    fun `+5 for each Land`() {
        val enchantress = Enchantress()
        val hand = handOf(enchantress, Cavern(), EarthElemental())

        val score = hand.getTotalScore()[enchantress]

        assertThat(score).isEqualTo(enchantress.baseScore + 5 + 5)
    }
    @Test
    fun `+5 for each Weather`() {
        val enchantress = Enchantress()
        val hand = handOf(enchantress, Blizzard(), Rainstorm())

        val score = hand.getTotalScore()[enchantress]

        assertThat(score).isEqualTo(enchantress.baseScore + 5 + 5)
    }
    @Test
    fun `+5 for each Flood`() {
        val enchantress = Enchantress()
        val hand = handOf(enchantress, WaterElemental(), Swamp())

        val score = hand.getTotalScore()[enchantress]

        assertThat(score).isEqualTo(enchantress.baseScore + 5 + 5)
    }
    @Test
    fun `+5 for each Flame`() {
        val enchantress = Enchantress()
        val hand = handOf(enchantress, Forge(), FireElemental())

        val score = hand.getTotalScore()[enchantress]

        assertThat(score).isEqualTo(enchantress.baseScore + 5 + 5)
    }
}
