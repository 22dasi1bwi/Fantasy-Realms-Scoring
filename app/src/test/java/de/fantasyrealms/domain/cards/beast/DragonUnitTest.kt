package de.fantasyrealms.domain.cards.beast

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.wizard.Beastmaster
import org.junit.jupiter.api.Test

class DragonUnitTest {

    @Test
    fun `returns base score when with at least one Wizard`() {
        val dragon = Dragon()
        val hand = handOf(dragon, Beastmaster())

        val score = hand.getTotalScore()[dragon]

        assertThat(score).isEqualTo(dragon.baseScore)
    }

    @Test
    fun `-40 when without a Wizard`() {
        val dragon = Dragon()
        val hand = handOf(dragon, Knights())

        val score = hand.getTotalScore()[dragon]

        assertThat(score).isEqualTo(dragon.baseScore - 40)
    }
}