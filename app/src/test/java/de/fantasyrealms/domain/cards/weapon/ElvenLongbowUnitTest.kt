package de.fantasyrealms.domain.cards.weapon

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.army.ElvenArchers
import de.fantasyrealms.domain.cards.flame.FireElemental
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.leader.Warlord
import de.fantasyrealms.domain.cards.wizard.Beastmaster
import org.junit.jupiter.api.Test

class ElvenLongbowUnitTest {

    @Test
    fun `returns base score when without Elven Archers, Warlord or Beastmaster`() {
        val elvenLongbow = ElvenLongbow()
        val hand = handOf(elvenLongbow, FireElemental())

        val score = hand.getTotalScore()[elvenLongbow]

        assertThat(score).isEqualTo(elvenLongbow.baseScore)
    }

    @Test
    fun `+30 when with Elven Archers`() {
        val elvenLongbow = ElvenLongbow()
        val hand = handOf(elvenLongbow, ElvenArchers())

        val score = hand.getTotalScore()[elvenLongbow]

        assertThat(score).isEqualTo(elvenLongbow.baseScore + 30)
    }

    @Test
    fun `+30 when with Warlord`() {
        val elvenLongbow = ElvenLongbow()
        val hand = handOf(elvenLongbow, Warlord())

        val score = hand.getTotalScore()[elvenLongbow]

        assertThat(score).isEqualTo(elvenLongbow.baseScore + 30)
    }

    @Test
    fun `+30 when with Beastmaster`() {
        val elvenLongbow = ElvenLongbow()
        val hand = handOf(elvenLongbow, Beastmaster())

        val score = hand.getTotalScore()[elvenLongbow]

        assertThat(score).isEqualTo(elvenLongbow.baseScore + 30)
    }

    @Test
    fun `no multiple gains when with multiple cards triggering the effect`() {
        val elvenLongbow = ElvenLongbow()
        val hand = handOf(elvenLongbow, Warlord(), ElvenArchers())

        val score = hand.getTotalScore()[elvenLongbow]

        assertThat(score).isEqualTo(elvenLongbow.baseScore + 30)
    }
}
