package katas.rpg

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
        val attacker = RPGCharacter(damageAmount = 2000)
        val attacked = CharacterMother.aCharacter()

        attacker.attack(attacked, 1)

        with(attacked) {
            assertEquals(0, health)
        }
    }

    @Test
    fun `should be capable of healing another character`() {
        val healed = RPGCharacter(health = 200, healingAmount = 100)

        healed.heal()

        with(healed) {
            assertEquals(300, health)
        }
    }

    @Test
    fun `should be capable of healing itself character until max healing value`() {
        val maxValidHealth = 1000
        val healer = RPGCharacter(healingAmount = 1000, health = 500)

        healer.heal()

        with(healer) {
            assertEquals(maxValidHealth, health)
        }
    }

}

