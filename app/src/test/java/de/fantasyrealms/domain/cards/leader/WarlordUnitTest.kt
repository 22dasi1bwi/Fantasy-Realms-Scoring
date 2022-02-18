package de.fantasyrealms.domain.cards.leader

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.army.*
import de.fantasyrealms.domain.cards.flame.FireElemental
import de.fantasyrealms.domain.cards.flood.WaterElemental
import de.fantasyrealms.domain.cards.handOf
import org.junit.jupiter.api.Test

class WarlordUnitTest {

    @Test
    fun `returns base score when without any other Army`() {
        val warlord = Warlord()
        val hand = handOf(warlord, FireElemental(), WaterElemental())

        val score = hand.getTotalScore()[warlord]

        assertThat(score).isEqualTo(warlord.baseScore)
    }

    @Test
    fun `gains equal to each others Army's base score`() {
        val warlord = Warlord()
        val dwarvishInfantry = DwarvishInfantry() // + 15
        val elvenArchers = ElvenArchers() // + 10
        val knights = Knights() // + 20
        val lightCavalry = LightCavalry() // + 17
        val rangers = Rangers() // + 5
        val hand = handOf(warlord, dwarvishInfantry, elvenArchers, knights, lightCavalry, rangers)

        val score = hand.getTotalScore()[warlord]

        assertThat(score)
            .isEqualTo(
                warlord.baseScore
                        + dwarvishInfantry.baseScore
                        + elvenArchers.baseScore
                        + knights.baseScore
                        + lightCavalry.baseScore
                        + rangers.baseScore
            )
    }
}