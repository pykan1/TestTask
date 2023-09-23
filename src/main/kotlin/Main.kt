import kotlin.random.Random
import kotlin.random.Random.Default.nextInt
import kotlin.random.nextInt

open class Creature(val name: String, var attack: Int, var defense: Int, var health: Int) {
    private var damageRange: IntRange = 1..6

    fun takeDamage(damage: Int) {
        health -= damage
        if (health < 0) {
            health = 0
        }
    }

    fun isAlive(): Boolean {
        return health > 0
    }

    fun heal() {
        val maxHeal = (health * 0.3).toInt()
        val healAmount = nextInt(1, maxHeal + 1)
        health += healAmount
        if (health > 100) {
            health = 100
        }
    }

    fun calculateAttackModifier(defender: Creature): Int {
        return attack - defender.defense + 1
    }

    fun attack(target: Creature) {
        val attackModifier = calculateAttackModifier(target)
        val success = (1..attackModifier).any { nextInt(1, 7) in 5..6 }

        if (success) {
            val damage = Random.nextInt(damageRange)
            println("$name успешно атакует ${target.name} и наносит $damage урона.")
            target.takeDamage(damage)
        } else {
            println("$name не смог пробить защиту ${target.name}.")
        }
    }
}

class Player(name: String, attack: Int, defense: Int, health: Int) : Creature(name, attack, defense, health) {
    init {
        require(attack in 1..30) { "Значение атаки должно быть от 1 до 30." }
        require(defense in 1..30) { "Значение защиты должно быть от 1 до 30." }
        require(health in 0..100) { "Значение здоровья должно быть от 0 до 100." }
    }
}

class Monster(name: String, attack: Int, defense: Int, health: Int) : Creature(name, attack, defense, health) {
    init {
        require(attack in 1..30) { "Значение атаки монстра должно быть от 1 до 30." }
        require(defense in 1..30) { "Значение защиты монстра должно быть от 1 до 30." }
        require(health in 0..100) { "Значение здоровья монстра должно быть от 0 до 100." }
    }
}

fun main() {
    val player = Player("Игрок", 25, 20, 100)
    val monster = Monster("Монстр", 22, 18, 90)

    while (player.isAlive() && monster.isAlive()) {
        player.attack(monster)
        if (monster.isAlive()) {
            monster.attack(player)
        }
        println("${player.name}: Здоровье = ${player.health}")
        println("${monster.name}: Здоровье = ${monster.health}")
    }

    if (player.isAlive()) {
        println("${player.name} победил!")
    } else {
        println("${monster.name} победил!")
    }
}