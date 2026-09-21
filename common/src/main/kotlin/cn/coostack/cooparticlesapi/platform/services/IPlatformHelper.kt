package cn.coostack.cooparticlesapi.platform.services

import net.minecraft.server.level.ServerPlayer

interface IPlatformHelper {
    fun getPlatformName(): String
    fun isModLoaded(modId: String): Boolean
    fun getDistType(): cn.coostack.cooparticlesapi.enums.DistType
}
