package de.fantasyrealms.domain.cards.beast

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.army.LightCavalry
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.leader.Empress
import org.junit.jupiter.api.Test

class BasiliskUnitTest {

    @Test
    fun `returns base score`() {
        val basilisk = Basilisk()
        val hand = handOf(basilisk, Knights())

        val score = hand.getTotalScore()[basilisk]

        assertThat(score).isEqualTo(basilisk.baseScore)
    }

    @Test
    fun `blanks all Armies`() {
        val basilisk = Basilisk()
        val hand = handOf(basilisk, LightCavalry())

        val score = hand.getTotalScore().raw

        // total score should not contains LightCavalry's score, since it has been blanked
        assertThat(score).isEqualTo(basilisk.baseScore)
    }

    @Test
    fun `blanks all Leaders`() {
        val basilisk = Basilisk()
        val hand = handOf(basilisk, Empress())

        val score = hand.getTotalScore().raw

        // total score should not contains Empress's score, since it has been blanked
        assertThat(score).isEqualTo(basilisk.baseScore)
    }

    @Test
    fun `blanks all other Beasts`() {
        val basilisk = Basilisk()
        val hand = handOf(basilisk, Warhorse())

        val score = hand.getTotalScore().raw

        // total score should not contains Warhorse's score, since it has been blanked
        assertThat(score).isEqualTo(basilisk.baseScore)
    }
}
