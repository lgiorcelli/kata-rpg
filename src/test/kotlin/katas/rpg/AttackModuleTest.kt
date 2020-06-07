package katas.rpg

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import katas.rpg.model.AttackModule
import katas.rpg.model.LeveledCharacter
import katas.rpg.model.faction.FactionService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AttackModuleTest {

    private val minimumRange = 1
    private val baseDamage = 100
    private val noDamage = 0
    private val factionService: FactionService = mock()

    private val module = AttackModule(baseDamage, minimumRange, factionService)

    @Test
    fun `should deal damage to other character`() {
        val attacker = aLevelOneCharacter()
        val attacked = aLevelOneCharacter()

        val damage = module.calculateDamageAmount(attacker, attacked, minimumRange)

        Assertions.assertEquals(baseDamage, damage)
    }

    @Test
    fun `should not deal damage to itself`() {
        val attacker: LeveledCharacter = aLevelOneCharacter()

        val damageAmount = module.calculateDamageAmount(attacker, attacker, minimumRange)

        Assertions.assertEquals(noDamage, damageAmount)

    }

    @Test
    fun `should reduce its damage when target id 5 levels above`() {
        val reducedAttack = 50
        val attacker: LeveledCharacter = aLevelOneCharacter()
        val damaged: LeveledCharacter = aLevelSixCharacter()

        val damageAmount = module.calculateDamageAmount(attacker, damaged, minimumRange)

        Assertions.assertEquals(reducedAttack, damageAmount)
    }

    @Test
    fun `should increase its damage when target id 5 levels below`() {
        val levelDifferencePlus = 50
        val attacker = aLevelSixCharacter()
        val damaged = aLevelOneCharacter()

        val damageAmount = module.calculateDamageAmount(attacker, damaged, minimumRange)

        Assertions.assertEquals(baseDamage + levelDifferencePlus, damageAmount)
    }

    @Test
    fun `an attacker to another character outside its range should not deal damage`() {
        val attacker = aLevelOneCharacter()
        val attacked = aLevelOneCharacter()

        val damageAmount = module.calculateDamageAmount(attacker, attacked, minimumRange + 1)

        Assertions.assertEquals(noDamage, damageAmount)
    }

    private fun givenBothCharactersBelongsToSameFaction(attacker: LeveledCharacter, attacked: LeveledCharacter) {
        whenever(factionService.belongsToSameFaction(attacker, attacked)).thenReturn(true)
        whenever(factionService.belongsToSameFaction(attacked, attacker)).thenReturn(true)
    }

    @Test
    fun `a member of the same faction should not damage each others`() {
        val attacker = aLevelOneCharacter()
        val attacked = aLevelOneCharacter()
        givenBothCharactersBelongsToSameFaction(attacker, attacked)

        val damageAmount = module.calculateDamageAmount(attacker, attacked, minimumRange)

        Assertions.assertEquals(noDamage, damageAmount)
    }

    private fun aLevelOneCharacter(): LeveledCharacter {
        val character: LeveledCharacter = mock()
        whenever(character.level).thenReturn(1)
        return character
    }

    private fun aLevelSixCharacter(): LeveledCharacter {
        val character: LeveledCharacter = mock()
        whenever(character.level).thenReturn(6)
        return character
    }

}
