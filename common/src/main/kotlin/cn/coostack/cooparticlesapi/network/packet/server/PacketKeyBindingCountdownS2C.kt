package cn.coostack.cooparticlesapi.network.packet.server

import cn.coostack.cooparticlesapi.CooParticlesConstants
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation

class PacketKeyBindingCountdownS2C(val key: ResourceLocation, val cd: Int) {
    companion object {
        private val identifierID =
            ResourceLocation(CooParticlesConstants.MOD_ID, "key_binding_countdown")
        val payloadID = ResourceLocation(CooParticlesConstants.MOD_ID, "key_binding_countdown")
        val CODEC = ForgeStreamCodec.of({ packet, buf ->
            buf.writeResourceLocation(packet.key)
            buf.writeInt(packet.cd)
        }, { buf ->
            PacketKeyBindingCountdownS2C(buf.readResourceLocation(), buf.readInt())
        })
    }
}
