package cn.coostack.cooparticlesapi.network.packet.server

import cn.coostack.cooparticlesapi.CooParticlesConstants
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation

object PacketClearClientStateS2C {
    private val identifierID =
        ResourceLocation(CooParticlesConstants.MOD_ID, "clear_client_state")

    val payloadID = ResourceLocation(CooParticlesConstants.MOD_ID, "clear_client_state")
    val CODEC = ForgeStreamCodec.of({ _, _ -> }, { _ -> PacketClearClientStateS2C })
}
