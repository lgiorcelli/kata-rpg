package katas.rpg.model.faction

import com.nhaarman.mockito_kotlin.mock
import katas.rpg.model.LeveledCharacter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DefaultFactionServiceTest {
    lateinit var service: DefaultFactionService

    @BeforeEach
    internal fun setUp() {
        service = DefaultFactionService()
    }

    @Test
    fun `should add members to factions`() {
        val character: LeveledCharacter  = mock()
        val factionName = "firstFaction"

        service.addMemberToFaction(character, factionName)

        Assertions.assertTrue(service.belongsToFaction(character, factionName))
        Assertions.assertFalse(service.belongsToFaction(character, "otherFaction"))
    }

    @Test
    fun `should be able to inform if two character are part of the same faction`() {
        val factionName = "sharedFaction"
        val character: LeveledCharacter  = mock()
        val otherCharacter: LeveledCharacter  = mock()
        service.addMemberToFaction(character, factionName)
        service.addMemberToFaction(otherCharacter, factionName)

        val belongsToSameFaction = service.areAllies(character, otherCharacter)

        Assertions.assertTrue(belongsToSameFaction)
    }

    @Test
    fun `should return false  if two character are not part of the same faction`() {
        val factionName = "firstFaction"
        val otherFactionName = "otherFaction"
        val character: LeveledCharacter  = mock()
        val otherCharacter: LeveledCharacter  = mock()
        service.addMemberToFaction(character, factionName)
        service.addMemberToFaction(otherCharacter, otherFactionName)

        val belongsToSameFaction = service.areAllies(character, otherCharacter)

        Assertions.assertFalse(belongsToSameFaction)
    }
}
