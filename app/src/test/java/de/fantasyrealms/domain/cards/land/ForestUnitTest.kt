package de.fantasyrealms.domain.cards.land

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.army.ElvenArchers
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.beast.Dragon
import de.fantasyrealms.domain.cards.beast.Warhorse
import de.fantasyrealms.domain.cards.handOf
import org.junit.jupiter.api.Test

class ForestUnitTest {

    @Test
    fun `returns base score`() {
        val forest = Forest()
        val hand = handOf(forest, Knights())

        val score = hand.getTotalScore()[forest]

        assertThat(score).isEqualTo(forest.baseScore)
    }

    @Test
    fun `+12 for each Beast`() {
        val forest = Forest()
        val hand = handOf(forest, Dragon(), Warhorse())

        val score = hand.getTotalScore()[forest]

        assertThat(score).isEqualTo(forest.baseScore + 12 + 12)
    }

    @Test
    fun `+12 for each Elven Archers`() {
        val forest = Forest()
        val hand = handOf(forest, ElvenArchers(), ElvenArchers())

        val score = hand.getTotalScore()[forest]

        assertThat(score).isEqualTo(forest.baseScore + 12 + 12)
    }

    @Test
    fun `+12 for each Elven Archers and Beast`() {
        val forest = Forest()
        val hand = handOf(forest, Warhorse(), ElvenArchers(), ElvenArchers())

        val score = hand.getTotalScore()[forest]

        assertThat(score).isEqualTo(forest.baseScore + 12 + 12 + 12)
    }
}