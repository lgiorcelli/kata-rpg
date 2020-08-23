package katas.rpg.model

import katas.rpg.model.attack.AttackModule
import katas.rpg.model.attack.Target
import katas.rpg.model.healing.HealingModule
import katas.rpg.model.health.HealthModule

class RPGCharacter(
        override val level: Int = 1,
        private val attackModule: AttackModule,
        private val healingModule: HealingModule,
        private val healthModule: HealthModule
) : LeveledCharacter {

    fun health(): Int {
        return healthModule.currentHealth
    }

    fun isAlive(): Boolean {
        return healthModule.isAlive()
    }

    fun attack(target: Target, distance: Int) {
        target.receiveDamage(attackModule.calculateDamageAmount(this, target, distance))
    }

    override fun receiveDamage(damageAmount: Int) {
        healthModule.receiveDamage(damageAmount)
    }

    fun heal() {
        heal(this)
    }

    internal fun receiveHealth(healingAmount: Int) {
        healthModule.heal(healingAmount)
    }

    private fun heal(healed: RPGCharacter) {
        healingModule.heal(this, healed)
    }
}

