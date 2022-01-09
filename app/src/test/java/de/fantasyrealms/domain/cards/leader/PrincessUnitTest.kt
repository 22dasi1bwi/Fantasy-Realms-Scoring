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
        val hand = handOf(Princess, Knights)

        val score = hand.getScore(Princess)

        assertThat(score).isEqualTo(Princess.baseScore + 8)
    }

    @Test
    fun `+8 for each Wizard`() {
        val hand = handOf(Princess, Beastmaster)

        val score = hand.getScore(Princess)

        assertThat(score).isEqualTo(Princess.baseScore + 8)
    }

    @Test
    fun `+8 for each other Leader`() {
        val hand = handOf(Princess, Empress)

        val score = hand.getScore(Princess)

        assertThat(score).isEqualTo(Princess.baseScore + 8)
    }

    @Test
    fun `multiple gains for different suits`() {
        val hand = handOf(Princess, Empress, Beastmaster, Knights)

        val score = hand.getScore(Princess)

        assertThat(score).isEqualTo(Princess.baseScore + 8 + 8 + 8)
    }

    @Test
    fun `no additional gains for itself`() {
        val hand = handOf(Princess)

        val score = hand.getScore(Princess)

        assertThat(score).isEqualTo(Princess.baseScore)
    }

    @Test
    fun `no gain when no other Leader, Wizard or Army are present`() {
        val hand = handOf(Princess, Forge)

        val score = hand.getScore(Princess)

        assertThat(score).isEqualTo(Princess.baseScore)
    }
}
