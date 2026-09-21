package cn.coostack.cooparticlesapi.test.options.particle.emitter

import cn.coostack.cooparticlesapi.annotations.CooAutoRegister
import cn.coostack.cooparticlesapi.network.particle.emitters.AutoTransformableCParticleEmitter
import net.minecraft.world.level.Level
import net.minecraft.world.phys.Vec3
import kotlin.math.PI

@CooAutoRegister
class TestTransformGPUEmitter(pos: Vec3, world: Level?) : AutoTransformableCParticleEmitter(pos, world) {
    override fun doTick() {
        rotateEmitter(PI / 64)
    }
}
