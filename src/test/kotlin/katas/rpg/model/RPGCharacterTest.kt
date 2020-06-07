package katas.rpg.model

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import katas.rpg.CharacterMother
import katas.rpg.model.attack.AttackModule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RPGCharacterTest {

    @Test
    fun `should create a character with health and alive`() {
        val character = CharacterMother.aCharacter()

        with(character) {
            assertEquals(1000, health)
            assertEquals(1, level)
            assertTrue(isAlive())
        }
    }



    @Test
    fun `should limit minimun health to zero`() {
        val attacked = CharacterMother.aCharacter()

        attacked.receiveDamage(2000)

        with(attacked) {
            assertEquals(0, health)
        }
    }

    @Test
    fun `should be capable of healing another character`() {
        val attackModule: AttackModule = mock()
        val healed = RPGCharacter(health = 200, healingAmount = 100, attackModule = attackModule)

        healed.heal()

        with(healed) {
            assertEquals(300, health)
        }
    }

    @Test
    fun `should be capable of healing itself character until max healing value`() {
        val maxValidHealth = 1000
        val attackModule: AttackModule = mock()
        val healer = RPGCharacter(health = 500, healingAmount = 1000, attackModule = attackModule)

        healer.heal()

        with(healer) {
            assertEquals(maxValidHealth, health)
        }
    }

    @Test
    fun `should delegate attack to its type`() {
        val attackModule: AttackModule = mock()
        val attacker = RPGCharacter(attackModule = attackModule)
        val attacked = RPGCharacter(attackModule = mock())

        attacker.attack(attacked, 1)

        verify(attackModule).calculateDamageAmount(attacker, attacked, 1)
    }
}

