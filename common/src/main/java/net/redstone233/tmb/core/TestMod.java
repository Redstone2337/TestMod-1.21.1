package net.redstone233.tmb.core;

import net.redstone233.tmb.annotation.Fabric;
import net.redstone233.tmb.annotation.NeoForged;
import net.redstone233.tmb.util.PlatformDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 多平台模组主类
 */
public class TestMod {
    public static final String MOD_ID = "tmb";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    private static TestMod instance;
    
    public static void init() {
        instance = new TestMod();
        instance.onInitialize();
    }
    
    @Fabric
    public void onFabricInitialize() {
        // 这个方法只会在Fabric平台被调用
        LOGGER.info("在Fabric平台上初始化模组");
    }
    
    @NeoForged
    public void onNeoForgeInitialize() {
        // 这个方法只会在NeoForge平台被调用
        LOGGER.info("在NeoForge平台上初始化模组");
    }
    
    private void onInitialize() {
        LOGGER.info("开始初始化多平台模组");
        
        // 根据平台执行相应的初始化方法
        PlatformDetector.executePlatformSpecific(
            this::onFabricInitialize,
            this::onNeoForgeInitialize
        );
        
        LOGGER.info("多平台模组初始化完成");
    }
    
    public static TestMod getInstance() {
        return instance;
    }
}