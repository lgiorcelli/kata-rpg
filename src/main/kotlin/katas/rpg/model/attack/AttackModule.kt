package katas.rpg.model.attack

import katas.rpg.model.LeveledCharacter
import katas.rpg.model.Target
import katas.rpg.model.faction.FactionService

class AttackModule(
        private val baseDamageAmount: Int,
        private val maxReachingRange: Int,
        private val factionService: FactionService
) {

    fun calculateDamageAmount(attacker: LeveledCharacter, target: Target, distance: Int): Int {
        if (isProp(target)) {
            return baseDamageAmount
        }
        target as LeveledCharacter
        if (shouldNotDealDamage(distance, attacker, target)) {
            return 0
        }
        if (attackerIsFiveLevelsAbove(attacker, target)) {
            return (baseDamageAmount * 1.5).toInt()
        }
        if (attackerIsFiveLevelsBelow(attacker, target)) {
            return (baseDamageAmount * 0.5).toInt()
        }
        return baseDamageAmount
    }

    private fun isProp(attacked: Target): Boolean {
        return attacked !is LeveledCharacter
    }

    private fun shouldNotDealDamage(distance: Int, attacker: LeveledCharacter, attacked: LeveledCharacter): Boolean {
        return isOutOfRange(distance) || isSameCharacter(attacker, attacked) || factionService.areAllies(attacker, attacked)
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