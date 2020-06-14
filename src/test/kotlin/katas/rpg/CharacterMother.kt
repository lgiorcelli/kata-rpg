package katas.rpg

import katas.rpg.model.RPGCharacter
import katas.rpg.model.RPGCharacterFactory
import katas.rpg.model.faction.DefaultFactionService

object CharacterMother {
    fun aCharacter(): RPGCharacter {
        val factionService = DefaultFactionService()
        return RPGCharacterFactory(factionService).createMeleeFighter()
    }

}