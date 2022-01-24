package de.fantasyrealms.domain.cards.wild

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.artifact.BookOfChanges
import de.fantasyrealms.domain.cards.beast.Dragon
import de.fantasyrealms.domain.cards.flame.FireElemental
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.leader.Princess
import de.fantasyrealms.domain.cards.weapon.MagicWand
import de.fantasyrealms.domain.cards.wizard.Beastmaster
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ShapeShifterUnitTest {

    @Nested
    inner class `mimicing` {

        @Test
        fun `takes on Name`() {
            val magicWand = MagicWand()
            val shapeShifter = Shapeshifter(magicWand)

            assertThat(shapeShifter.name).isEqualTo(magicWand.name)
        }

        @Test
        fun `does not take on Bonuses`() {
            val shapeShifter = Shapeshifter(MagicWand()) // MagicWand gives +25 if with Wizard
            val beastmaster = Beastmaster()
            val hand = handOf(shapeShifter, beastmaster) // we're not taking bonuses!

            val score = hand.getTotalScore()[shapeShifter]

            assertThat(score).isEqualTo(0)
        }

        @Test
        fun `does not take on Penalties`() {
            val shapeShifter = Shapeshifter(Dragon()) // -40 if without a wizard
            val hand = handOf(shapeShifter)

            val score = hand.getTotalScore()[shapeShifter]

            assertThat(score).isEqualTo(0)
        }

        @Test
        fun `throws if Suit is not supported`() {
            // Beasts are not supported
            assertThat { Shapeshifter(FireElemental()) }.isFailure().isInstanceOf(IllegalArgumentException::class)
        }

        @Nested
        inner class `suit` {

            @Test
            fun `if ARTIFACT`() {
                val shapeShifter = Shapeshifter(BookOfChanges(Knights(), Suit.FLOOD))

                assertThat(shapeShifter.suit).isEqualTo(Suit.ARTIFACT)
            }

            @Test
            fun `if LEADER`() {
                val shapeShifter = Shapeshifter(Princess())

                assertThat(shapeShifter.suit).isEqualTo(Suit.LEADER)
            }

            @Test
            fun `if WIZARD`() {
                val shapeShifter = Shapeshifter(Beastmaster())

                assertThat(shapeShifter.suit).isEqualTo(Suit.WIZARD)
            }

            @Test
            fun `if WEAPON`() {
                val shapeShifter = Shapeshifter(MagicWand())

                assertThat(shapeShifter.suit).isEqualTo(Suit.WEAPON)
            }

            @Test
            fun `if BEAST`() {
                val shapeShifter = Shapeshifter(Dragon())

                assertThat(shapeShifter.suit).isEqualTo(Suit.BEAST)
            }
        }
    }
}
