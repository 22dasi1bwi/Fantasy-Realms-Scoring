package de.fantasyrealms.domain.cards.wizard

import de.fantasyrealms.domain.EffectDefinition
import de.fantasyrealms.domain.cards.AbstractCard
import de.fantasyrealms.domain.cards.Card
import de.fantasyrealms.domain.cards.Suit

private val allowedSuits = setOf(Suit.ARMY, Suit.LEADER, Suit.WIZARD, Suit.BEAST)

class Necromancer(choice: AbstractCard?) : AbstractCard(Card.NECROMANCER, choice) {

    // TODO: Implement the discard pile first
    // test still missing
    override val effectDefinition: EffectDefinition = EffectDefinition(
        "BONUS: At the end of the game, you may take one Army, Leader, Wizard, or Beast from the discard pile and add it to your hand as an eighth card.",
        setOf()
    )
}
