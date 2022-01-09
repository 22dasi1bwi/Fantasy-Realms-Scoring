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
        val beastmaster = Beastmaster()
        val hand = handOf(beastmaster, Dragon(), Warhorse())

        val score = hand.getTotalScore()[beastmaster]

        assertThat(score).isEqualTo(beastmaster.baseScore + 9 + 9)
    }

    @Test
    fun `clears the penalty on all Beasts`() {
        val beastmaster = Beastmaster()
        val basilisk = Basilisk()
        val lightCavalry = LightCavalry() // normally blanked by Basilisk
        val hand = handOf(basilisk, beastmaster, lightCavalry)

        val lightCavalryScore = hand.getTotalScore()[lightCavalry] // should not be blanked

        assertThat(lightCavalryScore).isEqualTo(lightCavalry.baseScore)
    }
}