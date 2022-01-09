package de.fantasyrealms.domain.cards.flame

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.artifact.ProtectionRune
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.weapon.MagicWand
import org.junit.jupiter.api.Test

class ForgeUnitTest {

    @Test
    fun `returns base score`() {
        val forge = Forge()

        val hand = handOf(forge, Knights())

        val score = hand.getTotalScore()[forge]

        assertThat(score).isEqualTo(forge.baseScore)
    }

    @Test
    fun `+9 for each Weapon`() {
        val forge = Forge()

        val hand = handOf(forge, MagicWand())

        val score = hand.getTotalScore()[forge]

        assertThat(score).isEqualTo(forge.baseScore + 9)
    }

    @Test
    fun `+9 for each Artifact`() {
        val forge = Forge()

        val hand = handOf(forge, ProtectionRune())

        val score = hand.getTotalScore()[forge]

        assertThat(score).isEqualTo(forge.baseScore + 9)
    }

    @Test
    fun `multiple gains from different Suits`() {
        val forge = Forge()

        val hand = handOf(forge, MagicWand(), ProtectionRune())

        val score = hand.getTotalScore()[forge]

        assertThat(score).isEqualTo(forge.baseScore + 9 + 9)
    }
}
