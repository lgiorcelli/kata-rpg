package katas.rpg.model

import katas.rpg.model.attack.Target
import katas.rpg.model.health.HealthModule

class PropsFactory {
    fun createTree(): Prop {
        return Prop("Tree", HealthModule(2000, 2000))
    }
}

class Prop(private val name: String, private val healthModule: HealthModule) : Target {

    override fun receiveDamage(damageAmount: Int) {
        healthModule.receiveDamage(damageAmount)
    }

}
