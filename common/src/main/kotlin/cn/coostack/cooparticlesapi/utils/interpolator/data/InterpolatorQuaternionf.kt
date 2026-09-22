package cn.coostack.cooparticlesapi.utils.interpolator.data

import cn.coostack.cooparticlesapi.annotations.codec.ForgeStreamCodec
import cn.coostack.cooparticlesapi.utils.GraphMathHelper

import org.joml.Quaternionf

class InterpolatorQuaternionf(value: Quaternionf) : AbstractInterpolatorData<Quaternionf>(value) {

    companion object {
        @JvmStatic
        val CODEC = ForgeStreamCodec.of(
            { buf ->
                val x = buf.readFloat()
                val y = buf.readFloat()
                val z = buf.readFloat()
                val w = buf.readFloat()
                InterpolatorQuaternionf(Quaternionf(x, y, z, w))
            },
            { buf, data ->
                buf.writeFloat(data.value.x)
                buf.writeFloat(data.value.y)
                buf.writeFloat(data.value.z)
                buf.writeFloat(data.value.w)
            }
        )
    }

    override fun getWithInterpolator(progress: Number): Quaternionf {
        return GraphMathHelper.lerp(progress.toFloat(), last, value)
    }

    override fun getCurrent(): Quaternionf {
        return Quaternionf(value)
    }
}
