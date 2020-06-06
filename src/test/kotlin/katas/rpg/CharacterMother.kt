package katas.rpg

import katas.rpg.model.RPGCharacter
import katas.rpg.model.RPGCharacterFactory

object CharacterMother {
    fun aCharacter(): RPGCharacter {
        return RPGCharacterFactory().createMeleeFighter()
    }

}