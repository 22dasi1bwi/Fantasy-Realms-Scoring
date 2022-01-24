package de.fantasyrealms.domain.cards.wild

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.beast.Warhorse
import de.fantasyrealms.domain.cards.flame.FireElemental
import de.fantasyrealms.domain.cards.flame.Forge
import de.fantasyrealms.domain.cards.flood.Swamp
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.land.EarthElemental
import de.fantasyrealms.domain.cards.weapon.MagicWand
import de.fantasyrealms.domain.cards.weather.Rainstorm
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MirageUnitTest {

    @Nested
    inner class `mimicing` {

        @Test
        fun `takes on Name`() {
            val forge = Forge()
            val mirage = Mirage(forge)

            assertThat(mirage.name).isEqualTo(forge.name)
        }

        @Test
        fun `does not take on Bonuses`() {
            val mirage = Mirage(Forge()) // forge's bonus gives + 9 for each weapon
            val magicWand = MagicWand()
            val hand = handOf(mirage, magicWand) // we're not taking bonuses!

            val score = hand.getTotalScore()[mirage]

            assertThat(score).isEqualTo(0)
        }

        @Test
        fun `does not take on Penalties`() {
            val mirage = Mirage(Swamp()) // - penalty for Flames
            val forge = Forge()
            val hand = handOf(mirage, forge) // we're not taking penalties!

            val score = hand.getTotalScore()[mirage]

            assertThat(score).isEqualTo(0)
        }

        @Test
        fun `throws if Suit is not supported`() {
            // Beasts are not supported
            assertThat { Mirage(Warhorse()) }.isFailure().isInstanceOf(IllegalArgumentException::class)
        }

        @Nested
        inner class `suit` {

            @Test
            fun `if ARMY`() {
                val mirage = Mirage(Knights())

                assertThat(mirage.suit).isEqualTo(Suit.ARMY)
            }

            @Test
            fun `if LAND`() {
                val mirage = Mirage(EarthElemental())

                assertThat(mirage.suit).isEqualTo(Suit.LAND)
            }

            @Test
            fun `if WEATHER`() {
                val mirage = Mirage(Rainstorm())

                assertThat(mirage.suit).isEqualTo(Suit.WEATHER)
            }

            @Test
            fun `if FLOOD`() {
                val mirage = Mirage(Swamp())

                assertThat(mirage.suit).isEqualTo(Suit.FLOOD)
            }

            @Test
            fun `if FLAME`() {
                val mirage = Mirage(FireElemental())

                assertThat(mirage.suit).isEqualTo(Suit.FLAME)
            }
        }
    }
}

