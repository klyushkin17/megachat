package com.example.chat_impl.test

import com.example.chat_impl.domain.model.Message
import com.example.chat_impl.presentation.model.MessageUi
import com.example.core.errors.DataError
import com.example.core.network.Result

fun getAllMessagesTestSuccess(): Result<List<Message>, DataError.Network> = Result.Success(
    listOf(
        Message(
            id = "1",
            username = "ArcherQueen",
            formattedTime = "Jul 18, 2025",
            text = "Ready for battle? My arrows are sharp!"
        ),
        Message(
            id = "2",
            username = "MiniPekka",
            formattedTime = "Jul 18, 2025",
            text = "Pekka SMASH!!! I may be small, but I hit like a truck. Just don't ask me to attack skeletons... they're too fast for me!"
        ),
        Message(
            id = "3",
            username = "ElectroWizard",
            formattedTime = "Jul 18, 2025",
            text = "Got something for ya"
        ),
        Message(
            id = "4",
            username = "RoyalGhost",
            formattedTime = "Jul 17, 2025",
            text = "Kaboom time!"
        ),
        Message(
            id = "5",
            username = "MegaKnight",
            formattedTime = "Jul 17, 2025",
            text = "CRUSH. POUND. DESTROY. That's my motto. When I leap into battle, the ground shakes, and enemies regret standing in my way. Nothing personal—just business."
        ),
        Message(
            id = "6",
            username = "Princess",
            formattedTime = "Jul 17, 2025",
            text = "Why fight up close when I can snipe from the safety of my tower? My flaming arrows rain destruction from afar. Just don't get too close—I'm not great at melee."
        ),
        Message(
            id = "7",
            username = "Lumberjack",
            formattedTime = "Jul 16, 2025",
            text = "RAGE POTION DROPPED! When I go down, I leave behind a little surprise. My juice makes everything faster—troops, towers, even spells. Drink up and go wild!"
        ),
        Message(
            id = "8",
            username = "Bandit",
            formattedTime = "Jul 16, 2025",
            text = "Dashing through the battlefield, striking before they see me coming. My mask isn't just for style—it keeps my identity hidden while I steal victories."
        ),
        Message(
            id = "9",
            username = "NightWitch",
            formattedTime = "Jul 16, 2025",
            text = "The night is my domain. My bats swarm enemies, and my staff drains life. Just don't expect me to stick around in daylight—I work best in the shadows."
        ),
        Message(
            id = "10",
            username = "RoyalGiant",
            formattedTime = "Jul 15, 2025",
            text = "Big gun, big damage. I don't need to get close—I'll blast your towers from a safe distance. Some say I'm annoying, but I call it strategy."
        ),
        Message(
            id = "11",
            username = "GoblinBarrel",
            formattedTime = "Jul 15, 2025",
            text = "Surprise! Three angry goblins just landed in your tower. Hope you have a spell ready, because we're already stabbing away. Log? Zap? Too late!"
        ),
        Message(
            id = "12",
            username = "Valkyrie",
            formattedTime = "Jul 15, 2025",
            text = "Spin to win! My axe cuts through swarms like butter. Skeletons, goblins, minions—none stand a chance. Just keep me away from flying units."
        ),
        Message(
            id = "13", username = "InfernoDragon",
            formattedTime = "Jul 14, 2025",
            text = "Heating things up! My beam starts weak, but give me a few seconds, and I'll melt even the toughest tanks. P.E.K.K.A? More like P.U.D.D.L.E."
        ),
        Message(
            id = "14",
            username = "BattleRam",
            formattedTime = "Jul 14, 2025",
            text = "CHARGE!!! Two barbarians and a battering ram—what could go wrong? Even if we don't break through, we'll leave a mess for the next wave to clean up."
        ),
        Message(
            id = "15",
            username = "MagicArcher",
            formattedTime = "Jul 14, 2025",
            text = "Piercing shots for piercing victories. My arrows go through everything—troops, towers, even the King's ego. Just don't ask me to hit anything behind a building."
        ),
        Message(
            id = "16",
            username = "SkeletonArmy",
            formattedTime = "Jul 13, 2025",
            text = "Overwhelm and overpower! There's strength in numbers, and we've got plenty. Just watch out for splash damage—one zap and we're toast."
        ),
        Message(
            id = "17",
            username = "BabyDragon",
            formattedTime = "Jul 13, 2025",
            text = "Rawr! Don't let my size fool you—I breathe fire and fly over obstacles. Perfect for clearing swarms or chipping away at towers while staying safe."
        ),
        Message(
            id = "18",
            username = "Graveyard",
            formattedTime = "Jul 13, 2025",
            text = "Rise, my bony minions! A surprise attack that keeps spawning skeletons. Great for distracting towers or finishing off low-health targets. Just don't expect them to last long."
        ),
        Message(
            id = "19",
            username = "HogRider",
            formattedTime = "Jul 12, 2025",
            text = "HOGGG RIDAAAA! Fast, relentless, and always going for the towers. Buildings? What buildings? I only see things I can jump over."
        ),
        Message(
            id = "20",
            username = "Sparky",
            formattedTime = "Jul 12, 2025",
            text = "Charging... 5... 4... 3... BOOM! One shot, one obliterated tower. Just keep me protected—I'm useless when zapped or swarmed."
        ),
        Message(
            id = "21",
            username = "Fisherman",
            formattedTime = "Jul 12, 2025",
            text = "Got something for ya! My hook pulls enemies closer—whether they like it or not. Perfect for yanking tanks away or disrupting pushes."
        ),
        Message(
            id = "22",
            username = "ElixirGolem",
            formattedTime = "Jul 11, 2025",
            text = "Blob blob! I'm a walking elixir bank—beat me, and you get extra resources. But good luck stopping all my phases before I reach your tower!"
        ),
        Message(
            id = "23",
            username = "WallBreakers",
            formattedTime = "Jul 11, 2025",
            text = "Two suicidal goblins with bombs—what could go right? If we connect, it's massive damage. If not... well, at least we tried."
        ),
        Message(
            id = "24",
            username = "Executioner",
            formattedTime = "Jul 11, 2025",
            text = "Axe of justice, spinning through enemies like a deadly tornado. Great against swarms, but don't expect me to win any speed contests."
        ),
        Message(
            id = "25",
            username = "Bowler",
            formattedTime = "Jul 10, 2025",
            text = "Rock and roll! My boulders knock back everything in their path. Perfect for clearing lanes or disrupting big pushes. Just don't ask me to hit air troops."
        ),
        Message(
            id = "26", username = "GoblinGiant",
            formattedTime = "Jul 10, 2025",
            text = "Two spears are better than one! I'm a tank with built-in DPS. Those little goblins on my back? They're just bonus damage dealers."
        ),
        Message(
            id = "27",
            username = "Firecracker",
            formattedTime = "Jul 10, 2025",
            text = "Pop! Pop! Pop! My shots explode on impact, making me great against swarms. Just don't place me near the king tower—my recoil sends me flying!"
        ),
        Message(
            id = "28",
            username = "IceWizard",
            formattedTime = "Jul 9, 2025",
            text = "Chill out! My frost slows everything down, making enemies easy pickings for your other troops. Perfect support for defense or controlling pushes."
        ),
        Message(
            id = "29",
            username = "DarkPrince",
            formattedTime = "Jul 9, 2025",
            text = "Charge and crush! My shield blocks damage, and my splash attacks clear swarms. The edgier, stronger version of my golden brother."
        ),
        Message(
            id = "30",
            username = "MotherWitch",
            formattedTime = "Jul 9, 2025",
            text = "Cursed sows turn enemies into hogs under my control. The more they fight, the more I convert. A single curse can snowball into an unstoppable army."
        )
    )
)
