package de.fantasyrealms.domain.cards


enum class Card(private val cardSpecification: CardSpecification) {

    // ----- ARMY -----
    DWARVISH_INFANTRY(CardSpecification(24, "Dwawrvish Infantry", 15, Suit.ARMY)),
    ELVEN_ARCHERS(CardSpecification(22, "Elven Archers", 10, Suit.ARMY)),
    KNIGHTS(CardSpecification(21, "Knights", 20, Suit.ARMY)),
    LIGHT_CAVALRY(CardSpecification(23, "Light Cavalry", 17, Suit.ARMY)),
    RANGERS(CardSpecification(25, "Rangers", 5, Suit.ARMY)),

    // ----- ARTIFACT -----
    BOOK_OF_CHANGES(CardSpecification(49, "Book Of Changes", 3, Suit.ARTIFACT)),
    GEM_OF_ORDER(CardSpecification(47, "Gem Of Order", 5, Suit.ARTIFACT)),
    PROTECTION_RUNE(CardSpecification(50, "Protection Rune", 1, Suit.ARTIFACT)),
    SHIELD_OF_KETH(CardSpecification(49, "Shield Of Keth", 3, Suit.ARTIFACT)),
    WORLD_TREE(CardSpecification(48, "World Tree", 2, Suit.ARTIFACT)),

    // ----- BEAST -----
    BASILISK(CardSpecification(37, "Basilisk", 35, Suit.BEAST)),
    DRAGON(CardSpecification(39, "Dragon", 30, Suit.BEAST)),
    HYDRA(CardSpecification(40, "Hydra", 12, Suit.BEAST)),
    UNICORN(CardSpecification(36, "Unicorn", 9, Suit.BEAST)),
    WARHORSE(CardSpecification(38, "Warhorse", 6, Suit.BEAST)),

    // ----- FLAME -----
    CANDLE(CardSpecification(17, "Candle", 2, Suit.FLAME)),
    FIRE_ELEMENTAL(CardSpecification(20, "Fire Elemental", 4, Suit.FLAME)),
    FORGE(CardSpecification(18, "Forge", 9, Suit.FLAME)),
    LIGHTNING(CardSpecification(19, "Lightning", 11, Suit.FLAME)),
    WILDFIRE(CardSpecification(16, "Wildfire", 40, Suit.FLAME)),

    // ----- FLOOD -----
    FOUNTAIN_OF_LIFE(CardSpecification(6, "Fountain Of Life", 1, Suit.FLOOD)),
    GREAT_FLOOD(CardSpecification(8, "Great Flood", 32, Suit.FLOOD)),
    ISLAND(CardSpecification(9, "Island", 14, Suit.FLOOD)),
    SWAMP(CardSpecification(7, "Swamp", 18, Suit.FLOOD)),
    WATER_ELEMENTAL(CardSpecification(10, "Water Elemental", 4, Suit.FLOOD)),

    // ----- LAND -----
    BELL_TOWER(CardSpecification(3, "Bell Tower", 8, Suit.LAND)),
    CAVERN(CardSpecification(2, "Cavern", 6, Suit.LAND)),
    EARTH_ELEMENTAL(CardSpecification(5, "Earth Elemental", 4, Suit.LAND)),
    FOREST(CardSpecification(4, "Forest", 7, Suit.LAND)),
    MOUNTAIN(CardSpecification(1, "Mountain", 9, Suit.LAND)),

    // ----- LEADER -----
    EMPRESS(CardSpecification(35, "Empress", 15, Suit.LEADER)),
    KING(CardSpecification(31, "King", 8, Suit.LEADER)),
    PRINCESS(CardSpecification(33, "Princess", 2, Suit.LEADER)),
    QUEEN(CardSpecification(32, "Queen", 6, Suit.LEADER)),
    WARLORD(CardSpecification(34, "Warlord", 4, Suit.LEADER)),

    // ----- WEAPON -----
    ELVEN_LONGBOW(CardSpecification(44, "Elven Longbow", 3, Suit.WEAPON)),
    MAGIC_WAND(CardSpecification(42, "Magic Wand", 1, Suit.WEAPON)),
    SWORD_OF_KETH(CardSpecification(43, "Sword Of Keth", 7, Suit.WEAPON)),
    WARSHIP(CardSpecification(41, "Warship", 23, Suit.WEAPON)),
    WAR_DIRIGIBLE(CardSpecification(45, "War Dirigible", 35, Suit.WEAPON)),

    // ----- WEATHER -----
    AIR_ELEMENTAL(CardSpecification(15, "Air Elemental", 4, Suit.WEATHER)),
    BLIZZARD(CardSpecification(12, "Blizzard", 30, Suit.WEATHER)),
    RAINSTORM(CardSpecification(11, "Rainstorm", 8, Suit.WEATHER)),
    SMOKE(CardSpecification(13, "Smoke", 27, Suit.WEATHER)),
    WHIRLWIND(CardSpecification(14, "Whirlwind", 13, Suit.WEATHER)),

    // ----- WILD -----
    DOPPELGAENGER(CardSpecification(53, "Doppelgaenger", 0, Suit.WILD)),
    MIRAGE(CardSpecification(52, "Mirage", 0, Suit.WILD)),
    SHAPESHIFTER(CardSpecification(51, "Shapeshifter", 0, Suit.WILD)),

    // ----- WIZARD -----
    BEASTMASTER(CardSpecification(27, "Beastmaster", 9, Suit.WIZARD)),
    ENCHANTRESS(CardSpecification(30, "Enchantress", 5, Suit.WIZARD));

    val id = cardSpecification.id
    val cardName = cardSpecification.name
    val baseScore = cardSpecification.baseScore
    val suit: Suit = cardSpecification.suit

    companion object {

        fun except(vararg cards: Card): List<Card> {
            return values().filterNot { cards.contains(it) }
        }

        fun except(vararg suits: Suit): List<Card> {
            return values().filterNot { suits.contains(it.cardSpecification.suit) }
        }

        fun get(vararg suits: Suit): List<Card> {
            return values().filter { suits.contains(it.cardSpecification.suit) }
        }

        fun get(vararg suits: Suit, except: Card): List<Card> {
            return get(*suits).filter { it != except }
        }
    }

    private data class CardSpecification(val id: Int, val name: String, val baseScore: Int, val suit: Suit)
}
