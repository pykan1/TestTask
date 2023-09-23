import kotlin.random.Random
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

    fun calculateAttackModifier(defender: Creature): Int {
        return attack - defender.defense + 1
    }

    fun attack(target: Creature) {
        val attackModifier = calculateAttackModifier(target)
        val success = (1..attackModifier).any { Random.nextInt(1, 7) in 5..6 }

        if (success) {
            val damage = Random.nextInt(damageRange)
            println("$name успешно атакует ${target.name} и наносит $damage урона.")
            target.takeDamage(damage)
        } else {
            println("$name не смог пробить защиту ${target.name}.")
        }
    }
}