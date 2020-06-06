package katas.rpg.model

class AttackModule(private val baseDamageAmount: Int, val maxReachingRange: Int) {

    fun calculateDamageAmount(attacker: RPGCharacter, attacked: RPGCharacter, distance: Int): Int {
        if (isOutOfRange(distance)) {
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


    private fun isOutOfRange(distance: Int): Boolean {
        return distance > maxReachingRange
    }

    private fun attackerIsFiveLevelsBelow(attacker: RPGCharacter, attacked: RPGCharacter): Boolean {
        return attacked.level >= attacker.level + 5
    }

    private fun attackerIsFiveLevelsAbove(attacker: RPGCharacter, attacked: RPGCharacter): Boolean {
        return attacker.level >= attacked.level + 5
    }

}