package de.fantasyrealms.domain.cards.wizard

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.beast.Unicorn
import de.fantasyrealms.domain.cards.handOf
import org.junit.jupiter.api.Test

class CollectorUnitTest {

    @Test
    fun `returns base score with 1 card of same Suit in hand`() {
        val collector = Collector()
        val hand = handOf(collector)

        val score = hand.getTotalScore()[collector]

        assertThat(score).isEqualTo(collector.baseScore)
    }

    @Test
    fun `returns base score with 2 cards of same Suit in hand`() {
        val collector = Collector()
        val hand = handOf(collector, Beastmaster())

        val score = hand.getTotalScore()[collector]

        assertThat(score).isEqualTo(collector.baseScore)
    }

    @Test
    fun `+10 when with 3 cards of same Suit in hand`() {
        val collector = Collector()
        val hand = handOf(collector, Beastmaster(), Enchantress())

        val score = hand.getTotalScore()[collector]

        assertThat(score).isEqualTo(collector.baseScore + 10)
    }

    @Test
    fun `+40 when with 4 cards of same Suit in hand`() {
        val collector = Collector()
        val hand = handOf(collector, Beastmaster(), Enchantress(), Necromancer(Unicorn()))

        val score = hand.getTotalScore()[collector]

        assertThat(score).isEqualTo(collector.baseScore + 40)
    }

    @Test
    fun `+100 when with 5 cards of same Suit in hand`() {
        val collector = Collector()
        val hand =
            handOf(collector, Beastmaster(), Enchantress(), Necromancer(Unicorn()), WarlockLord())

        val score = hand.getTotalScore()[collector]

        assertThat(score).isEqualTo(collector.baseScore + 100)
    }
}