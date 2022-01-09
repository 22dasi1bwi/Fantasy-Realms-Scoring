package de.fantasyrealms.domain.cards.wizard

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.army.LightCavalry
import de.fantasyrealms.domain.cards.beast.Basilisk
import de.fantasyrealms.domain.cards.beast.Dragon
import de.fantasyrealms.domain.cards.beast.Warhorse
import de.fantasyrealms.domain.cards.handOf
import org.junit.jupiter.api.Test

class BeastmasterUnitTest {

    @Test
    fun `+9 for each Beast`() {
        val hand = handOf(Beastmaster, Dragon, Warhorse)

        val score = hand.getScore(Beastmaster)

        assertThat(score).isEqualTo(Beastmaster.baseScore + 9 + 9)
    }

    @Test
    fun `clears the penalty on all Beasts`() {
        // Basilisk blanks all Armies, Leaders and other Beasts
        // Beastmaster's effect should clear that
        val hand = handOf(Beastmaster, Basilisk, LightCavalry)

        val score = hand.getTotalScore()

        assertThat(score).isEqualTo(Beastmaster.baseScore + 9 + Basilisk.baseScore + LightCavalry.baseScore)
    }
}