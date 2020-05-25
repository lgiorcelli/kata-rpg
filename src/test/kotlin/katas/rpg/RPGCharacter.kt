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
        if (attacked != this) {
            attacked.receiveDamage(attackType.calculateDamageAmount(this, attacked, distance))
        }
    }

    private fun receiveDamage(damageAmount: Int) {
        health = maxOf(0, health - damageAmount)
    }

    fun heal() {
        health = minOf(maxValidHealth, health + healingAmount)
    }
}

class MeleeAttackType(private val damageAmount: Int, private val maxRange: Int) {

    fun calculateDamageAmount(attacker:RPGCharacter, attacked: RPGCharacter, distance: Int): Int {
        if (isOutOfRange(distance)) {
            return 0
        }
        if (attackerIsFiveLevelsAbove(attacker, attacked)) {
            return (damageAmount * 1.5).toInt()
        }
        if (attackerIsFiveLevelsBelow(attacker, attacked)) {
            return (damageAmount * 0.5).toInt()
        }
        return damageAmount
    }


    private fun isOutOfRange(distance: Int): Boolean {
        return distance > maxRange
    }

    private fun attackerIsFiveLevelsBelow(attacker: RPGCharacter, attacked: RPGCharacter): Boolean {
        return attacked.level >= attacker.level + 5
    }

    private fun attackerIsFiveLevelsAbove(attacker:RPGCharacter, attacked: RPGCharacter): Boolean {
        return attacker.level >= attacked.level + 5
    }

}