package de.fantasyrealms.domain.cards.weapon

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.artifact.ShieldOfKeth
import de.fantasyrealms.domain.cards.flame.FireElemental
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.leader.Empress
import de.fantasyrealms.domain.cards.leader.Princess
import org.junit.jupiter.api.Test

class SwordOfKethUnitTest {

    @Test
    fun `returns base score when without Leader and without Shield Of Keth`() {
        val swordOfKeth = SwordOfKeth()
        val hand = handOf(swordOfKeth, FireElemental())

        val score = hand.getTotalScore()[swordOfKeth]

        assertThat(score).isEqualTo(swordOfKeth.baseScore)
    }

    @Test
    fun `+10 when with at least one Leader but without Shield Of Keth`() {
        val swordOfKeth = SwordOfKeth()
        val hand = handOf(swordOfKeth, Princess(), Empress())

        val score = hand.getTotalScore()[swordOfKeth]

        assertThat(score).isEqualTo(swordOfKeth.baseScore + 10)
    }

    @Test
    fun `+40 when with at least one Leader and with Shield Of Keth`() {
        val swordOfKeth = SwordOfKeth()
        val hand = handOf(swordOfKeth, Princess(), Empress(), ShieldOfKeth())

        val score = hand.getTotalScore()[swordOfKeth]

        assertThat(score).isEqualTo(swordOfKeth.baseScore + 40)
    }
}
