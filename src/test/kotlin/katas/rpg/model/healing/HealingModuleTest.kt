package katas.rpg.model.healing

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import katas.rpg.model.LeveledCharacter
import katas.rpg.model.RPGCharacter
import katas.rpg.model.attack.AttackModule
import katas.rpg.model.faction.FactionService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class HealingModuleTest {
    @Mock
    lateinit var healer: RPGCharacter
    @Mock
    lateinit var healed: RPGCharacter
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

        val healingModule:HealingModule = mock()
        val healed = RPGCharacter(health = 200, attackModule = attackModule, healingModule = healingModule)

        healed.heal()

        verify(healingModule).heal(healed, healed)
    }

    @Test
    fun `a character should be capable of heal an ally`() {
        val healingModule = HealingModule(100, factionService)

        givenBothCharactersAreAllies(healed, healer)

        healingModule.heal(healer, healed)

        verify(healed).receiveHealth(100)
    }

    @Test
    fun `a character should not be capable of heal a non ally`() {
        val healingModule = HealingModule(100, factionService)
        givenBothCharactersAreNotAllies(healed, healer)

        healingModule.heal(healer, healed)

        verify(healed, times(0)).receiveHealth(any())
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