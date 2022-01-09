package de.fantasyrealms.domain.cards.beast

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.flame.FireElemental
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.leader.Empress
import de.fantasyrealms.domain.cards.leader.Princess
import de.fantasyrealms.domain.cards.leader.Queen
import de.fantasyrealms.domain.cards.wizard.Enchantress
import org.junit.jupiter.api.Test

class UnicornUnitTest {

    @Test
    fun `returns base score when neither with Princess nor Enchantress, Queen or Empress`() {
        val unicorn = Unicorn()
        val hand = handOf(unicorn, FireElemental())

        val score = hand.getTotalScore()[unicorn]

        assertThat(score).isEqualTo(unicorn.baseScore)
    }

    @Test
    fun `+ 30 when with Princess ignoring Bonus from Enchantress, Queen or Empress`() {
        val unicorn = Unicorn()
        val hand = handOf(unicorn, Princess(), Empress(), Queen(), Enchantress())

        val score = hand.getTotalScore()[unicorn]

        assertThat(score).isEqualTo(unicorn.baseScore + 30) // bonus from Princess only!
    }

    @Test
    fun `+ 15 with Enchantress`() {
        val unicorn = Unicorn()
        val hand = handOf(unicorn, Enchantress())

        val score = hand.getTotalScore()[unicorn]

        assertThat(score).isEqualTo(unicorn.baseScore + 15)
    }
    @Test
    fun `+ 15 with Queen`() {
        val unicorn = Unicorn()
        val hand = handOf(unicorn, Queen())

        val score = hand.getTotalScore()[unicorn]

        assertThat(score).isEqualTo(unicorn.baseScore + 15)
    }
    @Test
    fun `+ 15 with Empress`() {
        val unicorn = Unicorn()
        val hand = handOf(unicorn, Empress())

        val score = hand.getTotalScore()[unicorn]

        assertThat(score).isEqualTo(unicorn.baseScore + 15)
    }
}
