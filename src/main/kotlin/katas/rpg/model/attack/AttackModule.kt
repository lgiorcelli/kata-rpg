package katas.rpg.model.attack

import katas.rpg.model.LeveledCharacter
import katas.rpg.model.Target
import katas.rpg.model.faction.FactionService

class AttackModule(
        private val baseDamageAmount: Int,
        private val maxReachingRange: Int,
        private val factionService: FactionService
) {

    fun calculateDamageAmount(attacker: LeveledCharacter, attacked: Target, distance: Int): Int {
        if (isProp(attacked)) {
            return baseDamageAmount
        }
        attacked as LeveledCharacter
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