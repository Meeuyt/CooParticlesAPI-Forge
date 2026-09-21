package cn.coostack.cooparticlesapi.platform

import net.minecraft.server.MinecraftServer
import net.minecraft.world.level.Level

val MinecraftServer.tickRateManager: TickRateManager1_20_1
    get() = TickRateManager1_20_1()

val Level.tickRateManager: TickRateManager1_20_1
    get() = TickRateManager1_20_1()
