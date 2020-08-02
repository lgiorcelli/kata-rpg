package katas.rpg.model.health

class HealthModule(private val maxValidHealth: Int, initialHealth: Int) {
    var currentHealth = initialHealth

    fun isAlive(): Boolean {
        return currentHealth > 0
    }

    fun receiveDamage(damageAmount: Int) {
        currentHealth = maxOf(0, currentHealth - damageAmount)
    }

    fun heal(healingAmount: Int) {
        currentHealth = minOf(maxValidHealth, currentHealth + healingAmount)
    }

}