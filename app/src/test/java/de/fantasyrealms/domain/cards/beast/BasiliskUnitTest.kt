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
        val hand = handOf(Basilisk, Knights)

        val score = hand.getScore(Basilisk)

        assertThat(score).isEqualTo(Basilisk.baseScore)
    }

    @Test
    fun `blanks all Armies`() {
        val hand = handOf(Basilisk, LightCavalry)

        val score = hand.getTotalScore()

        // total score should not contains LightCavalry's score, since it has been blanked
        assertThat(score).isEqualTo(Basilisk.baseScore)
    }

    @Test
    fun `blanks all Leaders`() {
        val hand = handOf(Basilisk, Empress)

        val score = hand.getTotalScore()

        // total score should not contains Empress's score, since it has been blanked
        assertThat(score).isEqualTo(Basilisk.baseScore)
    }

    @Test
    fun `blanks all other Beasts`() {
        val hand = handOf(Basilisk, Warhorse)

        val score = hand.getTotalScore()

        // total score should not contains Warhorse's score, since it has been blanked
        assertThat(score).isEqualTo(Basilisk.baseScore)
    }
}
