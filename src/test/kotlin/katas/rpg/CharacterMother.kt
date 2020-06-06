package katas.rpg

import katas.rpg.model.AttackModule
import katas.rpg.model.RPGCharacter

object CharacterMother {
    fun aCharacter(): RPGCharacter {
        return RPGCharacter(attackModule = AttackModule(100, 1))
    }

}