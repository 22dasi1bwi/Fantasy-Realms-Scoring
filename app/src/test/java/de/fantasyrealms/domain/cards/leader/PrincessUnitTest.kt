package de.fantasyrealms.domain.cards.leader

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.flame.Forge
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.wizard.Beastmaster
import org.junit.jupiter.api.Test

class PrincessUnitTest {

    @Test
    fun `+8 for each Army`() {
        val princess = Princess()
        val hand = handOf(princess, Knights())

        val score = hand.getTotalScore()[princess]

        assertThat(score).isEqualTo(princess.baseScore + 8)
    }

    @Test
    fun `+8 for each Wizard`() {
        val princess = Princess()
        val hand = handOf(princess, Beastmaster())

        val score = hand.getTotalScore()[princess]

        assertThat(score).isEqualTo(princess.baseScore + 8)
    }

    @Test
    fun `+8 for each other Leader`() {
        val princess = Princess()
        val hand = handOf(princess, Empress())

        val score = hand.getTotalScore()[princess]

        assertThat(score).isEqualTo(princess.baseScore + 8)
    }

    @Test
    fun `multiple gains for different suits`() {
        val princess = Princess()
        val hand = handOf(princess, Empress(), Beastmaster(), Knights())

        val score = hand.getTotalScore()[princess]

        assertThat(score).isEqualTo(princess.baseScore + 8 + 8 + 8)
    }

    @Test
    fun `no additional gains for itself`() {
        val princess = Princess()
        val hand = handOf(princess)

        val score = hand.getTotalScore()[princess]

        assertThat(score).isEqualTo(princess.baseScore)
    }

    @Test
    fun `no gain when no other Leader, Wizard or Army are present`() {
        val princess = Princess()
        val hand = handOf(princess, Forge())

        val score = hand.getTotalScore()[princess]

        assertThat(score).isEqualTo(princess.baseScore)
    }
}
