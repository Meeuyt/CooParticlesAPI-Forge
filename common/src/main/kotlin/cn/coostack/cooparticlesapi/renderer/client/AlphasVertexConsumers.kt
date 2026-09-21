package cn.coostack.cooparticlesapi.renderer.client

import com.mojang.blaze3d.vertex.VertexConsumer

class AlphasVertexConsumers(var alpha: Int, val consumer: VertexConsumer) : VertexConsumer {
    override fun addVertex(
        x: Float,
        y: Float,
        z: Float
    ) {
        consumer.addVertex(x, y, z)
    }

    override fun setColor(
        red: Int,
        green: Int,
        blue: Int,
        alpha: Int
    ) {
        consumer.setColor(red, green, blue, this.alpha)
    }

    override fun setUv(u: Float, v: Float) {
        consumer.setUv(u, v)
    }

    override fun setUv1(u: Int, v: Int) {
        consumer.setUv1(u, v)
    }

    override fun setUv2(u: Int, v: Int) {
        consumer.setUv2(u, v)
    }

    override fun setNormal(
        normalX: Float,
        normalY: Float,
        normalZ: Float
    ) {
        consumer.setNormal(normalX, normalY, normalZ)
    }
}
