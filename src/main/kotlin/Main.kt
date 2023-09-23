import java.lang.Exception

fun main() {
    val player = Player("Игрок", 18, 20, 67)
    val monster = Monster("Монстр", 22, 18, 90)
    fun fight() {
        while (player.isAlive() && monster.isAlive()) {
            player.attack(monster)
            if (monster.isAlive()) {
                monster.attack(player)
                if (!player.isAlive()) {
                    try {
                        player.heal()
                    } catch (_: Exception) {
                    }
                }
            }
            println("${player.name}: Здоровье = ${player.health}")
            println("${monster.name}: Здоровье = ${monster.health}")
        }
    }
    fight()
    if (player.isAlive()) {
        println("${player.name} победил!")
    } else {
        println("${monster.name} победил!")
    }
}