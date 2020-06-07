package katas.rpg.application

import katas.rpg.model.RPGCharacterFactory
import katas.rpg.model.faction.DefaultFactionService

class Main {

    fun startApp() {
        val factionService = DefaultFactionService()
        val factory = RPGCharacterFactory(factionService)
        val meleeFighter = factory.createMeleeFighter()
        val rangedFighter = factory.createRangedFighter()


    }
}