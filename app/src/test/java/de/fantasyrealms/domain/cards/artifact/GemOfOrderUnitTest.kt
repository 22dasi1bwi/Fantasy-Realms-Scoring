package de.fantasyrealms.domain.cards.artifact

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.fantasyrealms.domain.cards.army.ElvenArchers
import de.fantasyrealms.domain.cards.beast.Warhorse
import de.fantasyrealms.domain.cards.flame.FireElemental
import de.fantasyrealms.domain.cards.flame.Lightning
import de.fantasyrealms.domain.cards.handOf
import de.fantasyrealms.domain.cards.land.Forest
import de.fantasyrealms.domain.cards.leader.King
import de.fantasyrealms.domain.cards.wizard.Beastmaster
import org.junit.jupiter.api.Test

class GemOfOrderUnitTest {

    @Test
    fun `returns base score on 1 card run`() {
        val gemOfOrder = GemOfOrder()
        val hand = handOf(gemOfOrder)

        val score = hand.getTotalScore()[gemOfOrder]

        assertThat(score).isEqualTo(gemOfOrder.baseScore)
    }

    @Test
    fun `returns base score on 2 card run`() {
        val gemOfOrder = GemOfOrder() // base score 5
        val warhorse = Warhorse() // base score 6
        val hand = handOf(gemOfOrder, warhorse)

        val score = hand.getTotalScore()[gemOfOrder]

        assertThat(score).isEqualTo(gemOfOrder.baseScore)
    }

    @Test
    fun `+10 on 3 card run`() {
        val gemOfOrder = GemOfOrder() // base score 5
        val warhorse = Warhorse() // base score 6
        val forest = Forest() // base score 7
        val hand = handOf(gemOfOrder, warhorse, forest)

        val score = hand.getTotalScore()[gemOfOrder]

        assertThat(score).isEqualTo(gemOfOrder.baseScore + 10)
    }

    @Test
    fun `+30 on 4 card run`() {
        val gemOfOrder = GemOfOrder() // base score 5
        val warhorse = Warhorse() // base score 6
        val forest = Forest() // base score 7
        val king = King() // base score 8
        val hand = handOf(gemOfOrder, warhorse, forest, king)

        val score = hand.getTotalScore()[gemOfOrder]

        assertThat(score).isEqualTo(gemOfOrder.baseScore + 30)
    }

    @Test
    fun `+60 on 5 card run`() {
        val gemOfOrder = GemOfOrder() // base score 5
        val warhorse = Warhorse() // base score 6
        val forest = Forest() // base score 7
        val king = King() // base score 8
        val beastmaster = Beastmaster() // base score 9
        val hand = handOf(gemOfOrder, warhorse, forest, king, beastmaster)

        val score = hand.getTotalScore()[gemOfOrder]

        assertThat(score).isEqualTo(gemOfOrder.baseScore + 60)
    }

    @Test
    fun `+100 on 6 card run`() {
        val gemOfOrder = GemOfOrder() // base score 5
        val warhorse = Warhorse() // base score 6
        val forest = Forest() // base score 7
        val king = King() // base score 8
        val beastmaster = Beastmaster() // base score 9
        val elvenArchers = ElvenArchers() // base score 10
        val hand = handOf(gemOfOrder, warhorse, forest, king, beastmaster, elvenArchers)

        val score = hand.getTotalScore()[gemOfOrder]

        assertThat(score).isEqualTo(gemOfOrder.baseScore + 100)
    }

    @Test
    fun `+150 on 7 card run`() {
        val gemOfOrder = GemOfOrder() // base score 5
        val warhorse = Warhorse() // base score 6
        val forest = Forest() // base score 7
        val king = King() // base score 8
        val beastmaster = Beastmaster() // base score 9
        val elvenArchers = ElvenArchers() // base score 10
        val lightning = Lightning() // base score 11
        val hand = handOf(gemOfOrder, warhorse, forest, king, beastmaster, elvenArchers, lightning)

        val score = hand.getTotalScore()[gemOfOrder]

        assertThat(score).isEqualTo(gemOfOrder.baseScore + 150)
    }

    @Test
    fun `longest run bonus is taken, if longest run comes after first run`() {
        // 3-card-run
        val fireElemental = FireElemental() // base score 4
        val gemOfOrder = GemOfOrder() // base score 5
        val warhorse = Warhorse() // base score 6

        // 4-card-run
        val king = King() // base score 8
        val beastmaster = Beastmaster() // base score 9
        val elvenArchers = ElvenArchers() // base score 10
        val lightning = Lightning() // base score 11
        val hand = handOf(gemOfOrder, warhorse, fireElemental, king, beastmaster, elvenArchers, lightning)

        val score = hand.getTotalScore()[gemOfOrder]

        assertThat(score).isEqualTo(gemOfOrder.baseScore + 30)
    }

    @Test
    fun `longest run bonus is taken, if longest run comes before other runs`() {
        // 4-card-run
        val fireElemental = FireElemental() // base score 4
        val gemOfOrder = GemOfOrder() // base score 5
        val warhorse = Warhorse() // base score 6
        val forest = Forest() // base score 7

        // 3-card-run
        val beastmaster = Beastmaster() // base score 9
        val elvenArchers = ElvenArchers() // base score 10
        val lightning = Lightning() // base score 11
        val hand = handOf(gemOfOrder, warhorse, forest, fireElemental, beastmaster, elvenArchers, lightning)

        val score = hand.getTotalScore()[gemOfOrder]

        assertThat(score).isEqualTo(gemOfOrder.baseScore + 30)
    }
}
