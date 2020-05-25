package katas.rpg

class RPGCharacter(
        health: Int = 1000,
        val level: Int = 1,
        damageAmount: Int = 100,
        private val healingAmount: Int = 100,
        private val maxValidHealth: Int = 1000,
        maxRange:Int = 1,
        private val attackType:MeleeAttackType = MeleeAttackType(damageAmount, maxRange)
) {


    var health = health
        private set


    fun isAlive(): Boolean {
        return health > 0
    }

    fun attack(attacked: RPGCharacter, distance: Int) {
        val realDamageAmount = calculateDamageAmount(attacked, distance)
        if (attacked != this) {
            attacked.receiveDamage(realDamageAmount)
        }
    }

    private fun calculateDamageAmount(attacked: RPGCharacter, distance: Int): Int {
        if (isOutOfRange(distance)) {
            return 0
        }
        if (attackerIsFiveLevelsAbove(attacked)) {
            return (attackType.damageAmount * 1.5).toInt()
        }
        if (attackerIsFiveLevelsBelow(attacked)) {
            return (attackType.damageAmount * 0.5).toInt()
        }
        return attackType.damageAmount
    }

    private fun isOutOfRange(distance: Int): Boolean {
        return distance > attackType.maxRange
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

class MeleeAttackType(val damageAmount: Int, val maxRange: Int)