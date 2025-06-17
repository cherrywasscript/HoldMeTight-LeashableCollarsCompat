package com.ricardthegreat.holdmetight_leashablecollars_compat;

import com.mojang.logging.LogUtils;
import com.ricardthegreat.holdmetight.network.PacketHandler;
import com.ricardthegreat.holdmetight.size.PlayerSize;
import com.ricardthegreat.holdmetight.size.PlayerSizeProvider;
import com.ricardthegreat.holdmetight.utils.sizeutils.EntitySizeUtils;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import virtuoel.pehkui.api.ScaleTypes;

import org.jlortiz.playercollars.leash.LeashProxyEntity;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(LeashableCollarsCompat.MODID)
public class LeashableCollarsCompat
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "holdmetight_leashablecollars_compat";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    public LeashableCollarsCompat(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void commonSetup(final FMLCommonSetupEvent event){
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event){
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents{
    }


    @SubscribeEvent
    public void entityJoinLevelEvent(EntityJoinLevelEvent event){
    }
}
