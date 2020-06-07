package katas.rpg.model

import katas.rpg.model.attack.AttackModule

class RPGCharacter(
        health: Int = 1000,
        override val level: Int = 1,
        private val healingAmount: Int = 100,
        private val maxValidHealth: Int = 1000,
        private val attackModule: AttackModule
) : LeveledCharacter {

    var health = health
        private set

    fun isAlive(): Boolean {
        return health > 0
    }

    fun attack(attacked: RPGCharacter, distance: Int) {
        attacked.receiveDamage(attackModule.calculateDamageAmount(this, attacked, distance))
    }

    internal fun receiveDamage(damageAmount: Int) {
        health = maxOf(0, health - damageAmount)
    }

    fun heal() {
        health = minOf(maxValidHealth, health + healingAmount)
    }
}

