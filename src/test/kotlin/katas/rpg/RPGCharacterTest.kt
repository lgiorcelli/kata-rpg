package katas.rpg

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RPGCharacterTest {


    @Test
    fun `should create a character with health and alive`() {
        val character = aCharacter(100)

        with(character) {
            assertEquals(1000, health)
            assertEquals(1, level)
            assertTrue(isAlive())
        }
    }

    @Test
    fun `should deal damage to other character`() {
        val expectedHealth = 900
        val attacker = aCharacter()
        val attacked = aCharacter()

        attacker.attack(attacked)

        with(attacked) {
            assertEquals(expectedHealth, health)
        }
    }

    @Test
    fun `should limit minimun health to zero`() {
        val attacker = aCharacter(damageAmount = 2000)
        val attacked = aCharacter()

        attacker.attack(attacked)

        with(attacked) {
            assertEquals(0, health)
        }
    }

    private fun aCharacter(damageAmount: Int = 100): RPGCharacter {
        return RPGCharacter(1000, 1, damageAmount = damageAmount)
    }

}

class RPGCharacter(var health: Int, val level: Int, val damageAmount: Int = 100) {
    fun isAlive(): Boolean {
        return health > 0
    }

    fun attack(attacked: RPGCharacter) {
        attacked.receiveDamage(damageAmount)
    }

    private fun receiveDamage(damageAmount: Int) {
        health = manageHealtUnderflow(damageAmount)
    }

    private fun manageHealtUnderflow(damageAmount: Int): Int {
        val healthAfterAttack = health - damageAmount
        return if (healthAfterAttack < 0) 0 else healthAfterAttack
    }
}
