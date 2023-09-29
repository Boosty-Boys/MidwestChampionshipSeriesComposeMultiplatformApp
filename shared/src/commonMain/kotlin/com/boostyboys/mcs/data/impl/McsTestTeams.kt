package com.boostyboys.mcs.data.impl

import com.boostyboys.mcs.data.api.models.Player
import com.boostyboys.mcs.data.api.models.Team
import com.boostyboys.mcs.data.impl.McsTestData.CHALLENGER_ID
import com.boostyboys.mcs.data.impl.McsTestData.PREMIER_ID
import com.boostyboys.mcs.data.impl.McsTestData.RISING_STAR_ID
import com.boostyboys.mcs.data.impl.McsTestData.SEASON_ONE_ID
import com.boostyboys.mcs.data.impl.McsTestData.SEASON_TWO_ID

object McsTestTeams {
    val s1PremierHibbing = Team(
        id = "s1PremierHibbing",
        name = "Hibbing Rangers",
        wins = 8,
        losses = 0,
        players = listOf(
            Player(name = "Tjack"),
            Player(name = "joebrothehobo"),
            Player(name = "sbaves15"),
        ),
        leagueId = PREMIER_ID,
        seasonId = SEASON_ONE_ID,
    )

    val s1PremierRochester = Team(
        id = "s1PremierRochester",
        name = "Rochester Rhythm",
        wins = 7,
        losses = 1,
        players = listOf(
            Player(name = "Stinky"),
            Player(name = "garbage"),
            Player(name = "loser42"),
        ),
        leagueId = PREMIER_ID,
        seasonId = SEASON_ONE_ID,
    )

    val s1PremierMinneapolis = Team(
        id = "s1PremierMinneapolis",
        name = "Minneapolis Miracles",
        wins = 5,
        losses = 3,
        players = listOf(
            Player(name = "mini"),
            Player(name = "apple"),
            Player(name = "LOSS"),
        ),
        leagueId = PREMIER_ID,
        seasonId = SEASON_ONE_ID,
    )

    val s1PremierBloomington = Team(
        id = "s1PremierBloomington",
        name = "Bloomington Maulers",
        wins = 2,
        losses = 6,
        players = listOf(
            Player(name = "Blue"),
            Player(name = "mington"),
            Player(name = "maul_her"),
        ),
        leagueId = PREMIER_ID,
        seasonId = SEASON_ONE_ID,
    )

    val s1PremierMankato = Team(
        id = "s1PremierMankato",
        name = "Mankato Atlas",
        wins = 0,
        losses = 8,
        players = listOf(
            Player(name = "M A N"),
            Player(name = "'kay"),
            Player(name = "DOE"),
        ),
        leagueId = PREMIER_ID,
        seasonId = SEASON_ONE_ID,
    )

    val s1PremierStCloud = Team(
        id = "s1PremierStCloud",
        name = "St. Cloud Flyers",
        wins = 1,
        losses = 7,
        players = listOf(
            Player(name = "SaInT"),
            Player(name = "cloudy"),
            Player(name = "supa-fly"),
        ),
        leagueId = PREMIER_ID,
        seasonId = SEASON_ONE_ID,
    )

    val s1PremierDuluth = Team(
        id = "s1PremierDuluth",
        name = "Duluth Superiors",
        wins = 6,
        losses = 2,
        players = listOf(
            Player(name = "dull"),
            Player(name = "xX_YOUTH_Xx"),
            Player(name = "PERIerr"),
        ),
        leagueId = PREMIER_ID,
        seasonId = SEASON_ONE_ID,
    )

    val s1PremierBemidji = Team(
        id = "s1PremierBemidji",
        name = "Bemidji Lumberjacks",
        wins = 3,
        losses = 5,
        players = listOf(
            Player(name = "Madge"),
            Player(name = "lumbHer?"),
            Player(name = "jaq"),
        ),
        leagueId = PREMIER_ID,
        seasonId = SEASON_ONE_ID,
    )

    val s1ChallengerSavage = Team(
        id = "s1ChallengerSavage",
        name = "Savage Boosty Boys",
        wins = 8,
        losses = 0,
        players = listOf(
            Player(name = "Tjack"),
            Player(name = "joebrothehobo"),
            Player(name = "sbaves15"),
        ),
        leagueId = CHALLENGER_ID,
        seasonId = SEASON_ONE_ID,
    )

    val s1ChallengerWhiteBear = Team(
        id = "s1ChallengerWhiteBear",
        name = "White Bear Lakers",
        wins = 0,
        losses = 8,
        players = listOf(
            Player(name = "Stinky"),
            Player(name = "garbage"),
            Player(name = "loser42"),
        ),
        leagueId = CHALLENGER_ID,
        seasonId = SEASON_ONE_ID,
    )

