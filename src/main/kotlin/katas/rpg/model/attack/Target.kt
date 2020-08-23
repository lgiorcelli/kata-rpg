package katas.rpg.model.attack

interface Target {
    fun receiveDamage(damageAmount: Int)
}