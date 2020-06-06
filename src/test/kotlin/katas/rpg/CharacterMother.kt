package katas.rpg

import katas.rpg.model.MeleeAttackType
import katas.rpg.model.RPGCharacter

object CharacterMother {
    fun aCharacter(): RPGCharacter {
        return RPGCharacter(attackType = MeleeAttackType(100, 1))
    }

}