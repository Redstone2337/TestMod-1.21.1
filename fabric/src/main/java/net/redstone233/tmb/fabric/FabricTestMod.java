package net.redstone233.tmb.fabric;

import net.redstone233.tmb.core.TestMod;
import net.redstone233.tmb.annotation.Fabric;
import net.fabricmc.api.ModInitializer;

import java.util.logging.Logger;

/**
 * Fabric平台入口点
 */
@Fabric
public class FabricTestMod implements ModInitializer {
    public static final Logger LOGGER = Logger.getLogger(TestMod.MOD_ID);
    
    @Override
    public void onInitialize() {
        // 调用通用的初始化逻辑
        TestMod.init();
        
        // Fabric特定的初始化
        initializeFabricSpecific();
    }
    
    @Fabric
    private void initializeFabricSpecific() {
        // 这里是Fabric平台特定的初始化代码
        LOGGER.info("执行Fabric平台特定的初始化");
    }
}