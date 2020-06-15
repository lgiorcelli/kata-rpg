package katas.rpg.model.healing

import katas.rpg.model.RPGCharacter

class HealingModule(val healingAmount: Int = 100) {

    fun heal(rpgCharacter: RPGCharacter, healed: RPGCharacter, areAllies: Boolean) {
        if (areAllies) {
            healed.receiveHealth(healingAmount)
        }
    }
}