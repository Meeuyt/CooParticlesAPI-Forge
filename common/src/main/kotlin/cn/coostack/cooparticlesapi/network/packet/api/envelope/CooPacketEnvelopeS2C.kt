package cn.coostack.cooparticlesapi.network.packet.api.envelope

import cn.coostack.cooparticlesapi.CooParticlesConstants
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation

class CooPacketEnvelopeS2C(
    val kindId: Int,
    val packetId: ResourceLocation,
    val correlationId: Long,
    val timeoutTicks: Int,
    val data: ByteArray,
) {
    companion object {
        @JvmStatic
        fun write(buf: FriendlyByteBuf, packet: CooPacketEnvelopeS2C) {
            buf.writeVarInt(packet.kindId)
            buf.writeResourceLocation(packet.packetId)
            buf.writeLong(packet.correlationId)
            buf.writeVarInt(packet.timeoutTicks)
            buf.writeByteArray(packet.data)
        }

        @JvmStatic
        fun read(buf: FriendlyByteBuf): CooPacketEnvelopeS2C {
            return CooPacketEnvelopeS2C(
                kindId = buf.readVarInt(),
                packetId = buf.readResourceLocation(),
                correlationId = buf.readLong(),
                timeoutTicks = buf.readVarInt(),
                data = buf.readByteArray()
            )
        }
    }
}
