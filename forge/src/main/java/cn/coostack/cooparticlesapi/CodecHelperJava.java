package cn.coostack.cooparticlesapi;

import cn.coostack.cooparticlesapi.annotations.codec.CodecHelper;
import cn.coostack.cooparticlesapi.annotations.codec.ForgeStreamCodec;
import net.minecraft.network.PacketByteBuf;

public class CodecHelperJava {
    static {
        CodecHelper.register(
                Boolean.class, ForgeStreamCodec.of(PacketByteBuf::writeBoolean, PacketByteBuf::readBoolean)
        );
        CodecHelper.register(
                Integer.class, ForgeStreamCodec.of(PacketByteBuf::writeInt, PacketByteBuf::readInt)
        );
        CodecHelper.register(
                Long.class, ForgeStreamCodec.of(PacketByteBuf::writeLong, PacketByteBuf::readLong)
        );
    }

    public static void init() {
    }
}
