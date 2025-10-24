package net.redstone233.tmb.fabric.client;

import net.redstone233.tmb.annotation.Fabric;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.redstone233.tmb.annotation.Platform;
import net.redstone233.tmb.fabric.FabricTestMod;

/**
 * Fabric客户端处理器
 */
@Fabric
public class FabricTestModClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        // Fabric客户端特定的初始化
        setupFabricClientEvents();
    }
    
    @Fabric
    private void setupFabricClientEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // Fabric客户端tick事件
            if (client.player != null && client.player.age % 100 == 0) {
                FabricTestMod.LOGGER.info("Fabric客户端运行中...");
            }
        });
    }
}