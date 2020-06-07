package katas.rpg.model.faction

import katas.rpg.model.LeveledCharacter

interface FactionService {
    fun areAllies(character: LeveledCharacter, otherCharacter: LeveledCharacter): Boolean
    fun addMemberToFaction(character: LeveledCharacter, factionName: String)
    fun belongsToFaction(character: LeveledCharacter, factionName: String): Boolean
}

class DefaultFactionService : FactionService {
    private val factions: MutableMap<String, List<LeveledCharacter>> = mutableMapOf()

    override fun areAllies(character: LeveledCharacter, otherCharacter: LeveledCharacter): Boolean {
        val firstSetOfFactions = factionsOf(character)
        val secondSetOfFactions = factionsOf(otherCharacter)
        return firstSetOfFactions.intersect(secondSetOfFactions).isNotEmpty()
    }

    private fun factionsOf(attacker: LeveledCharacter): Set<String> {
        return factions.filter { (_, members) -> members.contains(attacker) }.keys
    }

    override fun addMemberToFaction(character: LeveledCharacter, factionName: String) {
        var members = factions[factionName]
        if (members == null) {
            members = emptyList()
        }
        factions[factionName] = members + character
    }

    override fun belongsToFaction(character: LeveledCharacter, factionName: String): Boolean {
        return factions[factionName]?.contains(character) ?: false
    }

}
