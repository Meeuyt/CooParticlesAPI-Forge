package cn.coostack.cooparticlesapi.entities

import net.minecraft.nbt.CompoundTag
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.Level

class TestRenderEntity(entityType: EntityType<*>, level: Level) : Entity(entityType, level) {

    constructor(level: Level) : this(CooModEntityTypes.TEST_RENDER.get(), level)

    override fun registerData() {
    }

    override fun readAdditionalSaveData(tag: CompoundTag) {
    }

    override fun addAdditionalSaveData(tag: CompoundTag) {
    }

    override fun tick() {
        // 直接漂浮

    }
}
