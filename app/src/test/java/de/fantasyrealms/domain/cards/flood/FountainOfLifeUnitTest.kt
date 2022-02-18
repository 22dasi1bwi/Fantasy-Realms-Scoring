package de.fantasyrealms.domain.cards.flood

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import de.fantasyrealms.domain.cards.flame.FireElemental
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.land.Cavern
import de.fantasyrealms.domain.cards.weapon.MagicWand
import de.fantasyrealms.domain.cards.weather.Rainstorm
import de.fantasyrealms.domain.cards.wizard.Beastmaster
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

class FountainOfLifeUnitTest {

    @Test
    fun `throws if choice is not any of allowed suits`() {
        val beastmaster = Beastmaster() // is not allowed suit

        assertThat {  FountainOfLife(beastmaster) }.isFailure().isInstanceOf(IllegalArgumentException::class)
    }

    @Nested
    inner class `gains base score from` {

        @Test
        fun `any Weapon in hand`() {
            val magicWand = MagicWand()
            val fountainOfLife = FountainOfLife(magicWand)
            val hand = handOf(fountainOfLife, magicWand)

            val score = hand.getTotalScore()[fountainOfLife]

            assertThat(score).isEqualTo(fountainOfLife.baseScore + magicWand.baseScore)
        }

        @Test
        fun `any Flood in hand`() {
            val greatFlood = GreatFlood()
            val fountainOfLife = FountainOfLife(greatFlood)
            val hand = handOf(fountainOfLife, greatFlood)

            val score = hand.getTotalScore()[fountainOfLife]

            assertThat(score).isEqualTo(fountainOfLife.baseScore + greatFlood.baseScore)
        }

        @Test
        fun `any Flame in hand`() {
            val fireElemental = FireElemental()
            val fountainOfLife = FountainOfLife(fireElemental)
            val hand = handOf(fountainOfLife, fireElemental)

            val score = hand.getTotalScore()[fountainOfLife]

            assertThat(score).isEqualTo(fountainOfLife.baseScore + fireElemental.baseScore)
        }

        @Test
        fun `any Land in hand`() {
            val cavern = Cavern()
            val fountainOfLife = FountainOfLife(cavern)
            val hand = handOf(fountainOfLife, cavern)

            val score = hand.getTotalScore()[fountainOfLife]

            assertThat(score).isEqualTo(fountainOfLife.baseScore + cavern.baseScore)
        }

        @Test
        fun `any Weather in hand`() {
            val rainstorm = Rainstorm()
            val fountainOfLife = FountainOfLife(rainstorm)
            val hand = handOf(fountainOfLife, rainstorm)

            val score = hand.getTotalScore()[fountainOfLife]

            assertThat(score).isEqualTo(fountainOfLife.baseScore + rainstorm.baseScore)
        }
    }
}
