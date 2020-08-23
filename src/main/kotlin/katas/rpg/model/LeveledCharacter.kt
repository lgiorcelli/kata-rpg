package katas.rpg.model

import katas.rpg.model.attack.Target

interface LeveledCharacter : Target {
    val level: Int
}