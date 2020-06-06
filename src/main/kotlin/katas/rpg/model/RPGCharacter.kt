package katas.rpg.model

class RPGCharacter(
        health: Int = 1000,
        val level: Int = 1,
        damageAmount: Int = 100,
        private val healingAmount: Int = 100,
        private val maxValidHealth: Int = 1000,
        maxRange: Int = 1,
        private val attackType: MeleeAttackType
) {

    val range: Int
        get() = attackType.maxRange


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

