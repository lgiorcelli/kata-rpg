package katas.rpg.model

import katas.rpg.model.faction.FactionService

class AttackModule(
        private val baseDamageAmount: Int,
        private val maxReachingRange: Int,
        private val factionService: FactionService
) {

    fun calculateDamageAmount(attacker: LeveledCharacter, attacked: LeveledCharacter, distance: Int): Int {
        if (shouldNotDealDamage(distance, attacker, attacked)) {
            return 0
        }
        if (attackerIsFiveLevelsAbove(attacker, attacked)) {
            return (baseDamageAmount * 1.5).toInt()
        }
        if (attackerIsFiveLevelsBelow(attacker, attacked)) {
            return (baseDamageAmount * 0.5).toInt()
        }
        return baseDamageAmount
    }

    private fun shouldNotDealDamage(distance: Int, attacker: LeveledCharacter, attacked: LeveledCharacter): Boolean {
        return isOutOfRange(distance) || isSameCharacter(attacker, attacked) || factionService.belongsToSameFaction(attacker, attacked)
    }

    private fun isSameCharacter(attacker: LeveledCharacter, attacked: LeveledCharacter): Boolean {
        return attacker == attacked
    }


    private fun isOutOfRange(distance: Int): Boolean {
        return distance > maxReachingRange
    }

    private fun attackerIsFiveLevelsBelow(attacker: LeveledCharacter, attacked: LeveledCharacter): Boolean {
        return attacked.level >= attacker.level + 5
    }

    private fun attackerIsFiveLevelsAbove(attacker: LeveledCharacter, attacked: LeveledCharacter): Boolean {
        return attacker.level >= attacked.level + 5
    }

}