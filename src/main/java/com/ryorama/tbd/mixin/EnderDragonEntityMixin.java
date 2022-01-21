package com.ryorama.tbd.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnderDragonEntity.class)
public abstract class EnderDragonEntityMixin extends MobEntity implements Monster {

    protected EnderDragonEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "updatePostDeath")
    protected void updatePostDeath(CallbackInfo ci) {
        for (int i = 0; i <= this.world.getServer().getPlayerManager().getPlayerList().size() - 1; i++) {
            this.world.getServer().getPlayerManager().getPlayerList().get(i).sendMessage(new TranslatableText("A large pulse of energy has cause ancient beings to awaken. Be as careful as you can while gathering new materials that have been brought upon this world!s").formatted(Formatting.BOLD).formatted(Formatting.DARK_BLUE), false);
        }
    }
}
