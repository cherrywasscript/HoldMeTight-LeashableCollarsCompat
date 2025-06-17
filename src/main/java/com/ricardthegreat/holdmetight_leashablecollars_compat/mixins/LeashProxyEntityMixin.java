package com.ricardthegreat.holdmetight_leashablecollars_compat.mixins;

import java.util.Objects;

import org.jlortiz.playercollars.leash.LeashProxyEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.ricardthegreat.holdmetight.utils.sizeutils.EntitySizeUtils;
import com.ricardthegreat.holdmetight_leashablecollars_compat.interfaces.LeashProxyInterface;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;
import virtuoel.pehkui.util.PehkuiEntityExtensions;

@Mixin(LeashProxyEntity.class)
public class LeashProxyEntityMixin extends Turtle{

    private float scale;

    public LeashProxyEntityMixin(EntityType<? extends Turtle> p_30132_, Level p_30133_) {
        super(p_30132_, p_30133_);
    }

    @Shadow 
    LivingEntity target;

    @Inject(at = @At("TAIL"), method = "<init>")
    public void constructor(LivingEntity target, CallbackInfo info){
        EntitySizeUtils.setSize(this, EntitySizeUtils.getSize(target), 0);
        scale = EntitySizeUtils.getSize(target);
    }

    @Inject(at = @At("TAIL"), method = "tick()V", cancellable = true)
    public void tick(CallbackInfo info) {
        if (this.target.level() == this.level() && this.target.isAlive()) {

            
            if (scale != EntitySizeUtils.getSize(target)) {
                EntitySizeUtils.setSize(this, EntitySizeUtils.getSize(target), 0);
                scale = EntitySizeUtils.getSize(target);
            }


            Vec3 posActual = this.position();
            Vec3 posTarget = this.target.position().add(0.0, -0.1 + 1.4*EntitySizeUtils.getSize(target), -0.15*scale);
            if (!Objects.equals(posActual, posTarget)) {
                this.setRot(0.0F, 0.0F);
                this.setPos(posTarget.x(), posTarget.y(), posTarget.z());
                this.setBoundingBox(this.getDimensions(Pose.DYING).makeBoundingBox(posTarget));
            }
        }
    }
}
