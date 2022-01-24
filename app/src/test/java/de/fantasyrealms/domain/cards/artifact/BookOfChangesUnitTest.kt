package de.fantasyrealms.domain.cards.artifact

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.cards.army.Knights
import de.fantasyrealms.domain.cards.flame.Forge
import de.fantasyrealms.domain.cards.flood.Swamp
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.weapon.MagicWand
import org.junit.jupiter.api.Test

class BookOfChangesUnitTest {

    @Test
    fun `transforms Suit of one other card`() {
        val knights = Knights()
        BookOfChanges(knights, Suit.FLOOD)

        assertThat(knights.suit).isEqualTo(Suit.FLOOD)
    }

    @Test
    fun `does not transform Name of an other card`() {
        val knights = Knights()
        BookOfChanges(knights, Suit.FLOOD)

        assertThat(knights.name).isEqualTo(knights.name)
    }

    @Test
    fun `does not transform Penalties of an other card`() {
        val swamp = Swamp()
        val forge = Forge()
        val bookOfChanges = BookOfChanges(swamp, Suit.FLAME)
        val hand = handOf(swamp, bookOfChanges, forge)

        val score = hand.getTotalScore().raw

        assertThat(score).isEqualTo(
            bookOfChanges.baseScore +
                    swamp.baseScore +
                    -3 + // penalty of swamp for other flames (forge)
                    forge.baseScore +
                    9 // + 9 for swamp (transformed to flame)
        )
    }

    @Test
    fun `does not transform Bonuses of an other card`() {
        val forge = Forge()
        val bookOfChanges = BookOfChanges(forge, Suit.WIZARD)
        val magicWand = MagicWand()
        // BookOfChanges does not receive +9 for the Forge's bonus for every other weapon
        val hand = handOf(bookOfChanges, forge, magicWand)

        val score = hand.getTotalScore().raw

        assertThat(score).isEqualTo(
            bookOfChanges.baseScore +
                    magicWand.baseScore +
                    25 + // Forge is now a Wizard, thus giving Magic Wand its bonus
                    forge.baseScore
                    + 9 // Forge gains + 9 from MagicWand. Important: BookOfChanges does not!
                    + 9 // Forge gains + 9 for BookOfChanges (artifact)
        )
    }
}