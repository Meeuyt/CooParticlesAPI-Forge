package cn.coostack.cooparticlesapi.particles

import cn.coostack.cooparticlesapi.compat.IrisCompat
import cn.coostack.cooparticlesapi.network.particle.emitters.ControlableParticleData
import com.mojang.blaze3d.platform.GlStateManager
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.Tesselator
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.VertexFormat
import net.minecraft.client.particle.ParticleRenderType
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.client.renderer.ShaderInstance
import net.minecraft.client.renderer.texture.TextureAtlas
import net.minecraft.client.renderer.texture.TextureManager
import net.minecraft.server.packs.resources.ResourceManager

object CooParticleTextureSheet {

    @JvmStatic
    val sheets = mutableListOf<ParticleRenderType>()
    private var boundedTranslucentShader: ShaderInstance? = null

    @JvmStatic
    val ADDITION_BLEND = register(object : ParticleRenderType {
        override fun begin(
            tesselator: Tesselator,
            manager: TextureManager
        ) {
            RenderSystem.depthMask(true)
            RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_PARTICLES)
            RenderSystem.enableBlend()
            RenderSystem.blendFunc(
                GlStateManager.SourceFactor.ONE,
                GlStateManager.DestFactor.ONE
            )
            tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE)
        }

        override fun toString(): String {
            return "ADDITION_BLEND"
        }
    })

    @JvmStatic
    val ADDITION_BLEND_TRANSLUCENT = register(object : ParticleRenderType {
        override fun begin(
            tesselator: Tesselator,
            manager: TextureManager
        ) {
            RenderSystem.depthMask(true)
            RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_PARTICLES)
            RenderSystem.enableBlend()
            RenderSystem.blendFunc(
                GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE
            )
            tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE)
        }

        override fun toString(): String {
            return "ADDITION_BLEND_TRANSLUCENT"
        }
    })

    @JvmStatic
    val ADDITION_BLEND_TRANSLUCENT_NO_DEPTH_WRITE = register(object : ParticleRenderType {
        override fun begin(
            tesselator: Tesselator,
            manager: TextureManager
        ) {
            RenderSystem.depthMask(false)
            RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_PARTICLES)
            RenderSystem.enableBlend()
            RenderSystem.blendFunc(
                GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE
            )
            tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE)
        }

        override fun toString(): String {
            return "ADDITION_BLEND_TRANSLUCENT_NO_DEPTH_WRITE"
        }
    })

    @JvmStatic
    val PARTICLE_SHEET_TRANSLUCENT_NO_DEPTH_WRITE = registerSheet(
        "PARTICLE_SHEET_TRANSLUCENT_NO_DEPTH_WRITE",
        false,
        GlStateManager.SourceFactor.SRC_ALPHA,
        GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
        GlStateManager.SourceFactor.ONE,
        GlStateManager.DestFactor.ZERO,
    )

    @JvmStatic
    val ADDITION_BLEND_NOT_HDR = registerSheet(
        "ADDITION_BLEND_NOT_HDR",
        true,
        GlStateManager.SourceFactor.ONE,
        GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR,
        GlStateManager.SourceFactor.ZERO,
        GlStateManager.DestFactor.ONE,
    )

    @JvmStatic
    val ADDITION_BLEND_NOT_HDR_NO_DEPTH_WRITE = registerSheet(
        "ADDITION_BLEND_NOT_HDR_NO_DEPTH_WRITE",
        false,
        GlStateManager.SourceFactor.ONE,
        GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR,
        GlStateManager.SourceFactor.ZERO,
        GlStateManager.DestFactor.ONE,
    )

    @JvmStatic
    val ADDITION_BLEND_TRANSLUCENT_NOT_HDR = registerSheet(
        "ADDITION_BLEND_TRANSLUCENT_NOT_HDR",
        true,
        GlStateManager.SourceFactor.ONE,
        GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR,
        GlStateManager.SourceFactor.ZERO,
        GlStateManager.DestFactor.ONE,
        true,
    )

    @JvmStatic
    val ADDITION_BLEND_TRANSLUCENT_NOT_HDR_NO_DEPTH_WRITE = registerSheet(
        "ADDITION_BLEND_TRANSLUCENT_NOT_HDR_NO_DEPTH_WRITE",
        false,
        GlStateManager.SourceFactor.ONE,
        GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR,
        GlStateManager.SourceFactor.ZERO,
        GlStateManager.DestFactor.ONE,
        true,
    )

    fun init() {
        ControlableParticleData.registerRenderType(ParticleRenderType.PARTICLE_SHEET_LIT)
        ControlableParticleData.registerRenderType(ParticleRenderType.TERRAIN_SHEET)
        ControlableParticleData.registerRenderType(ParticleRenderType.NO_RENDER)
        ControlableParticleData.registerRenderType(ParticleRenderType.CUSTOM)
        ControlableParticleData.registerRenderType(ParticleRenderType.PARTICLE_SHEET_OPAQUE)
        ControlableParticleData.registerRenderType(ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT)
    }

    fun reloadShader(resourceManager: ResourceManager) {
        releaseShader()
        boundedTranslucentShader = ShaderInstance(
            resourceManager,
            "coo_particle_screen",
            DefaultVertexFormat.PARTICLE,
        ).also(IrisCompat::markUnskippable)
    }

    fun releaseShader() {
        boundedTranslucentShader?.close()
        boundedTranslucentShader = null
    }

    fun register(type: ParticleRenderType): ParticleRenderType {
        sheets.add(type)
        ControlableParticleData.registerRenderType(type)
        return type
    }

    private fun registerSheet(
        name: String,
        depthWrite: Boolean,
        sourceRgb: GlStateManager.SourceFactor,
        destinationRgb: GlStateManager.DestFactor,
        sourceAlpha: GlStateManager.SourceFactor,
        destinationAlpha: GlStateManager.DestFactor,
        useBoundedTranslucentShader: Boolean = false,
    ): ParticleRenderType = register(object : ParticleRenderType {
        override fun begin(tesselator: Tesselator, manager: TextureManager) {
            RenderSystem.depthMask(depthWrite)
            RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_PARTICLES)
            if (useBoundedTranslucentShader) {
                RenderSystem.setShader { boundedTranslucentShader ?: GameRenderer.getParticleShader() }
            }
            RenderSystem.enableBlend()
            RenderSystem.blendFuncSeparate(sourceRgb, destinationRgb, sourceAlpha, destinationAlpha)
            tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE)
        }

        override fun toString(): String = name
    })
}
