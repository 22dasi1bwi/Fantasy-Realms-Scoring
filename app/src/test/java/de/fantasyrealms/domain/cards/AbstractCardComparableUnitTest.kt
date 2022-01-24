package de.fantasyrealms.domain.cards

import assertk.assertThat
import assertk.assertions.containsExactly
import de.fantasyrealms.domain.cards.artifact.BookOfChanges
import de.fantasyrealms.domain.cards.flame.Forge
import de.fantasyrealms.domain.cards.flood.GreatFlood
import de.fantasyrealms.domain.cards.leader.Empress
import de.fantasyrealms.domain.cards.weapon.MagicWand
import de.fantasyrealms.domain.cards.wild.Doppelgaenger
import de.fantasyrealms.domain.cards.wild.Mirage
import de.fantasyrealms.domain.cards.wild.Shapeshifter
import de.fantasyrealms.domain.cards.wizard.Beastmaster
import org.junit.jupiter.api.Test

class AbstractCardComparableUnitTest {

    @Test
    fun `Doppelgaenger has highest priority`() {
        val doppelgaenger = Doppelgaenger.mimic(Empress())
        val beastmaster = Beastmaster()

        val hand = handOf(beastmaster, doppelgaenger)

        assertThat(hand.ordered).containsExactly(doppelgaenger, beastmaster)
    }

    @Test
    fun `Mirage has second to highest priority`() {
        val doppelgaenger = Doppelgaenger.mimic(Empress())
        val mirage = Mirage(Forge())

        val hand = handOf(mirage, doppelgaenger)

        assertThat(hand.ordered).containsExactly(doppelgaenger, mirage)
    }

    @Test
    fun `Shapeshifter has third to highest priority`() {
        val mirage = Mirage(Forge())
        val shapeshifter = Shapeshifter(MagicWand())

        val hand = handOf(shapeshifter, mirage)

        assertThat(hand.ordered).containsExactly(mirage, shapeshifter)
    }

    @Test
    fun `Book Of Changes has fourth to highest priority`() {
        val shapeshifter = Shapeshifter(MagicWand())
        val bookOfChanges = BookOfChanges(shapeshifter, Suit.WILD)

        val hand = handOf(bookOfChanges, shapeshifter)

        assertThat(hand.ordered).containsExactly(shapeshifter, bookOfChanges)
    }

    @Test
    fun `Clearing effects have fifth to highest priority`() {
        val beastmaster = Beastmaster()
        val bookOfChanges = BookOfChanges(beastmaster, Suit.WIZARD)

        val hand = handOf(beastmaster, bookOfChanges)

        assertThat(hand.ordered).containsExactly(bookOfChanges, beastmaster)
    }

    @Test
    fun `Penalties from cards not blanked have sixth to highest priority`() {
        val beastmaster = Beastmaster()
        val greatFlood = GreatFlood()
        val empress = Empress()

        val hand = handOf(greatFlood, empress, beastmaster)

        assertThat(hand.ordered).containsExactly(beastmaster, empress, greatFlood)
    }
}
