package de.fantasyrealms.domain.cards.leader

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.army.LightCavalry
import de.fantasyrealms.domain.cards.flame.FireElemental
import de.fantasyrealms.domain.cards.handOf
import org.junit.jupiter.api.Test

class KingUnitTest {

    @Test
    fun `returns base score when without any ARMY`() {
        val king = King()
        val hand = handOf(king, FireElemental())

        val score = hand.getTotalScore()[king]

        assertThat(score).isEqualTo(king.baseScore)
    }

    @Test
    fun `+5 for each ARMY when without Queen`() {
        val king = King()
        val hand = handOf(king, Knights(), LightCavalry())

        val score = hand.getTotalScore()[king]

        assertThat(score).isEqualTo(king.baseScore + 5 + 5)
    }

    @Test
    fun `+20 for each ARMY when with Queen`() {
        val king = King()
        val hand = handOf(king, Knights(), LightCavalry(), Queen())

        val score = hand.getTotalScore()[king]

        assertThat(score).isEqualTo(king.baseScore + 20 + 20)
    }
}
