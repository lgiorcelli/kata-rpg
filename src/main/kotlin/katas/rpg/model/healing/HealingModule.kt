package katas.rpg.model.healing

import katas.rpg.model.RPGCharacter
import katas.rpg.model.faction.FactionService

class HealingModule(
        private val healingAmount: Int = 100,
        private val factionService: FactionService
) {

    fun heal(healer: RPGCharacter, healed: RPGCharacter) {
        if (factionService.areAllies(healed, healer)) {
            healed.receiveHealth(healingAmount)
        }
    }
}