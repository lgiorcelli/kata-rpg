package katas.rpg.model.healing

import com.nhaarman.mockito_kotlin.whenever
import katas.rpg.CharacterMother
import katas.rpg.model.LeveledCharacter
import katas.rpg.model.RPGCharacter
import katas.rpg.model.attack.AttackModule
import katas.rpg.model.faction.FactionService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class HealingModuleTest {
    @Mock
    private lateinit var factionService: FactionService
    @Mock
    private lateinit var attackModule: AttackModule

    @BeforeEach
    internal fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `should be capable of healing itself`() {
        val healed = RPGCharacter(health = 200, attackModule = attackModule, healingModule = HealingModule(100))

        healed.heal()

        with(healed) {
            Assertions.assertEquals(300, health)
        }
    }

    @Test
    fun `a character should be capable of heal an ally`() {
        val healer = CharacterMother.aCharacter()
        val healed = CharacterMother.aCharacter()
        val initialHealth = healed.health
        givenBothCharactersAreAllies(healed, healer)
        healed.receiveDamage(1)

        healer.heal(healed, true)

        with(healed) {
            Assertions.assertEquals(initialHealth, health)
        }
    }

    @Test
    fun `a character should not be capable of heal a non ally`() {
        val healer = CharacterMother.aCharacter()
        val healed = CharacterMother.aCharacter()
        givenBothCharactersAreNotAllies(healed, healer)
        healed.receiveDamage(1)
        val expectedHealth = healed.health

        healer.heal(healed, false)

        with(healed) {
            Assertions.assertEquals(expectedHealth, health)
        }
    }

    private fun givenBothCharactersAreNotAllies(attacker: RPGCharacter, attacked: RPGCharacter) {
        defineAlliance(attacker, attacked, false)
    }

    private fun givenBothCharactersAreAllies(attacker: LeveledCharacter, attacked: LeveledCharacter) {
        defineAlliance(attacker, attacked, true)
    }

    private fun defineAlliance(attacker: LeveledCharacter, attacked: LeveledCharacter, areAllies: Boolean) {
        whenever(factionService.areAllies(attacker, attacked)).thenReturn(areAllies)
        whenever(factionService.areAllies(attacked, attacker)).thenReturn(areAllies)
    }

}