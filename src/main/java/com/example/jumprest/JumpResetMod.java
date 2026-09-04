package com.example.jumprest;

import com.example.jumprest.config.JumpResetConfig;
import com.example.jumprest.hud.JumpResetHudOverlay;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JumpResetMod implements ClientModInitializer {
    public static final String MOD_ID = "jumprest";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    private static JumpResetHudOverlay hudOverlay;

    @Override
    public void onInitializeClient() {
        LOGGER.info("Initializing Jump Reset Training Mod for Minecraft 1.21.1");
        JumpResetConfig.loadConfig();
        hudOverlay = new JumpResetHudOverlay();
        HudRenderCallback.EVENT.register(hudOverlay);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (hudOverlay != null) {
                hudOverlay.onClientTick(client);
            }
        });
        LOGGER.info("Jump Reset Training Mod initialized successfully");
    }
    
    public static JumpResetHudOverlay getHudOverlay() {
        return hudOverlay;
    }
}
