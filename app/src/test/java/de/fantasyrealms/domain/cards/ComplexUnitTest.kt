package de.fantasyrealms.domain.cards

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.flame.Wildfire
import de.fantasyrealms.domain.cards.flood.GreatFlood
import de.fantasyrealms.domain.cards.land.Cavern
import de.fantasyrealms.domain.cards.weather.Blizzard
import org.junit.jupiter.api.Test

/** OFFICIAL documentation:
 * There are rare circumstances where a chain of cards will affect one another.
 * In these cases, first decide what the Doppelgänger, Mirage, and Shapeshifter
 * are, in that order. Then use the Book of Changes. Then follow any instructions
 * which call for the clearing of a penalty or part of a penalty. Finally, apply all
 * penalties, beginning with cards not blanked by any other cards.
 **/

class ComplexUnitTest {

    /**
     * EXAMPLE: You have a Blizzard (blanks Floods), a Great Flood (blanks Flames and
     * Lands), a Wildfire (blanks Lands), and Cavern (clears penalties on Weather). The
     * Cavern clears the penalty from the Blizzard first. Since the penalty is cleared, the Great
     * Flood is unaffected by the Blizzard, so it quenches the Wildfire and drowns the Cavern.
     * However, even though they are blanked, the Cavern still has successfully cleared the
     * Blizzard penalty. The active cards in the hand are the Blizzard and the Flood.
     */
    @Test
    fun `complex Weather scenario`() {
        val blizzard = Blizzard() // Cleared by Cavern, does not blank Great Flood
        val greatFlood = GreatFlood() // not blanked by Blizzard, blanks Cavern
        val wildfire = Wildfire() // blanked by GreatFlood
        val cavern = Cavern() // clears Blizzard, blanked by GreatFlood
        val hand = handOf(blizzard, greatFlood, wildfire, cavern)

        val score = hand.getTotalScore().raw

        assertThat(score).isEqualTo(blizzard.baseScore + greatFlood.baseScore)
    }

    /**
     * Same as above, but without Cavern.
     * The Flood would be blanked, thus making Blizzard and Wildfire both be active.
     **/
    @Test
    fun `complex Weather scenario without Cavern`() {
        val blizzard = Blizzard() // -5 for Wildfire
        val greatFlood = GreatFlood() // blanked by Blizzard
        val wildfire = Wildfire() // not blanked by GreatFlood
        val hand = handOf(blizzard, greatFlood, wildfire)

        val score = hand.getTotalScore().raw

        assertThat(score).isEqualTo(blizzard.baseScore - 5 + wildfire.baseScore)
    }
}