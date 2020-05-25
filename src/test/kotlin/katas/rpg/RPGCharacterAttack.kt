package katas.rpg

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RPGCharacterAttack {

    @Test
    fun `should deal damage to other character`() {
        val expectedHealth = 900
        val attacker = RPGCharacter()
        val attacked = RPGCharacter()

        attacker.attack(attacked)

        with(attacked) {
            Assertions.assertEquals(expectedHealth, health)
        }
    }

    @Test
    fun `should not deal damage to itself`() {
        val attacker = RPGCharacter()
        val initialHealth = attacker.health

        attacker.attack(attacker)

        with(attacker) {
            Assertions.assertEquals(initialHealth, health)
        }
    }

    @Test
    fun `should reduce its damage when target id 5 levels above`() {
        val reducedAttack = 50
        val attacker = RPGCharacter(level = 1, damageAmount = 100)
        val attackedInitialHealth = 1000
        val damaged = RPGCharacter(level = 6, health = attackedInitialHealth)

        attacker.attack(damaged)

        with(damaged) {
            assertEquals(attackedInitialHealth - reducedAttack, health)
        }
    }

    @Test
    fun `should increase its damage when target id 5 levels below`() {
        val levelDifferencePlus = 50
        val damageAmount = 100
        val attacker = RPGCharacter(level = 6, damageAmount = damageAmount)
        val attackedInitialHealth = 1000
        val damaged = RPGCharacter(level = 1, health = attackedInitialHealth)

        attacker.attack(damaged)

        with(damaged) {
            assertEquals(attackedInitialHealth - damageAmount - levelDifferencePlus, health)
        }
    }
}