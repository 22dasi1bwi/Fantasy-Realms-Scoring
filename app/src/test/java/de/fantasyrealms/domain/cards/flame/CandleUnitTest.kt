package de.fantasyrealms.domain.cards.flame

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.Suit
import de.fantasyrealms.domain.cards.artifact.BookOfChanges
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.land.BellTower
import de.fantasyrealms.domain.cards.wizard.Beastmaster
import org.junit.jupiter.api.Test

class CandleUnitTest {

    @Test
    fun `returns base score when not with Book Of Changes, Bell Tower and any Wizard`() {
        val candle = Candle()
        val hand = handOf(candle, BookOfChanges(candle, Suit.ARTIFACT), Beastmaster())

        val score = hand.getTotalScore()[candle]

        assertThat(score).isEqualTo(candle.baseScore)
    }

    @Test
    fun `+100 when with Book Of Changes, Bell Tower and at least one Wizard`() {
        val candle = Candle()
        val hand = handOf(candle, BookOfChanges(candle, Suit.ARTIFACT), Beastmaster(), BellTower())

        val score = hand.getTotalScore()[candle]

        assertThat(score).isEqualTo(candle.baseScore + 100)
    }
}
