package de.fantasyrealms.domain.cards.wizard

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.flame.FireElemental
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.leader.Empress
import de.fantasyrealms.domain.cards.leader.Princess
import org.junit.jupiter.api.Test

class WarlockLordUnitTest {

    @Test
    fun `returns base score when without Leader or other Wizard`() {
        val warlockLord = WarlockLord()
        val hand = handOf(warlockLord, FireElemental())

        val score = hand.getTotalScore()[warlockLord]

        assertThat(score).isEqualTo(warlockLord.baseScore)
    }

    @Test
    fun `-10 for each other Leader and Wizard`() {
        val warlockLord = WarlockLord()
        val hand = handOf(warlockLord, Empress(), Princess(), Beastmaster(), Enchantress())

        val score = hand.getTotalScore()[warlockLord]

        assertThat(score).isEqualTo(warlockLord.baseScore -10 -10 -10 -10)
    }
}
