package de.fantasyrealms.domain.cards.flood

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.flame.FireElemental
import de.fantasyrealms.domain.cards.flame.Lightning
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.land.Cavern
import de.fantasyrealms.domain.cards.land.EarthElemental
import de.fantasyrealms.domain.cards.land.Mountain
import de.fantasyrealms.domain.cards.weapon.MagicWand
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class GreatFloodUnitTest {

    @Test
    fun `returns base score`() {
        val greatFlood = GreatFlood()
        val magicWand = MagicWand()
        val hand = handOf(greatFlood, magicWand)

        val score = hand.getTotalScore()[greatFlood]

        assertThat(score).isEqualTo(greatFlood.baseScore)
    }

    @Nested
    inner class `blanks` {

        @Test
        fun `all Armies`() {
            val greatFlood = GreatFlood()
            val knights = Knights()
            val hand = handOf(greatFlood, knights)

            hand.getTotalScore()[greatFlood]

            assertThat(knights.isBlanked()).isTrue()
        }

        @Test
        fun `all Lands`() {
            val greatFlood = GreatFlood()
            val earthElemental = EarthElemental()
            val cavern = Cavern()
            val hand = handOf(greatFlood, cavern, earthElemental)

            hand.getTotalScore()[greatFlood]

            assertAll {
                assertThat(earthElemental.isBlanked()).isTrue()
                assertThat(cavern.isBlanked()).isTrue()
            }
        }

        @Test
        fun `all Lands except Mountain`() {
            val greatFlood = GreatFlood()
            val earthElemental = EarthElemental()
            val mountain = Mountain()
            val hand = handOf(greatFlood, mountain, earthElemental)

            hand.getTotalScore()[greatFlood]

            assertAll {
                assertThat(earthElemental.isBlanked()).isFalse() // mountain clears Great Flood, thus not blanking EarthElemental
                assertThat(mountain.isBlanked()).isFalse()
            }
        }

        @Test
        fun `all Flames except Lightning`() {
            val greatFlood = GreatFlood()
            val fireElemental = FireElemental()
            val lightning = Lightning()
            val hand = handOf(greatFlood, lightning, fireElemental)

            hand.getTotalScore()[greatFlood]

            assertAll {
                assertThat(fireElemental.isBlanked()).isTrue()
                assertThat(lightning.isBlanked()).isFalse()
            }
        }
    }
}
