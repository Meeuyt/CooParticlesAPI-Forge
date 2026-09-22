package cn.coostack.cooparticlesapi.utils.interpolator.data

import cn.coostack.cooparticlesapi.utils.GraphMathHelper

import net.minecraft.world.phys.Vec3

class InterpolatorVec3d(value: Vec3) : AbstractInterpolatorData<Vec3>(value) {

    companion object {
        @JvmStatic
        val CODEC = ForgeStreamCodec.of(
            { buf ->
                val current = buf.readVec3()
                InterpolatorVec3d(current)
            },
            { buf, data ->
                buf.writeVec3(data.value)
            }
        )
    }

    override fun getWithInterpolator(progress: Number): Vec3 {
        return GraphMathHelper.lerp(progress.toDouble(), last, value)
    }

    override fun getCurrent(): Vec3 {
        return value
    }

    operator fun plus(vec: Vec3): InterpolatorVec3d {
        uploadData(value.add(vec))
        return this
    }

    operator fun minus(vec: Vec3): InterpolatorVec3d {
        uploadData(value.sub(vec))
        return this
    }

    operator fun times(double: Double): InterpolatorVec3d {
        uploadData(value.mul(double))
        return this
    }

    operator fun div(double: Double): InterpolatorVec3d {
        require(double != 0.0) { "Division by zero" }
        uploadData(value.mul(1.0 / double))
        return this
    }

    operator fun unaryMinus(): InterpolatorVec3d {
        uploadData(value.mul(-1.0))
        return this
    }
}
