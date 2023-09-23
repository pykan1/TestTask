class Monster(name: String, attack: Int, defense: Int, health: Int) : Creature(name, attack, defense, health) {
    init {
        require(attack in 1..30) { "Значение атаки монстра должно быть от 1 до 30." }
        require(defense in 1..30) { "Значение защиты монстра должно быть от 1 до 30." }
        require(health in 0..100) { "Значение здоровья монстра должно быть от 0 до 100." }
    }
}