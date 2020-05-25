package katas.rpg

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RPGCharacterAttack {

    private val minimumRange = 1

    @Test
    fun `should deal damage to other character`() {
        val expectedHealth = 900
        val attacker = aCharacter()
        val attacked = aCharacter()

        attacker.attack(attacked, minimumRange)

        with(attacked) {
            Assertions.assertEquals(expectedHealth, health)
        }
    }

    @Test
    fun `should not deal damage to itself`() {
        val attacker = aCharacter()
        val initialHealth = attacker.health

        attacker.attack(attacker, minimumRange)

        with(attacker) {
            Assertions.assertEquals(initialHealth, health)
        }
    }

    private fun aCharacter(): RPGCharacter {
        return CharacterMother.aCharacter()
    }

    @Test
    fun `should reduce its damage when target id 5 levels above`() {
        val reducedAttack = 50
        val attacker = RPGCharacter(level = minimumRange, damageAmount = 100)
        val attackedInitialHealth = 1000
        val damaged = RPGCharacter(level = 6, health = attackedInitialHealth)

        attacker.attack(damaged, minimumRange)

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
        val damaged = RPGCharacter(level = minimumRange, health = attackedInitialHealth)

        attacker.attack(damaged, minimumRange)

        with(damaged) {
            assertEquals(attackedInitialHealth - damageAmount - levelDifferencePlus, health)
        }
    }

    @Test
    fun `an attacker to another character outside its range should not deal damage`() {
        val initialHealth = 500
        val range = 10

        val attacker = RPGCharacter(maxRange = range)
        val attacked = RPGCharacter(health = initialHealth)

        attacker.attack(attacked, range + 1)

        with(attacked) {
            assertEquals(initialHealth, health)
        }
    }
}