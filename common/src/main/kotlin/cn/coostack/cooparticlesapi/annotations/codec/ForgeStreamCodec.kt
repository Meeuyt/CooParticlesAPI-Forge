package cn.coostack.cooparticlesapi.annotations.codec

interface ForgeStreamCodec<T> {
    fun decode(buf: net.minecraft.network.FriendlyByteBuf): T
    fun encode(buf: net.minecraft.network.FriendlyByteBuf, value: T)
    companion object {
        fun <T> of(dec: (net.minecraft.network.FriendlyByteBuf) -> T, enc: (net.minecraft.network.FriendlyByteBuf, T) -> Unit): ForgeStreamCodec<T> =
            object : ForgeStreamCodec<T> {
                override fun decode(buf: net.minecraft.network.FriendlyByteBuf): T = dec(buf)
                override fun encode(buf: net.minecraft.network.FriendlyByteBuf, value: T) = enc(buf, value)
            }
    }
}
