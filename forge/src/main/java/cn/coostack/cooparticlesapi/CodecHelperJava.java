package cn.coostack.cooparticlesapi;

import cn.coostack.cooparticlesapi.annotations.codec.CodecHelper;
import cn.coostack.cooparticlesapi.annotations.codec.ForgeStreamCodec;
import net.minecraft.network.FriendlyByteBuf;

public class CodecHelperJava {
    static {
        CodecHelper.register(
                Boolean.class, ForgeStreamCodec.of(FriendlyByteBuf::writeBoolean, FriendlyByteBuf::readBoolean)
        );
        CodecHelper.register(
                Integer.class, ForgeStreamCodec.of(FriendlyByteBuf::writeInt, FriendlyByteBuf::readInt)
        );
        CodecHelper.register(
                Long.class, ForgeStreamCodec.of(FriendlyByteBuf::writeLong, FriendlyByteBuf::readLong)
        );
    }

    public static void init() {
    }
}