    val s1ChallengerRedFoxes = Team(
        id = "s1ChallengerRedFoxes",
        name = "Red Foxes",
        wins = 3,
        losses = 5,
        players = listOf(
            Player(name = "SwiftPaw"),
            Player(name = "RedFur"),
            Player(name = "FoxTrot"),
        ),
        leagueId = CHALLENGER_ID,
        seasonId = SEASON_ONE_ID,
    )

    val s1ChallengerBlueSharks = Team(
        id = "s1ChallengerBlueSharks",
        name = "Blue Sharks",
        wins = 5,
        losses = 3,
        players = listOf(
            Player(name = "SharpTeeth"),
            Player(name = "DeepBlue"),
            Player(name = "SeaHunter"),
        ),
        leagueId = CHALLENGER_ID,
        seasonId = SEASON_ONE_ID,
    )

    val s1ChallengerGoldenEagles = Team(
        id = "s1ChallengerGoldenEagles",
        name = "Golden Eagles",
        wins = 7,
        losses = 1,
        players = listOf(
            Player(name = "SkyHigh"),
            Player(name = "WingSpan"),
            Player(name = "EagleEye"),
        ),
        leagueId = CHALLENGER_ID,
        seasonId = SEASON_ONE_ID,
    )

    val s1ChallengerBlackCats = Team(
        id = "s1ChallengerBlackCats",
        name = "Black Cats",
        wins = 2,
        losses = 6,
        players = listOf(
            Player(name = "NightProwler"),
            Player(name = "ShadowFeline"),
            Player(name = "SilentRoar"),
        ),
        leagueId = CHALLENGER_ID,
        seasonId = SEASON_ONE_ID,
    )

    val s1ChallengerSilverWolves = Team(
        id = "s1ChallengerSilverWolves",
        name = "Silver Wolves",
        wins = 6,
        losses = 2,
        players = listOf(
            Player(name = "MoonHowler"),
            Player(name = "AlphaSilver"),
            Player(name = "LoneWolf"),
        ),
        leagueId = CHALLENGER_ID,
        seasonId = SEASON_ONE_ID,
    )

    val s1ChallengerGreenCobras = Team(
        id = "s1ChallengerGreenCobras",
        name = "Green Cobras",
        wins = 4,
        losses = 4,
        players = listOf(
            Player(name = "VenomStrike"),
            Player(name = "CoiledAttack"),
            Player(name = "SerpentSwift"),
        ),
        leagueId = CHALLENGER_ID,
        seasonId = SEASON_ONE_ID,
    )

    val s2PremierHibbing = Team(
        id = "s2PremierHibbing",
        name = "Hibbing Rangers",
        wins = 10,
        losses = 0,
        players = listOf(
            Player(name = "Tjack"),
            Player(name = "joebrothehobo"),
            Player(name = "sbaves15"),
        ),
        leagueId = PREMIER_ID,
        seasonId = SEASON_TWO_ID,
    )

    val s2PremierRochester = Team(
        id = "s2PremierRochester",
        name = "Rochester Rhythm",
        wins = 3,
        losses = 7,
        players = listOf(
            Player(name = "Stinky"),
            Player(name = "garbage"),
            Player(name = "loser42"),
        ),
        leagueId = PREMIER_ID,
        seasonId = SEASON_TWO_ID,
    )

    val s2PremierMinneapolis = Team(
        id = "s2PremierMinneapolis",
        name = "Minneapolis Miracles",
        wins = 6,
        losses = 4,
        players = listOf(
            Player(name = "mini"),
            Player(name = "apple"),
            Player(name = "LOSS"),
        ),
        leagueId = PREMIER_ID,
        seasonId = SEASON_TWO_ID,
    )

    val s2PremierBloomington = Team(
        id = "s2PremierBloomington",
        name = "Bloomington Maulers",
        wins = 2,
        losses = 8,
        players = listOf(
            Player(name = "Blue"),
            Player(name = "mington"),
            Player(name = "maul_her"),
        ),
        leagueId = PREMIER_ID,
        seasonId = SEASON_TWO_ID,
    )

    val s2PremierMankato = Team(
        id = "s2PremierMankato",
        name = "Mankato Atlas",
        wins = 1,
        losses = 9,
        players = listOf(
            Player(name = "M A N"),
            Player(name = "'kay"),
            Player(name = "DOE"),
        ),
        leagueId = PREMIER_ID,
        seasonId = SEASON_TWO_ID,
    )

