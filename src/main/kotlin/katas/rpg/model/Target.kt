package katas.rpg.model

interface Target {
    val health: Int
    fun receiveDamage(damageAmount: Int)
}