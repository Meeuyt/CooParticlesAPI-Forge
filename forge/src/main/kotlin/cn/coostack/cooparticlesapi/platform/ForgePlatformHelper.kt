package cn.coostack.cooparticlesapi.platform

import cn.coostack.cooparticlesapi.enums.DistType
import cn.coostack.cooparticlesapi.platform.services.IPlatformHelper
import net.minecraftforge.fml.loading.FMLLoader
import net.minecraftforge.fml.loading.LoadingModList

class ForgePlatformHelper : IPlatformHelper {
    override fun getPlatformName(): String {
        return "Forge"
    }

    override fun isModLoaded(modId: String): Boolean {
        return LoadingModList.get().getModFileById(modId) != null
    }

    override fun getDistType(): DistType {
        return when (FMLLoader.getDist()) {
            net.minecraftforge.api.distmarker.Dist.CLIENT -> DistType.CLIENT
            else -> DistType.SERVER
        }
    }
}
