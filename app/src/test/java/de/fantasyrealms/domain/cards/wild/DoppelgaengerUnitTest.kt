package de.fantasyrealms.domain.cards.wild

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.leader.Empress
import de.fantasyrealms.domain.cards.leader.Princess
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DoppelgaengerUnitTest {

    @Nested
    inner class `mimicing` {

        @Test
        fun `takes on Name`() {
            val empress = Empress()
            val doppelgaengerAsEmpress = Doppelgaenger.mimic(empress)

            assertThat(doppelgaengerAsEmpress.name).isEqualTo(empress.name)
        }

        @Test
        fun `takes on Suit`() {
            val empress = Empress()
            val doppelgaengerAsEmpress = Doppelgaenger.mimic(empress)

            assertThat(doppelgaengerAsEmpress.suit).isEqualTo(empress.suit)
        }

        @Test
        fun `takes on base score`() {
            val empress = Empress()
            val doppelgaengerAsEmpress = Doppelgaenger.mimic(empress)

            assertThat(doppelgaengerAsEmpress.baseScore).isEqualTo(empress.baseScore)
        }

        @Test
        fun `takes on Penalty`() {
            val empress = Empress()
            val princess = Princess()
            val doppelgaengerAsEmpress = Doppelgaenger.mimic(empress) // -5 penalty for every other Leader
            val hand = handOf(doppelgaengerAsEmpress, princess)

            val score = hand.getTotalScore()[doppelgaengerAsEmpress]

            assertThat(score).isEqualTo(doppelgaengerAsEmpress.baseScore - 5)
        }

        @Test
        fun `does not take on Bonuses`() {
            val empress = Empress()
            val knights = Knights()
            val doppelgaengerAsEmpress = Doppelgaenger.mimic(empress) // does not gain +10 for armies
            val hand = handOf(doppelgaengerAsEmpress, knights)

            val score = hand.getTotalScore()[doppelgaengerAsEmpress]

            assertThat(score).isEqualTo(doppelgaengerAsEmpress.baseScore)
        }
    }
}
