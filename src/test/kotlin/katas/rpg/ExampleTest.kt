package katas.rpg

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ExampleTest {
    @Test
    fun `should create a character with health and alive`() {
        val char = RPGCharacter(1000, 1)

        with(char) {
            assertEquals(1000, health)
            assertEquals(1, level)
            assertTrue(isAlive())
        }


    }

    class RPGCharacter(val health: Int, val level: Int) {
        fun isAlive(): Boolean {
            return health > 0
        }
    }

}