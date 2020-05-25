package katas.rpg

class RPGCharacter(
        health: Int = 1000,
        val level: Int = 1,
        val damageAmount: Int = 100,
        private val healingAmount: Int = 100,
        private val maxValidHealth: Int = 1000
) {
    var health = health
        private set


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
        if (attackerIsFiveLevelsAbove(attacked)) {
            return (damageAmount * 1.5).toInt()
        }
        if (attackerIsFiveLevelsBelow(attacked)) {
            return (damageAmount * 0.5).toInt()
        }
        return damageAmount
    }

    private fun attackerIsFiveLevelsBelow(attacked: RPGCharacter): Boolean {
        return attacked.level >= this.level + 5
    }

    private fun attackerIsFiveLevelsAbove(attacked: RPGCharacter): Boolean {
        return this.level >= attacked.level + 5
    }

    private fun receiveDamage(damageAmount: Int) {
        health = maxOf(0, health - damageAmount)
    }

    fun heal() {
        health = minOf(maxValidHealth, health + healingAmount)
    }

}