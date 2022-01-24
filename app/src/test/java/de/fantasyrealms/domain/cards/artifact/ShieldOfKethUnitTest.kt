package de.fantasyrealms.domain.cards.artifact

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.flame.FireElemental
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.leader.Empress
import de.fantasyrealms.domain.cards.leader.Princess
import de.fantasyrealms.domain.cards.weapon.SwordOfKeth
import org.junit.jupiter.api.Test

class ShieldOfKethUnitTest {

    @Test
    fun `returns base score when without Leader and without Sword Of Keth`() {
        val shieldOfKeth = ShieldOfKeth()
        val hand = handOf(shieldOfKeth, FireElemental())

        val score = hand.getTotalScore()[shieldOfKeth]

        assertThat(score).isEqualTo(shieldOfKeth.baseScore)
    }

    @Test
    fun `+15 when with at least one Leader but without Sword Of Keth`() {
        val shieldOfKeth = ShieldOfKeth()
        val hand = handOf(shieldOfKeth, Princess(), Empress())

        val score = hand.getTotalScore()[shieldOfKeth]

        assertThat(score).isEqualTo(shieldOfKeth.baseScore + 15)
    }

    @Test
    fun `+40 when with at least one Leader and with Sword Of Keth`() {
        val shieldOfKeth = ShieldOfKeth()
        val hand = handOf(shieldOfKeth, Princess(), Empress(), SwordOfKeth())

        val score = hand.getTotalScore()[shieldOfKeth]

        assertThat(score).isEqualTo(shieldOfKeth.baseScore + 40)
    }
}
