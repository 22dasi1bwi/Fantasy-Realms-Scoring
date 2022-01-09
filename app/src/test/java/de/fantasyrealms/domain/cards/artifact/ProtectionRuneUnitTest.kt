package de.fantasyrealms.domain.cards.artifact

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.beast.Dragon
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.leader.Empress
import de.fantasyrealms.domain.cards.wizard.Beastmaster
import org.junit.jupiter.api.Test

class ProtectionRuneUnitTest {

    @Test
    fun `returns base score`() {
        val protectionRune = ProtectionRune()
        val hand = handOf(protectionRune, Beastmaster())

        val score = hand.getTotalScore()[protectionRune]

        assertThat(score).isEqualTo(protectionRune.baseScore)
    }

    @Test
    fun `clears Penalties on all cards in hand`() {
        val protectionRune = ProtectionRune()
        val knights = Knights() // PENALTY: ARMY; -8 unless with Leader
        val dragon = Dragon() // PENALTY: BEAST; -40 when without Wizard
        val hand = handOf(protectionRune, knights, dragon, Empress())

        val score = hand.getTotalScore().raw

        assertThat(score).isEqualTo(protectionRune.baseScore + dragon.baseScore + knights.baseScore + 25)
    }
}