package katas.rpg.model

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import katas.rpg.CharacterMother
import katas.rpg.model.attack.AttackModule
import katas.rpg.model.healing.HealingModule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse

class RPGCharacterTest {

    @Test
    fun `should create a character with health and alive`() {
        val character = CharacterMother.aCharacter()

        with(character) {
            assertEquals(1000, health())
            assertEquals(1, level)
            assertTrue(isAlive())
        }
    }


    @Test
    fun `should limit minimun health to zero`() {
        val attacked = CharacterMother.aCharacter()

        attacked.receiveDamage(2000)

        with(attacked) {
            assertEquals(0, health())
        }
    }


    @Test
    fun `should be capable of healing itself character until max healing value`() {
        val maxValidHealth = 1000
        val healer = CharacterMother.aCharacter()

        healer.receiveHealth(2 * maxValidHealth)

        with(healer) {
            assertEquals(maxValidHealth, health())
        }
    }

    @Test
    fun `should delegate attack to its module`() {
        val attackModule: AttackModule = mock()
        val healingModule: HealingModule = mock()
        val attacker = RPGCharacter(attackModule = attackModule, healingModule = healingModule)
        val attacked = RPGCharacter(attackModule = mock(), healingModule = healingModule)

        attacker.attack(attacked, 1)

        verify(attackModule).calculateDamageAmount(attacker, attacked, 1)
    }

    @Test
    fun `when an attack surpasses its health, character must die`() {
        val character = CharacterMother.aCharacter()

        character.receiveDamage(character.health() * 2)

        assertFalse(character.isAlive())
    }
}

