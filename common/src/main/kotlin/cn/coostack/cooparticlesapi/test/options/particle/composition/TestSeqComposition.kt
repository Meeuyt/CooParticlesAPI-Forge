package cn.coostack.cooparticlesapi.test.options.particle.composition

import cn.coostack.cooparticlesapi.annotations.CooAutoRegister
import cn.coostack.cooparticlesapi.network.particle.composition.AutoSequencedParticleComposition
import net.minecraft.world.level.Level
import net.minecraft.world.phys.Vec3
import kotlin.math.PI

@CooAutoRegister
class TestSeqComposition(position: Vec3, world: Level? = null) : AutoSequencedParticleComposition(position, world) {

    override fun onDisplay() {
        var flag = false
        addPreTickAction {
            if (displayedParticleCount !in 1..<count) {
                flag = !flag
            }
            if (flag) {
                addMultiple(100)
            } else {
                removeMultiple(100)
            }
        }
    }
}
