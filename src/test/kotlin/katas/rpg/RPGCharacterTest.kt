package katas.rpg

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RPGCharacterTest {


    @Test
    fun `should create a character with health and alive`() {
        val character = RPGCharacter()

        with(character) {
            assertEquals(1000, health)
            assertEquals(1, level)
            assertTrue(isAlive())
        }
    }

    @Test
    fun `should deal damage to other character`() {
        val expectedHealth = 900
        val attacker = RPGCharacter()
        val attacked = RPGCharacter()

        attacker.attack(attacked)

        with(attacked) {
            assertEquals(expectedHealth, health)
        }
    }

    @Test
    fun `should limit minimun health to zero`() {
        val attacker = RPGCharacter(damageAmount = 2000)
        val attacked = RPGCharacter()

        attacker.attack(attacked)

        with(attacked) {
            assertEquals(0, health)
        }
    }

    @Test
    fun `should be capable of healing another character`() {
        val healer = RPGCharacter(healingAmount = 100)
        val damagedCharacter = RPGCharacter(health = 500)

        healer.heal(damagedCharacter)

        with(damagedCharacter) {
            assertEquals(600, health)
        }
    }

    @Test
    fun `should be capable of healing another character until max healing value`() {
        val maxValidHealth = 1000
        val healer = RPGCharacter(healingAmount = 1000)
        val damagedCharacter = RPGCharacter(health = 500)

        healer.heal(damagedCharacter)

        with(damagedCharacter) {
            assertEquals(maxValidHealth, health)
        }
    }

}

class RPGCharacter(
        var health: Int = 1000,
        val level: Int = 1,
        val damageAmount: Int = 100,
        private val healingAmount: Int = 100,
        private val maxValidHealth:Int = 1000
) {
    fun isAlive(): Boolean {
        return health > 0
    }

    fun attack(attacked: RPGCharacter) {
        attacked.receiveDamage(damageAmount)
    }

    private fun receiveDamage(damageAmount: Int) {
        health = maxOf(0, health - damageAmount)
    }

    fun heal(damagedCharacter: RPGCharacter) {
        damagedCharacter.beHealed(healingAmount)
    }

    private fun beHealed(healingAmount: Int) {
        this.health = minOf(maxValidHealth, this.health + healingAmount)
    }

}
