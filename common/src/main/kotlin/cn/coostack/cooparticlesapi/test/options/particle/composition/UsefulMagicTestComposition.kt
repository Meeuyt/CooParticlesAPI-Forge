package cn.coostack.cooparticlesapi.test.options.particle.composition

import cn.coostack.cooparticlesapi.annotations.CodecField
import cn.coostack.cooparticlesapi.annotations.CooAutoRegister
import cn.coostack.cooparticlesapi.cparticle.CParticleCurve
import cn.coostack.cooparticlesapi.network.particle.composition.AutoSequencedParticleComposition
import cn.coostack.cooparticlesapi.utils.RelativeLocation
import cn.coostack.cooparticlesapi.utils.helper.impl.composition.CompositionAlphaHelper
import cn.coostack.cooparticlesapi.utils.helper.impl.composition.CompositionBezierScaleHelper
import net.minecraft.world.level.Level
import net.minecraft.world.phys.Vec3
import org.joml.Vector3f

@CooAutoRegister
class UsefulMagicTestComposition(position: Vec3, world: Level? = null) :
    AutoSequencedParticleComposition(position, world) {
    @CodecField
    var direction: RelativeLocation = RelativeLocation(0.0, -1.0, 0.0)

    @CodecField
    var color: Vector3f = Vector3f(0.858824F, 0.341176F, 0.341176F)

    @CodecField
    var age: Int = 0


    val option: Int = 3

    private val scaleHelper = CompositionBezierScaleHelper(
        20,
        0.01,
        1.0,
        RelativeLocation(9.308036, 1.141461, 0.0),
        RelativeLocation(9.397321, 1.169344, 0.0)
    )


    private val alphaHelper = CompositionAlphaHelper(0.1, 1.0, 20)

    init {
        axis = RelativeLocation(0.0, -1.0, 0.0)
        scaleHelper.loadControler(this)
        setDisabledInterval(20)
        alphaHelper.loadControler(this)
        alphaHelper.resetAlphaMax()
        animate.addAnimate(2) { age > 0 }
            .addAnimate(5) { age > 60 }
    }

    override fun remove() {
        if (status.isDisable()) {
            super.remove()
        } else {
            status.disable()
        }
    }

    override fun onDisplay() {
        addPreTickAction {
            if (age++ > 60) {
                scaleHelper.doScale()
            }
            rotateToPoint(direction)
            if (status.isDisable()) {
                alphaHelper.decreaseAlpha()
            }
            toggleRelative()
        }
    }
}
