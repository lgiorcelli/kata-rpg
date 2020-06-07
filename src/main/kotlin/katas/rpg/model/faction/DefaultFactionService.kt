package katas.rpg.model.faction

import katas.rpg.model.LeveledCharacter

interface FactionService {
    fun belongsToSameFaction(attacker: LeveledCharacter, attacked: LeveledCharacter): Boolean
}

class DefaultFactionService : FactionService {

    override fun belongsToSameFaction(attacker: LeveledCharacter, attacked: LeveledCharacter): Boolean {
        TODO("Not yet implemented")
    }

}
