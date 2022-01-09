package de.fantasyrealms.domain.cards.weather

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.beast.Dragon
import de.fantasyrealms.domain.cards.flame.FireElemental
import de.fantasyrealms.domain.cards.flood.Swamp
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.leader.Empress
import de.fantasyrealms.domain.cards.weapon.MagicWand
import org.junit.jupiter.api.Test

class BlizzardUnitTest {

    @Test
    fun `returns base score`() {
        val blizzard = Blizzard()
        val hand = handOf(blizzard, MagicWand())

        val score = hand.getTotalScore()[blizzard]

        assertThat(score).isEqualTo(blizzard.baseScore)
    }

    @Test
    fun `-5 for each Army`() {
        val blizzard = Blizzard()
        val hand = handOf(blizzard, Knights())

        val score = hand.getTotalScore()[blizzard]

        assertThat(score).isEqualTo(blizzard.baseScore - 5)
    }

    @Test
    fun `-5 for each Flame`() {
        val blizzard = Blizzard()
        val hand = handOf(blizzard, FireElemental())

        val score = hand.getTotalScore()[blizzard]

        assertThat(score).isEqualTo(blizzard.baseScore - 5)
    }

    @Test
    fun `-5 for each Beast`() {
        val blizzard = Blizzard()
        val hand = handOf(blizzard, Dragon())

        val score = hand.getTotalScore()[blizzard]

        assertThat(score).isEqualTo(blizzard.baseScore - 5)
    }

    @Test
    fun `-5 for each Leader`() {
        val blizzard = Blizzard()
        val hand = handOf(blizzard, Empress())

        val score = hand.getTotalScore()[blizzard]

        assertThat(score).isEqualTo(blizzard.baseScore - 5)
    }

    @Test
    fun `blanks all Floods`() {
        val blizzard = Blizzard()
        val swamp = Swamp()
        val hand = handOf(blizzard, swamp)

        hand.getTotalScore()

        assertThat(swamp.blanked).isTrue()
    }
}
