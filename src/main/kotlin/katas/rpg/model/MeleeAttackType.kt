package katas.rpg.model

class MeleeAttackType(private val damageAmount: Int, private val maxRange: Int) {

    fun calculateDamageAmount(attacker: RPGCharacter, attacked: RPGCharacter, distance: Int): Int {
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

    private fun attackerIsFiveLevelsAbove(attacker: RPGCharacter, attacked: RPGCharacter): Boolean {
        return attacker.level >= attacked.level + 5
    }

}