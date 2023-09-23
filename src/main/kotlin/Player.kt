import kotlin.random.Random

class Player(name: String, attack: Int, defense: Int, health: Int) : Creature(name, attack, defense, health) {
    var reBorn = 0
    var maxHealth = health

    init {
        require(attack in 1..30) { "Значение атаки должно быть от 1 до 30." }
        require(defense in 1..30) { "Значение защиты должно быть от 1 до 30." }
        require(health in 0..100) { "Значение здоровья должно быть от 0 до 100." }
    }

    fun heal() {
        require(reBorn in 0..4) { "Превышен лимит жизней" }
        val heal = (maxHealth * 0.3).toInt()
        this.health += heal
        reBorn += 1
        println("Возрождение, теперь мое здоровье ${this.health}")
    }
}