package cn.coostack.cooparticlesapi.renderer.terrain

import cn.coostack.cooparticlesapi.renderer.pipeline.CooEffectUvMode
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.core.BlockPos

internal class CooEffectUvVertexConsumer(
    private val delegate: VertexConsumer,
    private val mode: CooEffectUvMode,
    private val blockPos: BlockPos,
    activatedAt: Long
) : VertexConsumer {
    private val activatedAt = activatedAt
    private var x = 0F
    private var y = 0F
    private var z = 0F
    private var baseU = 0F
    private var baseV = 0F

    override fun addVertex(x: Float, y: Float, z: Float) {
        this.x = x
        this.y = y
        this.z = z
        delegate.addVertex(x, y, z)
    }

    override fun setColor(red: Int, green: Int, blue: Int, alpha: Int) {
        delegate.setColor(red, green, blue, alpha)
    }

    override fun setUv(u: Float, v: Float) {
        baseU = u
        baseV = v
        delegate.setUv(u, v)
    }

    override fun setUv1(u: Int, v: Int) {
    }

    override fun setUv2(u: Int, v: Int) {
        val packed = CooEffectUvResolver.packLightWithActivation(
            (u and 0xFFFF) or ((v and 0xFFFF) shl 16),
            activatedAt
        )
        val packedU = packed and 0xFFFF
        val packedV = (packed ushr 16) and 0xFFFF
        delegate.setUv2(packedU, packedV)
    }

    override fun setNormal(x: Float, y: Float, z: Float) {
        val effectUv = CooEffectUvResolver.resolve(
            mode = mode,
            blockPos = blockPos,
            x = this.x,
            y = this.y,
            z = this.z,
            baseU = baseU,
            baseV = baseV,
            normalX = x,
            normalY = y,
            normalZ = z
        )
        delegate.setUv1(CooEffectUvResolver.pack(effectUv.u), CooEffectUvResolver.pack(effectUv.v))
        delegate.setNormal(x, y, z)
    }
}
