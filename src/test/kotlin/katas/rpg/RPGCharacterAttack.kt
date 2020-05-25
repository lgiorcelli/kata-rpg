package katas.rpg

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

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
}