    val s2PremierStCloud = Team(
        id = "s2PremierStCloud",
        name = "St. Cloud Flyers",
        wins = 5,
        losses = 5,
        players = listOf(
            Player(name = "SaInT"),
            Player(name = "cloudy"),
            Player(name = "supa-fly"),
        ),
        leagueId = PREMIER_ID,
        seasonId = SEASON_TWO_ID,
    )

    val s2PremierDuluth = Team(
        id = "s2PremierDuluth",
        name = "Duluth Superiors",
        wins = 2,
        losses = 8,
        players = listOf(
            Player(name = "dull"),
            Player(name = "xX_YOUTH_Xx"),
            Player(name = "PERIerr"),
        ),
        leagueId = PREMIER_ID,
        seasonId = SEASON_TWO_ID,
    )

    val s2PremierBemidji = Team(
        id = "s2PremierBemidji",
        name = "Bemidji Lumberjacks",
        wins = 5,
        losses = 5,
        players = listOf(
            Player(name = "Madge"),
            Player(name = "lumbHer?"),
            Player(name = "jaq"),
        ),
        leagueId = PREMIER_ID,
        seasonId = SEASON_TWO_ID,
    )

    val s2ChallengerHibbing = Team(
        id = "s2ChallengerHibbing",
        name = "Hibbing Rangers",
        wins = 10,
        losses = 0,
        players = listOf(
            Player(name = "Tjack"),
            Player(name = "joebrothehobo"),
            Player(name = "sbaves15"),
        ),
        leagueId = CHALLENGER_ID,
        seasonId = SEASON_TWO_ID,
    )

    val s2ChallengerRochester = Team(
        id = "s2ChallengerRochester",
        name = "Rochester Rhythm",
        wins = 5,
        losses = 5,
        players = listOf(
            Player(name = "Stinky"),
            Player(name = "garbage"),
            Player(name = "loser42"),
        ),
        leagueId = CHALLENGER_ID,
        seasonId = SEASON_TWO_ID,
    )

    val s2ChallengerMinneapolis = Team(
        id = "s2ChallengerMinneapolis",
        name = "Minneapolis Miracles",
        wins = 4,
        losses = 6,
        players = listOf(
            Player(name = "mini"),
            Player(name = "apple"),
            Player(name = "LOSS"),
        ),
        leagueId = CHALLENGER_ID,
        seasonId = SEASON_TWO_ID,
    )

    val s2ChallengerBloomington = Team(
        id = "s2ChallengerBloomington",
        name = "Bloomington Maulers",
        wins = 9,
        losses = 1,
        players = listOf(
            Player(name = "Blue"),
            Player(name = "mington"),
            Player(name = "maul_her"),
        ),
        leagueId = CHALLENGER_ID,
        seasonId = SEASON_TWO_ID,
    )

    val s2ChallengerMankato = Team(
        id = "s2ChallengerMankato",
        name = "Mankato Atlas",
        wins = 3,
        losses = 7,
        players = listOf(
            Player(name = "M A N"),
            Player(name = "'kay"),
            Player(name = "DOE"),
        ),
        leagueId = CHALLENGER_ID,
        seasonId = SEASON_TWO_ID,
    )

    val s2ChallengerStCloud = Team(
        id = "s2ChallengerStCloud",
        name = "St. Cloud Flyers",
        wins = 7,
        losses = 3,
        players = listOf(
            Player(name = "SaInT"),
            Player(name = "cloudy"),
            Player(name = "supa-fly"),
        ),
        leagueId = CHALLENGER_ID,
        seasonId = SEASON_TWO_ID,
    )

    val s2ChallengerDuluth = Team(
        id = "s2ChallengerDuluth",
        name = "Duluth Superiors",
        wins = 3,
        losses = 7,
        players = listOf(
            Player(name = "dull"),
            Player(name = "xX_YOUTH_Xx"),
            Player(name = "PERIerr"),
        ),
        leagueId = CHALLENGER_ID,
        seasonId = SEASON_TWO_ID,
    )

    val s2ChallengerBemidji = Team(
        id = "s2ChallengerBemidji",
        name = "Bemidji Lumberjacks",
        wins = 8,
        losses = 2,
        players = listOf(
            Player(name = "Madge"),
            Player(name = "lumbHer?"),
            Player(name = "jaq"),
        ),
        leagueId = CHALLENGER_ID,
        seasonId = SEASON_TWO_ID,
    )

    val s2RisingStarHibbing = Team(
        id = "s2RisingStarHibbing",
        name = "Hibbing Rangers",
        wins = 10,
        losses = 0,
        players = listOf(
            Player(name = "jackT"),
            Player(name = "hoebrothejobo"),
            Player(name = "1bavess5"),
        ),
        leagueId = RISING_STAR_ID,
        seasonId = SEASON_TWO_ID,
    )

