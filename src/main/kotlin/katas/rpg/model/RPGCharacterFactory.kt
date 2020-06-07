package katas.rpg.model

import katas.rpg.model.attack.AttackModule
import katas.rpg.model.faction.DefaultFactionService

class RPGCharacterFactory(private val factionService: DefaultFactionService) {

    private val defaultHealth = 1000
    private val defaultLevel = 1
    private val defaultAttackAmount = 100

    private val defaultHealingAmount = defaultAttackAmount

    fun createMeleeFighter(): RPGCharacter {
        val meleeAttackModule = AttackModule(defaultAttackAmount, 2, factionService)
        return createCharacter(meleeAttackModule)
    }

    fun createRangedFighter(): RPGCharacter {
        val rangedAttackModule = AttackModule(defaultAttackAmount, 20, factionService)
        return createCharacter(rangedAttackModule)
    }

    private fun createCharacter(rangedAttackModule: AttackModule): RPGCharacter {
        return RPGCharacter(defaultHealth, defaultLevel, defaultHealingAmount, defaultHealth, rangedAttackModule)
    }
}