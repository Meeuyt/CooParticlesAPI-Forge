package cn.coostack.cooparticlesapi.extend

import cn.coostack.cooparticlesapi.CooParticlesConstants
import net.minecraft.resources.ResourceLocation

/**
 * 缩短指令长度
 */
fun ofID(modID: String, path: String) = ResourceLocation(modID, path)

fun ofID(path: String) = ResourceLocation(CooParticlesConstants.MOD_ID, path)

fun ofVanillaID(path: String) = ResourceLocation("minecraft", path)

fun ResourceLocation.asID(modID: String): ResourceLocation = ofID(modID, this.path)