    val s2RisingStarRochester = Team(
        id = "s2RisingStarRochester",
        name = "Rochester Rhythm",
        wins = 2,
        losses = 8,
        players = listOf(
            Player(name = "Stinky"),
            Player(name = "garbage"),
            Player(name = "loser42"),
        ),
        leagueId = RISING_STAR_ID,
        seasonId = SEASON_TWO_ID,
    )

    val s2RisingStarMinneapolis = Team(
        id = "s2RisingStarMinneapolis",
        name = "Minneapolis Miracles",
        wins = 9,
        losses = 1,
        players = listOf(
            Player(name = "mini"),
            Player(name = "apple"),
            Player(name = "LOSS"),
        ),
        leagueId = RISING_STAR_ID,
        seasonId = SEASON_TWO_ID,
    )

    val s2RisingStarBloomington = Team(
        id = "s2RisingStarBloomington",
        name = "Bloomington Maulers",
        wins = 1,
        losses = 9,
        players = listOf(
            Player(name = "Blue"),
            Player(name = "mington"),
            Player(name = "maul_her"),
        ),
        leagueId = RISING_STAR_ID,
        seasonId = SEASON_TWO_ID,
    )

    val s2RisingStarMankato = Team(
        id = "s2RisingStarMankato",
        name = "Mankato Atlas",
        wins = 5,
        losses = 5,
        players = listOf(
            Player(name = "M A N"),
            Player(name = "'kay"),
            Player(name = "DOE"),
        ),
        leagueId = RISING_STAR_ID,
        seasonId = SEASON_TWO_ID,
    )

    val s2RisingStarStCloud = Team(
        id = "s2RisingStarStCloud",
        name = "St. Cloud Flyers",
        wins = 4,
        losses = 6,
        players = listOf(
            Player(name = "SaInT"),
            Player(name = "cloudy"),
            Player(name = "supa-fly"),
        ),
        leagueId = RISING_STAR_ID,
        seasonId = SEASON_TWO_ID,
    )

    val s2RisingStarDuluth = Team(
        id = "s2RisingStarDuluth",
        name = "Duluth Superiors",
        wins = 3,
        losses = 7,
        players = listOf(
            Player(name = "dull"),
            Player(name = "xX_YOUTH_Xx"),
            Player(name = "PERIerr"),
        ),
        leagueId = RISING_STAR_ID,
        seasonId = SEASON_TWO_ID,
    )

    val s2RisingStarBemidji = Team(
        id = "s2RisingStarBemidji",
        name = "Bemidji Lumberjacks",
        wins = 7,
        losses = 3,
        players = listOf(
            Player(name = "Madge"),
            Player(name = "lumbHer?"),
            Player(name = "jaq"),
        ),
        leagueId = RISING_STAR_ID,
        seasonId = SEASON_TWO_ID,
    )

    val s1PremierTeams = listOf(
        s1PremierHibbing,
        s1PremierRochester,
        s1PremierMinneapolis,
        s1PremierBloomington,
        s1PremierMankato,
        s1PremierStCloud,
        s1PremierDuluth,
        s1PremierBemidji,
    )

    val s1ChallengerTeams = listOf(
        s1ChallengerSavage,
        s1ChallengerWhiteBear,
        s1ChallengerRedFoxes,
        s1ChallengerBlueSharks,
        s1ChallengerGoldenEagles,
        s1ChallengerBlackCats,
        s1ChallengerSilverWolves,
        s1ChallengerGreenCobras,
    )

    val s2PremierTeams = listOf(
        s2PremierHibbing,
        s2PremierRochester,
        s2PremierMinneapolis,
        s2PremierBloomington,
        s2PremierMankato,
        s2PremierStCloud,
        s2PremierDuluth,
        s2PremierBemidji,
    )

    val s2ChallengerTeams = listOf(
        s2ChallengerHibbing,
        s2ChallengerRochester,
        s2ChallengerMinneapolis,
        s2ChallengerBloomington,
        s2ChallengerMankato,
        s2ChallengerStCloud,
        s2ChallengerDuluth,
        s2ChallengerBemidji,
    )

    val s2RisingStarTeams = listOf(
        s2RisingStarHibbing,
        s2RisingStarRochester,
        s2RisingStarMinneapolis,
        s2RisingStarBloomington,
        s2RisingStarMankato,
        s2RisingStarStCloud,
        s2RisingStarDuluth,
        s2RisingStarBemidji,
    )
}
