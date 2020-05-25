package katas.rpg

class RPGCharacter(
        var health: Int = 1000,
        val level: Int = 1,
        val damageAmount: Int = 100,
        private val healingAmount: Int = 100,
        private val maxValidHealth: Int = 1000
) {
    fun isAlive(): Boolean {
        return health > 0
    }

    fun attack(attacked: RPGCharacter) {
        val realDamageAmount = calculateDamageAmount(attacked)
        if (attacked != this) {
            attacked.receiveDamage(realDamageAmount)
        }
    }

    private fun calculateDamageAmount(attacked: RPGCharacter): Int {
        if (attacked.level >= 5 + this.level) {
            return (damageAmount * 0.5).toInt()
        }
        return damageAmount
    }

    private fun receiveDamage(damageAmount: Int) {
        health = maxOf(0, health - damageAmount)
    }

    fun heal() {
        health = minOf(maxValidHealth, health + healingAmount)
    }

}