package katas.rpg

import katas.rpg.model.MeleeAttackType
import katas.rpg.model.RPGCharacter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AttackTypeTest {

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

    @Test
    fun `should reduce its damage when target id 5 levels above`() {
        val reducedAttack = 50
        val attacker = aLowLevelCharacter()
        val damaged = aHighLevelCharacter()
        val attackedInitialHealth = damaged.health

        attacker.attack(damaged, minimumRange)

        with(damaged) {
            assertEquals(attackedInitialHealth - reducedAttack, health)
        }
    }

    @Test
    fun `should increase its damage when target id 5 levels below`() {
        val levelDifferencePlus = 50
        val attacker = aHighLevelCharacter()
        val damaged = aLowLevelCharacter()
        val attackedInitialHealth = damaged.health

        attacker.attack(damaged, minimumRange)

        with(damaged) {
            assertEquals(attackedInitialHealth - 100 - levelDifferencePlus, health)
        }
    }

    @Test
    fun `an attacker to another character outside its range should not deal damage`() {

        val attacker = aLowLevelCharacter()
        val range = attacker.range
        val attacked = aLowLevelCharacter()
        val initialHealth = attacked.health

        attacker.attack(attacked, range + 1)

        with(attacked) {
            assertEquals(initialHealth, health)
        }
    }

    private fun aCharacter(): RPGCharacter {
        return CharacterMother.aCharacter()
    }

    private fun aHighLevelCharacter(): RPGCharacter {
        return RPGCharacter(level = 6, damageAmount = 100, attackType = MeleeAttackType(100, minimumRange))
    }

    private fun aLowLevelCharacter(): RPGCharacter {
        return RPGCharacter(level = 1, damageAmount = 100, attackType = MeleeAttackType(100, minimumRange))
    }
}