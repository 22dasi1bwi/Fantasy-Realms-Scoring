package de.fantasyrealms.domain.cards.weather

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.flame.Forge
import de.fantasyrealms.domain.cards.flame.Lightning
import de.fantasyrealms.domain.cards.flood.Island
import de.fantasyrealms.domain.cards.flood.WaterElemental
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.weapon.MagicWand
import org.junit.jupiter.api.Test

class RainstormUnitTest {

    @Test
    fun `returns base score`() {
        val rainstorm = Rainstorm()
        val hand = handOf(rainstorm, Knights())

        val score = hand.getTotalScore()[rainstorm]

        assertThat(score).isEqualTo(rainstorm.baseScore)
    }

    @Test
    fun `+10 for each other Flood`() {
        val rainstorm = Rainstorm()
        val waterElemental = WaterElemental()
        val hand = handOf(rainstorm, waterElemental, Island(waterElemental))

        val score = hand.getTotalScore()[rainstorm]

        assertThat(score).isEqualTo(rainstorm.baseScore + 10 + 10)
    }

    @Test
    fun `blanks all Flames except 'Lightning'`() {
        val rainstorm = Rainstorm()
        val forge = Forge() // blanked
        val magicWand = MagicWand()
        val lightning = Lightning() // not blanked, + bonus being with Rainstorm
        val hand = handOf(rainstorm, forge, magicWand, lightning)

        val score = hand.getTotalScore().raw

        assertThat(score).isEqualTo(rainstorm.baseScore + magicWand.baseScore + lightning.baseScore + 30)
    }
}