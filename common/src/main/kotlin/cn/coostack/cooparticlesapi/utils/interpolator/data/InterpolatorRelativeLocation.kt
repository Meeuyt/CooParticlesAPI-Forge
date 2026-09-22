package cn.coostack.cooparticlesapi.utils.interpolator.data

import cn.coostack.cooparticlesapi.annotations.codec.ForgeStreamCodec
import cn.coostack.cooparticlesapi.utils.GraphMathHelper
import cn.coostack.cooparticlesapi.utils.RelativeLocation

import net.minecraft.world.phys.Vec3

class InterpolatorRelativeLocation(value: RelativeLocation) : AbstractInterpolatorData<RelativeLocation>(value) {

    companion object {
        @JvmStatic
        val CODEC = ForgeStreamCodec.of(
            { buf ->
                val x = buf.readDouble()
                val y = buf.readDouble()
                val z = buf.readDouble()
                InterpolatorRelativeLocation(RelativeLocation(x, y, z))
            },
            { buf, data ->
                buf.writeDouble(data.value.x)
                buf.writeDouble(data.value.y)
                buf.writeDouble(data.value.z)
            }
        )
    }

    override fun getWithInterpolator(progress: Number): RelativeLocation {
        return GraphMathHelper.lerp(progress.toDouble(), last, value)
    }

    override fun getCurrent(): RelativeLocation {
        return RelativeLocation(value.x, value.y, value.z)
    }
}
