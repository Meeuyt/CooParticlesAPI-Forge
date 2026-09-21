package cn.coostack.cooparticlesapi.network.packet.api

interface CommonCodec<T> {
    fun encode(buf: FriendlyByteBuf, value: T)
    fun decode(buf: FriendlyByteBuf): T
    companion object {
        fun <T> of(enc: (FriendlyByteBuf, T) -> Unit, dec: (FriendlyByteBuf) -> T): CommonCodec<T> =
            object : CommonCodec<T> {
                override fun encode(buf: FriendlyByteBuf, value: T) = enc(buf, value)
                override fun decode(buf: FriendlyByteBuf): T = dec(buf)
            }
    }
}
