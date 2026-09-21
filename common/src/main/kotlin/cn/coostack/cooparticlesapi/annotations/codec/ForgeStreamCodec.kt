package cn.coostack.cooparticlesapi.annotations.codec

import net.minecraft.network.FriendlyByteBuf

interface ForgeStreamCodec<T : FriendlyByteBuf, V> {
    fun encode(buf: T, value: V)
    fun decode(buf: T): V

    companion object {
        fun <T : FriendlyByteBuf, V> of(
            encode: (T, V) -> Unit,
            decode: (T) -> V
        ): ForgeStreamCodec<T, V> {
            return object : ForgeStreamCodec<T, V> {
                override fun encode(buf: T, value: V) {
                    encode(buf, value)
                }

                override fun decode(buf: T): V {
                    return decode(buf)
                }
            }
        }
    }
}
