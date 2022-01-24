package de.fantasyrealms.domain.cards.flame

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.cards.artifact.BookOfChanges
import de.fantasyrealms.domain.cards.beast.Warhorse
import de.fantasyrealms.domain.cards.flood.GreatFlood
import de.fantasyrealms.domain.cards.flood.Swamp
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.weapon.MagicWand
import de.fantasyrealms.domain.cards.weather.Blizzard
import de.fantasyrealms.domain.cards.weather.Rainstorm
import de.fantasyrealms.domain.cards.wizard.Beastmaster
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class WildfireUnitTest {

    @Test
    fun `returns base score`() {
        val wildfire = Wildfire()
        val hand = handOf(wildfire, Warhorse())

        val score = hand.getTotalScore()[wildfire]

        assertThat(score).isEqualTo(wildfire.baseScore)
    }

    @Nested
    inner class `does not blank specific Cards` {

        @Test
        fun `Great Flood`() {
            val wildfire = Wildfire()
            // Great Flood won't be blanked and thus blanking all Flames including Wildfire
            assertThat(wildfire.isBlanked()).isFalse()
            val hand = handOf(wildfire, GreatFlood())

            hand.getTotalScore()

            assertThat(wildfire.isBlanked()).isTrue()
        }

        @Test
        fun `Island`() {
            val wildfire = Wildfire()
            // Island won't be blanked and thus clearing the penalties on Swamp
            val swamp = Swamp()
            assertThat(swamp.effectDefinition.conditions.all { it.status.isCleared() }).isFalse()
            val hand = handOf(wildfire, de.fantasyrealms.domain.cards.flood.Island(swamp), swamp)

            hand.getTotalScore()

            assertThat(swamp.effectDefinition.conditions.all { it.status.isCleared() }).isTrue()
        }

        @Test
        fun `Mountain`() {
            val wildfire = Wildfire()
            val swamp = Swamp() // cleared by mountain, thus no -3 for wildfire
            val mountain = de.fantasyrealms.domain.cards.land.Mountain() // not blanked, thus clearing Swamp
            val hand = handOf(wildfire, mountain, swamp)

            val swampScore = hand.getTotalScore()[swamp]

            assertThat(swampScore).isEqualTo(swamp.baseScore)
        }

        @Test
        fun `Unicorn`() {
            val wildfire = Wildfire()
            val unicorn = de.fantasyrealms.domain.cards.beast.Unicorn()
            val hand = handOf(wildfire, unicorn)

            hand.getTotalScore()

            assertThat(unicorn.isBlanked()).isFalse()
        }

        @Test
        fun `Dragon`() {
            val wildfire = Wildfire()
            // Dragon won't be blanked and thus getting -40 because of missing Wizard
            val dragon = de.fantasyrealms.domain.cards.beast.Dragon()
            val hand = handOf(wildfire, dragon)

            val score = hand.getTotalScore()[dragon]

            assertThat(score).isEqualTo(-10)
        }
    }

    @Nested
    inner class `does not blank Cards of specific Suits` {

        @Test
        fun `Flame`() {
            val wildfire = Wildfire()
            val forge = Forge()
            val fireElemental = FireElemental()
            val hand = handOf(wildfire, forge, fireElemental)

            hand.getTotalScore()

            assertAll {
                assertThat(forge.isBlanked()).isFalse()
                assertThat(fireElemental.isBlanked()).isFalse()
            }
        }

        @Test
        fun `Weather`() {
            val wildfire = Wildfire()
            val blizzard = Blizzard()
            val rainstorm = Rainstorm()
            val hand = handOf(wildfire, blizzard, rainstorm)

            val score = hand.getTotalScore().raw

            assertThat(score).isEqualTo(
                0 + // wildfire (blanked by rainstorm)
                blizzard.baseScore + // not blanked, no penalty for wildfire, since it has been blanked
                rainstorm.baseScore // not blanked, thus blanking wildfire
            )
        }

        @Test
        fun `Wizard`() {
            val wildfire = Wildfire()
            val beastmaster = Beastmaster()
            val hand = handOf(wildfire, beastmaster)

            hand.getTotalScore()

            assertThat(beastmaster.isBlanked()).isFalse()
        }

        @Test
        fun `Weapon`() {
            val wildfire = Wildfire()
            val magicWand = MagicWand()
            val hand = handOf(wildfire, magicWand)

            hand.getTotalScore()

            assertThat(magicWand.isBlanked()).isFalse()
        }

        @Test
        fun `Artifact`() {
            val wildfire = Wildfire()
            val bookOfChanges = BookOfChanges(wildfire, Suit.FLAME)
            val hand = handOf(wildfire, bookOfChanges)

            hand.getTotalScore()

            assertThat(bookOfChanges.isBlanked()).isFalse()
        }
    }
}
