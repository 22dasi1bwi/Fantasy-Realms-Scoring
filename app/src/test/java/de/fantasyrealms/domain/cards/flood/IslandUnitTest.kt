package de.fantasyrealms.domain.cards.flood

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import de.fantasyrealms.domain.cards.beast.Warhorse
import de.fantasyrealms.domain.cards.flame.Forge
import de.fantasyrealms.domain.cards.flame.Wildfire
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.weapon.MagicWand
import de.fantasyrealms.domain.cards.wizard.Beastmaster
import org.junit.jupiter.api.Test

class IslandUnitTest {

    @Test
    fun `returns base score`() {
        val island = Island(Wildfire())
        val hand = handOf(island, MagicWand())

        val score = hand.getTotalScore()[island]

        assertThat(score).isEqualTo(island.baseScore)
    }

    @Test
    fun `throws if targetChoice is neither Suit#FLAME nor Suit#FLOOD`() {
        val magicWand = MagicWand()

        assertThat { Island(magicWand) }.isFailure().isInstanceOf(IllegalArgumentException::class)
    }

    @Test
    fun `clears Penalty on Flood`() {
        val swamp = Swamp() // penalty -3 for forge should be cleared
        val island = Island(swamp)
        val forge = Forge()
        val hand = handOf(island, swamp, forge)

        val score = hand.getTotalScore()[swamp]

        assertThat(score).isEqualTo(swamp.baseScore)
    }

    @Test
    fun `clears Penalty on Flame`() {
        val wildfire = Wildfire() // blanks Warhorse
        val island = Island(wildfire)
        val warhorse = Warhorse() // +14 for wizard (Beastmaster)
        val beastmaster = Beastmaster()
        val hand = handOf(island, warhorse, wildfire, beastmaster)

        val score = hand.getTotalScore()[warhorse]

        assertThat(score).isEqualTo(warhorse.baseScore + 14)
    }
}
