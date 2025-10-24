package net.redstone233.tmb.util;

import net.redstone233.tmb.annotation.Platform;

/**
 * 平台检测工具类
 */
public class PlatformDetector {
    
    /**
     * 检测当前运行平台
     */
    public static Platform getCurrentPlatform() {
        // 检测Fabric
        try {
            Class.forName("net.fabricmc.loader.api.FabricLoader");
            return Platform.FABRIC;
        } catch (ClassNotFoundException e) {
            // 不是Fabric，继续检测
        }
        
        // 检测NeoForge
        try {
            Class.forName("net.neoforged.fml.loading.FMLLoader");
            return Platform.NEOFORGE;
        } catch (ClassNotFoundException e) {
            // 不是NeoForge
        }
        
        return Platform.COMMON;
    }
    
    /**
     * 检查当前是否是Fabric平台
     */
    public static boolean isFabric() {
        return getCurrentPlatform() == Platform.FABRIC;
    }
    
    /**
     * 检查当前是否是NeoForge平台
     */
    public static boolean isNeoForge() {
        return getCurrentPlatform() == Platform.NEOFORGE;
    }
    
    /**
     * 安全执行平台特定代码
     */
    public static void executePlatformSpecific(Runnable fabricTask, Runnable neoForgeTask) {
        Platform platform = getCurrentPlatform();
        switch (platform) {
            case FABRIC:
                if (fabricTask != null) fabricTask.run();
                break;
            case NEOFORGE:
                if (neoForgeTask != null) neoForgeTask.run();
                break;
            default:
                System.err.println("未知平台，无法执行平台特定代码");
                break;
        }
    }
}